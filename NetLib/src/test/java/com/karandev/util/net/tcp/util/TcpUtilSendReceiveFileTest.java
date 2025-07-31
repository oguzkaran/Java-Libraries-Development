package com.karandev.util.net.tcp.util;

import com.karandev.util.net.TcpUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class TcpUtilSendReceiveFileTest {
    private static final String HOST = "localhost";
    private static final int PORT = 50500;
    private static final int SOCKET_TIMEOUT = 1000;
    private static final File SEND_FILE = new File("./sent.txt");
    private static final File RECEIVE_FILE = new File("./received.txt");
    private static final int FILE_LENGTH = 2050;
    private ServerSocket m_serverSocket;
    private ExecutorService m_threadPool;
    private final AtomicReference<Throwable> m_exception = new AtomicReference<>();

    private void serverCallback()
    {
        try {
            m_serverSocket = new ServerSocket(PORT, 1024);
            var clientSocket = m_serverSocket.accept();
            clientSocket.setSoTimeout(SOCKET_TIMEOUT);
            TcpUtil.receiveFile(clientSocket, RECEIVE_FILE);

            Assertions.assertTrue(RECEIVE_FILE.isFile());
            Assertions.assertEquals(FILE_LENGTH, RECEIVE_FILE.length());
        }
        catch (IOException ex) {
            m_exception.set(ex);
        }
    }

    private void writeRandomBytes() throws IOException
    {
        var random = new Random();
        var bytes = new byte[FILE_LENGTH];

        random.nextBytes(bytes);

        try (var fos = new FileOutputStream(SEND_FILE)) {
            fos.write(bytes);
        }
    }

    @BeforeEach
    public void setUp() throws IOException
    {
        m_threadPool = Executors.newSingleThreadExecutor();
        m_threadPool.execute(this::serverCallback);
        writeRandomBytes();
    }

    @Test
    public void test() throws IOException, InterruptedException
    {
        Thread.sleep(100);
        TcpUtil.sendFile(new Socket(HOST, PORT), SEND_FILE, 2048);
    }

    @AfterEach
    public void tearDown() throws IOException
    {
        Assertions.assertNull(m_exception.get());
        m_serverSocket.close();
        m_threadPool.shutdownNow();
        SEND_FILE.deleteOnExit();
        RECEIVE_FILE.deleteOnExit();
    }
}
