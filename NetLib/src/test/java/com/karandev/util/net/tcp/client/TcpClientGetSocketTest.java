package com.karandev.util.net.tcp.client;

import com.karandev.util.net.TCP;
import com.karandev.util.net.TCPClient;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Disabled("Run the debug test")
public class TcpClientGetSocketTest {
    private static final String HOST = "localhost";
    private static final int PORT = 50500;
    private ExecutorService m_threadPool;
    private ServerSocket m_serverSocket;

    private void serverCallback()
    {
        try {
            m_serverSocket = new ServerSocket(PORT);
            var clientSocket = m_serverSocket.accept();
            var tcp = new TCP(clientSocket);

            System.out.println(tcp.getSocket().toString());
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
    public void getSocketTest()
    {
        try (var tcpClient = new TCPClient(HOST, PORT)) {
            Assertions.assertNotNull(tcpClient.getSocket());
        }
    }

    @AfterEach
    public void tearDown() throws IOException
    {
        m_serverSocket.close();
        m_threadPool.shutdown();
    }
}
