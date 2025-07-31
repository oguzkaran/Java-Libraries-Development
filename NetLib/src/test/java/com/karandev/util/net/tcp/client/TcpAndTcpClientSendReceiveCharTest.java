package com.karandev.util.net.tcp.client;

import com.karandev.util.net.TCP;
import com.karandev.util.net.TCPClient;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class TcpAndTcpClientSendReceiveCharTest {
    private static final String HOST = "localhost";
    private static final int PORT = 50500;
    private static final int SOCKET_TIMEOUT = 1000;
    private static final char SEND_CHAR = 16;
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
            var val = tcp.receiveChar();

            Assertions.assertEquals(SEND_CHAR, val);

            tcp.sendChar(SEND_CHAR);
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
        try (var tcpClient = new TCPClient(HOST, PORT)) {
            tcpClient.sendChar(SEND_CHAR);

            Assertions.assertEquals(SEND_CHAR, tcpClient.receiveChar());
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
