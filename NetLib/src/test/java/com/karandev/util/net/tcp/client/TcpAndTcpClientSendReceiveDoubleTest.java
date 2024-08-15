package com.karandev.util.net.tcp.client;

import com.karandev.util.net.TCP;
import com.karandev.util.net.TCPClient;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Disabled("Run the debug test")
public class TcpAndTcpClientSendReceiveDoubleTest {
    private static final String HOST = "localhost";
    private static final int PORT = 50500;
    private static final int SOCKET_TIMEOUT = 1000;
    private static final double SEND_DOUBLE = 34.5;
    private ServerSocket m_serverSocket;
    private ExecutorService m_threadPool;

    private void serverCallback()
    {
        try {
            m_serverSocket = new ServerSocket(PORT);
            var clientSocket = m_serverSocket.accept();
            clientSocket.setSoTimeout(SOCKET_TIMEOUT);
            var tcp = new TCP(clientSocket);
            var val = tcp.receiveDouble();

            Assertions.assertEquals(SEND_DOUBLE, val);

            tcp.sendDouble(SEND_DOUBLE);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp()
    {
        m_threadPool = Executors.newSingleThreadExecutor();
        m_threadPool.execute(this::serverCallback);
    }

    @Test
    public void test() throws IOException
    {
        try (var tcpClient = new TCPClient(HOST, PORT)) {
            tcpClient.sendDouble(SEND_DOUBLE);

            Assertions.assertEquals(SEND_DOUBLE, tcpClient.receiveDouble());
        }
    }

    @AfterEach
    public void tearDown() throws IOException
    {
        m_serverSocket.close();
        m_threadPool.shutdown();
    }
}
