package com.karandev.util.net;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Disabled("Run the debug test")
public class TcpGetSocketTest {
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
    public void getSocketTest() throws IOException
    {
        try (var socket = new Socket(HOST, PORT)) {
            var tcp = new TCP(socket);

            Assertions.assertNotNull(tcp.getSocket());
        }
    }

    @AfterEach
    public void tearDown() throws IOException
    {
        m_serverSocket.close();
        m_threadPool.shutdown();
    }
}
