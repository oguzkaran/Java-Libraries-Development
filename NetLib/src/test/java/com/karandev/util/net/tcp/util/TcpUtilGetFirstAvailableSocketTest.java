package com.karandev.util.net.tcp.util;

import com.karandev.util.net.TcpUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.stream.IntStream;

@Disabled("Run the debug test")
public class TcpUtilGetFirstAvailableSocketTest {
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
        var serverSocketOpt = TcpUtil.getFirstAvailableSocket(1, 1024, 65535);
        int port;

        if (serverSocketOpt.isPresent()) {
            port = serverSocketOpt.get().getLocalPort();
            int[] ports = {port};

            Assertions.assertTrue(TcpUtil.getFirstAvailableSocket(1, ports).isEmpty());
        }
        else
            Assertions.fail("No available ports");
    }
}
