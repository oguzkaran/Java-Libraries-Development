package com.karandev.util.net.tcp.util;

import com.karandev.util.net.TcpUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class TcpUtilGetFirstAvailableSocketTest {
    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 8192;

    @Test
    public void givenPortNumberRange_whenAvailable_thenPortAssigned() throws InterruptedException
    {
        Thread.sleep(100);
        try (var serverSocket = TcpUtil.getFirstAvailableSocketWithBacklog(1024, MIN_PORT, MAX_PORT).orElseThrow()) {

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
        try (var serverSocket = TcpUtil.getFirstAvailableSocketWithBacklog(1, MIN_PORT, MAX_PORT).orElseThrow()) {
            var inUsePort = serverSocket.getLocalPort();
            var socketOpt = TcpUtil.getFirstAvailableSocket(inUsePort);

            Assertions.assertTrue(socketOpt.isEmpty());
        }
        catch (IOException ignore) {

        }
    }
}
