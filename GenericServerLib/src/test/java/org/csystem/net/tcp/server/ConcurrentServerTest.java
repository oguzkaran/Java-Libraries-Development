package org.csystem.net.tcp.server;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConcurrentServerTest {
    private ConcurrentServer m_concurrentServer;
    private final int DEFAULT_PORT = 6767;

    @BeforeEach
    void setUp() throws IOException
    {
        m_concurrentServer = ConcurrentServer.builder().setPort(DEFAULT_PORT)
                .setBacklog(100)
                .setInitRunnable(() -> System.out.println("Server initialized"))
                .setBeforeAcceptRunnable(() -> System.out.println("Before accept"))
                .setClientSocketConsumer(socket -> {
            try {
                socket.getOutputStream().write("Hello Client".getBytes());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).setServerExceptionConsumer(Throwable::printStackTrace).build();
    }

    @Test
    @Order(1)
    void testServerStopAndStop() throws IOException
    {
        m_concurrentServer.start();
        assertDoesNotThrow(() -> m_concurrentServer.stop());
    }

    @Test
    @Order(2)
    void testClientHandling() throws IOException, InterruptedException
    {
        m_concurrentServer.start();

        ExecutorService clientExecutor = Executors.newSingleThreadExecutor();
        clientExecutor.submit(() -> {
            try (Socket clientSocket = new Socket("localhost", DEFAULT_PORT)) {
                byte[] response = new byte[100];
                int bytesRead = clientSocket.getInputStream().read(response);

                var stringResponse = new String(response, 0, bytesRead);
                System.out.println(stringResponse);

                assertEquals("Hello Client", stringResponse);
            }
            catch (IOException e) {
                fail("Client connection failed: " + e.getMessage());
            }
        });

        Thread.sleep(1000);

        assertDoesNotThrow(() -> m_concurrentServer.stop());
        clientExecutor.shutdown();
        assertTrue(clientExecutor.awaitTermination(2, java.util.concurrent.TimeUnit.SECONDS));
    }

    @Test
    @Order(3)
    void testMultipleClientConnections() throws IOException, InterruptedException
    {
        m_concurrentServer.start();

        int clientCount = 5;
        ExecutorService clientExecutor = Executors.newFixedThreadPool(clientCount);
        CountDownLatch latch = new CountDownLatch(clientCount);

        for (int i = 0; i < clientCount; i++) {
            clientExecutor.submit(() -> {
                try (Socket clientSocket = new Socket("localhost", DEFAULT_PORT)) {
                    byte[] response = new byte[100];
                    int bytesRead = clientSocket.getInputStream().read(response);

                    var stringResponse = new String(response, 0, bytesRead);
                    System.out.println("Response from server: " + stringResponse);

                    assertEquals("Hello Client", stringResponse);
                    latch.countDown();
                }
                catch (IOException e) {
                    fail("Client connection failed: " + e.getMessage());
                }
            });
        }

        latch.await(5, TimeUnit.SECONDS);
        clientExecutor.shutdown();
        assertTrue(clientExecutor.awaitTermination(2, TimeUnit.SECONDS));
        assertDoesNotThrow(() -> m_concurrentServer.stop());
    }

    @Test
    @Order(4)
    void testServerExceptionHandling() throws IOException
    {
        m_concurrentServer = ConcurrentServer.builder().setPort(DEFAULT_PORT).setClientSocketConsumer(socket -> {
            throw new IOException("Simulated exception");
        }).setServerExceptionConsumer(ex -> assertInstanceOf(IOException.class, ex)).build();

        m_concurrentServer.start();

        try (Socket clientSocket = new Socket("localhost", DEFAULT_PORT)) {
            clientSocket.getOutputStream().write("Test".getBytes());
        }
        catch (IOException e) {
            //..
        }

        assertDoesNotThrow(() -> m_concurrentServer.stop());
    }

    @Test
    @Order(5)
    void testClientConnectionTimeout() throws IOException, InterruptedException
    {
        m_concurrentServer = ConcurrentServer.builder().setPort(DEFAULT_PORT).setClientSocketConsumer(socket -> {
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).build();

        m_concurrentServer.start();

        ExecutorService clientExecutor = Executors.newSingleThreadExecutor();
        clientExecutor.submit(() -> {
            try (Socket clientSocket = new Socket("localhost", DEFAULT_PORT)) {
                clientSocket.setSoTimeout(1000);
                byte[] response = new byte[100];
                System.out.println(clientSocket.getInputStream().read(response));
            }
            catch (IOException e) {
                assertInstanceOf(SocketTimeoutException.class, e);
            }
        });

        clientExecutor.shutdown();
        assertTrue(clientExecutor.awaitTermination(6, TimeUnit.SECONDS));
        assertDoesNotThrow(() -> m_concurrentServer.stop());
    }

    @Test
    @Order(6)
    void testServerRejectionAfterShutdown() throws IOException, InterruptedException
    {
        m_concurrentServer.start();
        assertDoesNotThrow(() -> m_concurrentServer.stop());


        try (Socket clientSocket = new Socket("localhost", DEFAULT_PORT)) {
            System.err.println("Connection should be rejected as server is stopped");
        }
        catch (IOException e) {
            assertInstanceOf(ConnectException.class, e);
        }
    }

}
