package com.karandev.util.net;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.stream.IntStream;

@Disabled("Run the debug test")
public class TcpUtilFirstAvailableSocketTest {
    private void presentCallback(ServerSocket serverSocket)
    {
        Assertions.fail();
    }

    @Test
    public void givenPortNumberRange_whenAvailable_thenPortAssigned()
    {
        var serverSocketOpt = TcpUtil.getFirstAvailableSocket(1, 1024, 65535);

        Assertions.assertTrue(serverSocketOpt.isPresent());
        var localPort = serverSocketOpt.get().getLocalPort();

        Assertions.assertTrue(IntStream.rangeClosed(1024, 65535)
                .anyMatch(i -> i == localPort));

        System.out.printf("Assigned Port: %s%n", localPort);
    }

    @Test
    public void givenPortNumber_whenPortIsUsed_thenReturnEmptyOptional() throws IOException
    {
        int[] port = {6666};

        try (var busySocket = new ServerSocket(6666)) {
            busySocket.setSoTimeout(5000);

            var serverSocketOpt = TcpUtil.getFirstAvailableSocket(1, port);
            serverSocketOpt.ifPresent(this::presentCallback);
        }
    }
}
