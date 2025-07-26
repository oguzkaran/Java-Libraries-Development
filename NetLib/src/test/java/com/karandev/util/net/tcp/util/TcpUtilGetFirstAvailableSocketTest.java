package com.karandev.util.net.tcp.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static com.karandev.util.net.TcpUtil.*;

public class TcpUtilGetFirstAvailableSocketTest {
    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 8192;
    private static final int[] ALLOWED_PORT_RANGE = IntStream.rangeClosed(1024, 65533).toArray();

    @Test
    public void givenPortNumberRange_whenAvailable_thenPortAssigned() throws InterruptedException
    {
        Thread.sleep(100);
        try (var serverSocket = getFirstAvailableSocketWithBacklog(1024, MIN_PORT, MAX_PORT).orElseThrow()) {

            var localPort = serverSocket.getLocalPort();

            Assertions.assertTrue(IntStream.rangeClosed(MIN_PORT, MAX_PORT)
                    .anyMatch(i -> i == localPort));

            System.out.printf("Assigned Port: %s%n", localPort);
        }
        catch (NoSuchElementException ex) {
            Assertions.fail("No available ports in the range [%s, %s]".formatted(MIN_PORT, MAX_PORT));
        }
        catch (IOException ignore) {

        }
    }

    @Test
    public void givenPortNumber_whenPortIsUsed_thenReturnEmptyOptional()
    {
        try (var serverSocket = getFirstAvailableSocketWithBacklog(1, MIN_PORT, MAX_PORT).orElseThrow()) {
            var inUsePort = serverSocket.getLocalPort();
            var socketOpt = getFirstAvailableSocket(inUsePort);

            Assertions.assertTrue(socketOpt.isEmpty());
        }
        catch (IOException ignore) {

        }
    }

    @Test
    public void givenPortNumberRange_whenOutOfAllowedRange_thenThrowIllegalArgumentException()
    {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> getFirstAvailableSocket(0, Character.MAX_VALUE + 1));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> getFirstAvailableSocketWithBacklog(1024, 0, Character.MAX_VALUE + 1));
    }

    @Test
    public void whenAllPortsAreBusy_thenReturnEmptyOptional()
    {
        var socketList = new ArrayList<ServerSocket>();
        Arrays.stream(ALLOWED_PORT_RANGE).forEach(port-> {
             try {
                 socketList.add(new ServerSocket(port));
             }
             catch (IOException ignore){}
        });

        var socketOpt = getFirstAvailableSocket(ALLOWED_PORT_RANGE);
        Assertions.assertTrue(socketOpt.isEmpty());

        socketList.forEach(ss -> {
            try {ss.close();}
            catch (IOException ignore) {}
        });
    }
}
