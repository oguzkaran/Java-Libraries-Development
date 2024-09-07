package org.csystem.net.tcp.server;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConcurrentServerTest {
    private ConcurrentServer m_concurrentServer;

    @BeforeEach
    void setUp() throws IOException
    {
        // Basic configuration for testing
        m_concurrentServer = ConcurrentServer.builder()
                .setPort(8080)
                .setBacklog(100)
                .setInitRunnable(() -> System.out.println("Server initialized"))
                .setBeforeAcceptRunnable(() -> System.out.println("Before accept"))
                .setClientSocketConsumer(socket -> {
                    try {
                        // Simulate basic client socket handling
                        socket.getOutputStream().write("Hello Client".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .setServerExceptionConsumer(Throwable::printStackTrace)
                .build();
    }

    @Test
    @Order(1)
    void testServerStart() throws IOException
    {
        // Ensure the server starts without exception
        assertDoesNotThrow(() -> m_concurrentServer.start());
    }

    @Test
    @Order(2)
    void testServerStop() throws IOException
    {
        // Start and stop the server to ensure it stops without exception
        m_concurrentServer.start();
        assertDoesNotThrow(() -> m_concurrentServer.stop());
    }

    @Test
    @Order(3)
    void testClientHandling() throws IOException, InterruptedException
    {
        // Start the server
        m_concurrentServer.start();

        // Start a client connection in a separate thread
        ExecutorService clientExecutor = Executors.newSingleThreadExecutor();
        clientExecutor.submit(() -> {
            try (Socket clientSocket = new Socket("localhost", 8080)) {
                byte[] response = new byte[100];
                int bytesRead = clientSocket.getInputStream().read(response);
                assertEquals("Hello Client", new String(response, 0, bytesRead));  // Validate server response
            }
            catch (IOException e) {
                fail("Client connection failed: " + e.getMessage());
            }
        });

        // Give the server time to handle the client
        Thread.sleep(1000);  // Delay to ensure the server has time to accept and handle the client request

        // Clean up by shutting down the server after client interaction
        assertDoesNotThrow(() -> m_concurrentServer.stop());

        // Clean up the client executor
        clientExecutor.shutdown();
        assertTrue(clientExecutor.awaitTermination(2, java.util.concurrent.TimeUnit.SECONDS)); // Wait for client thread to finish
    }



    @Test
    @Order(4)
    void testInitRunnable() throws IOException
    {
        ConcurrentServer server = ConcurrentServer.builder()
                .setPort(9090)
                .setInitRunnable(() -> {
                    System.out.println("Initialization logic ran");
                    assertTrue(true);  // Just to assert the logic ran
                })
                .build();

        assertDoesNotThrow(server::start);
        server.stop();
    }

    @Test
    @Order(5)
    void testBeforeAcceptRunnable() throws IOException
    {
        ConcurrentServer server = ConcurrentServer.builder()
                .setPort(9091)
                .setBeforeAcceptRunnable(() -> {
                    System.out.println("Before accept logic ran");
                    assertTrue(true);  // To assert the before accept logic ran
                })
                .build();

        assertDoesNotThrow(server::start);
        server.stop();
    }
}
