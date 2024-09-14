package org.csystem.net.tcp.server;

import org.csystem.net.function.IConsumer;
import org.csystem.net.function.IRunnable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A server that handles multiple client connections concurrently using a thread pool.
 *
 * <p>The {@code ConcurrentServer} class provides a flexible builder-based configuration and can handle client
 * connections on a specified port. It allows users to set various behaviors such as initialization logic,
 * client handling, and exception handling.</p>
 *
 * <p>This class is thread-safe and suitable for multi-client, concurrent applications.</p>
 *
 * @see ServerSocket
 * @see ExecutorService
 *
 * @author JavaApp2-Jan-2024 Group
 */

public class ConcurrentServer {
    private final ExecutorService m_threadPool;
    private ServerSocket m_serverSocket;
    private int m_port = 6767;
    private int m_backlog = 512;
    private IRunnable m_initRunnable;
    private IRunnable m_beforeAcceptRunnable;
    private IConsumer<Socket> m_clientSocketConsumer = s -> {};
    private IConsumer<Throwable> m_serverExceptionConsumer;


    /**
     * A builder for constructing a {@link ConcurrentServer} with custom configurations.
     *
     * <p>This builder allows setting various server options, including the port, backlog,
     * initial runnable, before-accept runnable, and handlers for client socket connections
     * and server exceptions.
     *
     * <p>Example usage:
     * <pre>{@code
     * ConcurrentServer server = ConcurrentServer.builder()
     *         .setPort(8080)
     *         .setBacklog(100)
     *         .setInitRunnable(() -> {
     *             // Initialization logic
     *         })
     *         .setClientSocketConsumer(socket -> {
     *             // Handle client socket
     *         })
     *         .setServerExceptionConsumer(ex -> {
     *             // Handle server exceptions
     *         })
     *         .build();
     * }</pre>
     */
    public static class Builder {
        private final ConcurrentServer m_concurrentServer;

        /**
         * Private constructor for the Builder.
         *
         * <p>To instantiate a {@code Builder}, use the {@link ConcurrentServer#builder()} method.</p>
         */
        private Builder()
        {
            m_concurrentServer = new ConcurrentServer();
        }

        /**
         * Sets the port number for the server.
         *
         * <p>If not set, the default port is 6767.</p>
         *
         * @param port The port number the server should listen on.
         * @return this builder
         */
        public Builder setPort(int port)
        {
            m_concurrentServer.m_port = port;

            return this;
        }

        /**
         * Sets the backlog for the server socket.
         *
         * <p>If not set, the default backlog is 512.
         *
         * @param backlog the maximum number of pending connections
         */
        public Builder setBacklog(int backlog)
        {
            m_concurrentServer.m_backlog = backlog;

            return this;
        }

        /**
         * Sets the initialization logic to be executed when the server starts.
         *
         * @param runnable The initialization logic.
         */
        public Builder setInitRunnable(IRunnable runnable)
        {
            m_concurrentServer.m_initRunnable = runnable;

            return this;
        }

        /**
         * Sets the logic to be executed before each client connection is accepted.
         *
         * @param runnable The pre-accept logic.
         */
        public Builder setBeforeAcceptRunnable(IRunnable runnable)
        {
            m_concurrentServer.m_beforeAcceptRunnable = runnable;

            return this;
        }

        /**
         * Sets the logic for handling client connections.
         *
         * <p>The provided {@link IConsumer} will be called with each accepted {@link Socket}.</p>
         *
         * @param clientSocketConsumer The client handling logic.
         */
        public Builder setClientSocketConsumer(IConsumer<Socket> clientSocketConsumer)
        {
            m_concurrentServer.m_clientSocketConsumer = clientSocketConsumer;

            return this;
        }

        /**
         * Sets the logic for handling server exceptions.
         *
         * @param serverExceptionConsumer The exception handling logic.
         */
        public Builder setServerExceptionConsumer(IConsumer<Throwable> serverExceptionConsumer)
        {
            m_concurrentServer.m_serverExceptionConsumer = serverExceptionConsumer;

            return this;
        }

        /**
         * Builds the {@link ConcurrentServer} instance with the configured options.
         *
         * @return The constructed {@link ConcurrentServer} instance.
         * @throws IOException If an error occurs during server setup.
         */
        public ConcurrentServer build() throws IOException
        {
            return m_concurrentServer;
        }
    }

    /**
     * Private method to handle client socket communication.
     *
     * <p>This method is executed in a separate thread for each connected client and uses
     * the provided {@link IConsumer} to handle the socket interaction.</p>
     *
     * @param socket The client socket.
     */
    private void handleClient(Socket socket)
    {
        try {
            m_clientSocketConsumer.accept(socket);
        }
        catch (Throwable ignore) {
            //...
        }
    }

    /**
     * Private method that defines the server's main execution logic.
     *
     * <p>This method initializes the server and continuously listens for new client connections.
     * It invokes the {@link IRunnable} before each accept, and submits a client handling task to the
     * thread pool for each connection.</p>
     */
    private void serverThreadCallback()
    {
        try {
            if (m_initRunnable != null)
                m_initRunnable.run();

            while (true) {
                if (m_beforeAcceptRunnable != null)
                    m_beforeAcceptRunnable.run();

                var socket = m_serverSocket.accept();

                m_threadPool.execute(() -> handleClient(socket));
            }
        }
        catch (Throwable ex) {
            try {
                m_serverExceptionConsumer.accept(ex);
            }
            catch (Exception ignore) {
                //...
            }
        }
        finally {
            //m_threadPool.shutdown();
        }
    }

    /**
     * Private constructor for {@link ConcurrentServer}.
     *
     * <p>Use the {@link Builder} to create instances of this class.</p>
     */
    private ConcurrentServer()
    {
        m_threadPool = Executors.newCachedThreadPool();
    }

    /**
     * Creates a new {@link Builder} instance for constructing a {@link ConcurrentServer}.
     *
     * @return A new {@link Builder}.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Starts the server, opening the {@link ServerSocket} and accepting client connections.
     *
     * <p>The server will listen on the port specified during construction. Incoming client
     * connections will be processed concurrently in separate threads from the thread pool.
     *
     * <p>If an {@link IOException} occurs during startup, it will be caught and handled
     * by the configured server exception consumer.
     *
     * @throws IOException if the server socket cannot be created or bound to the specified port.
     */
    public void start()
    {
        try {
            m_serverSocket = new ServerSocket(m_port, m_backlog);
            m_threadPool.execute(this::serverThreadCallback);
        }
        catch (IOException ignore) {
            //...
        }
    }

    /**
     * Stops the server.
     *
     * <p>This method closes the {@link ServerSocket}, preventing new connections. It does not
     * interrupt the existing client connections, which continue to be processed.</p>
     *
     * @throws IOException
     */
    public void stop()
    {
        try {
            m_serverSocket.close();
        }
        catch (IOException ignore) {
            //...
        }
    }
}
