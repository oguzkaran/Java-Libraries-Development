package com.karandev.util.net.tcp;

import com.karandev.util.net.TCP;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class TcpSendReceiveDoubleTest {
    private static final String HOST = "localhost";
    private static final int PORT = 50500;
    private static final int SOCKET_TIMEOUT = 1000;
    private static final double SEND_DOUBLE = 34.5;
    private ServerSocket m_serverSocket;
    private ExecutorService m_threadPool;
    private final AtomicReference<Throwable> m_exception = new AtomicReference<>();

    private void serverCallback()
    {
        try {
            m_serverSocket = new ServerSocket(PORT, 1024);
            var clientSocket = m_serverSocket.accept();
            clientSocket.setSoTimeout(SOCKET_TIMEOUT);
            var tcp = new TCP(clientSocket);

            var val = tcp.receiveDouble();

            Assertions.assertEquals(SEND_DOUBLE, val);
        }
        catch (IOException ex) {
            m_exception.set(ex);
        }
    }

    @BeforeEach
    public void setUp()
    {
        m_threadPool = Executors.newSingleThreadExecutor();
        m_threadPool.execute(this::serverCallback);
    }

    @Test
    public void test() throws IOException, InterruptedException
    {
        Thread.sleep(100);
        try (var socket = new Socket(HOST, PORT)) {
            var tcp = new TCP(socket);

            tcp.sendDouble(SEND_DOUBLE);
        }
    }

    @AfterEach
    public void tearDown() throws IOException
    {
        Assertions.assertNull(m_exception.get());
        m_serverSocket.close();
        m_threadPool.shutdownNow();
    }
}
