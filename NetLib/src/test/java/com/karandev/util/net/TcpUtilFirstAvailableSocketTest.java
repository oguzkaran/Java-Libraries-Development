package com.karandev.util.net;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Disabled("Run the debug test")
public class TcpUtilFirstAvailableSocketTest {
    @Test
    public void test() throws IOException
    {
        var serverSocketOpt = TcpUtil.getFirstAvailableSocket(512, 1024, 65535);
        System.out.println(serverSocketOpt.get().getLocalPort());
        Assertions.assertTrue(serverSocketOpt.isPresent());
    }
}
