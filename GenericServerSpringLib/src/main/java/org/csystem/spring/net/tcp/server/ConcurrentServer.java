package org.csystem.spring.net.tcp.server;

import lombok.extern.slf4j.Slf4j;
import org.csystem.spring.net.constant.Constant;
import org.csystem.spring.net.function.IConsumer;
import org.csystem.spring.net.function.IRunnable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * A server class that simplifies the inter-process communication of the server side
 * with the usage of the Spring Boot framework.
 *
 * <p>This {@code ConcurrentServer} class provides a multithreaded TCP server solution that handles client connections concurrently
 * using a thread pool. It supports Spring's dependency injection, which makes it easier to configure thread pools,
 * server sockets, and port numbers.</p>
 *
 * <p>This class uses the builder pattern for flexible configuration, where you can define the port, backlog,
 * and specific actions to be executed during different server lifecycle phases like initialization, before accepting connections,
 * and handling exceptions.</p>
 *
 * <p>The server can handle multiple clients simultaneously by delegating each client connection to a thread from the
 * provided {@link ExecutorService}.</p>
 *
 * <p>The {@code ConcurrentServer} is designed to be thread-safe, and it is suitable for applications that require
 * multi-client support and asynchronous processing of client requests.</p>
 *
 * @see ServerSocket
 * @see Socket
 * @see ExecutorService
 * @see IConsumer
 * @see IRunnable
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */

@Component(Constant.CONCURRENT_SERVER_BEAN_NAME)
@Scope("prototype")
@Slf4j
public class ConcurrentServer {
    private final ExecutorService m_threadPool;
    private final ServerSocket m_serverSocket;

    @Value("6767")
    private int m_port;

    @Value("512")
    private int m_backlog;

    private IRunnable m_initRunnable;
    private IRunnable m_beforeAcceptRunnable;
    private IConsumer<Socket> m_clientSocketConsumer = s -> {};
    private IConsumer<Throwable> m_serverExceptionConsumer;

    /**
     * Handles an individual client connection.
     *
     * <p>This method is called for each accepted client connection, and the provided {@link IConsumer}
     * is used to process the {@link Socket} instance representing the client connection.</p>
     *
     * @param socket the connected client socket
     */

    private void handleClient(Socket socket)
    {
        try {
            log.info("Client connected via {}:{}", socket.getInetAddress().getHostAddress(), socket.getPort());
            m_clientSocketConsumer.accept(socket);
        }
        catch (Throwable ignore) {
            //...
        }
    }

    /**
     * The main server logic that runs in a dedicated thread.
     *
     * <p>This method initializes the server, binds it to the specified port, and listens for incoming
     * client connections. Each client connection is handled in a separate thread using the configured
     * {@link ExecutorService}.</p>
     */

    private void serverThreadCallback()
    {
        try {
            if (m_initRunnable != null)
                m_initRunnable.run();
            
            m_serverSocket.bind(new InetSocketAddress(m_port), m_backlog);

            while (true) {
                if (m_beforeAcceptRunnable != null)
                    m_beforeAcceptRunnable.run();

                log.info("Server started on port:{}", m_port);

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

    }

    /**
     * Constructs a new {@code ConcurrentServer} with the provided thread pool and server socket.
     *
     * @param threadPool     the thread pool for handling client connections
     * @param serverSocket   the server socket for accepting client connections
     */
    public ConcurrentServer(@Qualifier("org.csystem.spring.net.executorService") ExecutorService threadPool,
                            @Qualifier("org.csystem.spring.net.concurrentServer.serverSocket") ServerSocket serverSocket)
    {
        m_threadPool = threadPool;
        m_serverSocket = serverSocket;
    }

    /**
     * Sets the port for the server to listen on.
     *
     * @param port the port number
     * @return the current {@code ConcurrentServer} instance for chaining
     */

    public ConcurrentServer setPort(int port)
    {
        m_port = port;

        return this;
    }

    /**
     * Sets the backlog (the maximum number of pending connections).
     *
     * @param backlog the backlog size
     * @return the current {@code ConcurrentServer} instance for chaining
     */

    public ConcurrentServer setBacklog(int backlog)
    {
        m_backlog = backlog;

        return this;
    }

    /**
     * Sets the runnable to be executed during server initialization.
     *
     * @param runnable the initialization runnable
     * @return the current {@code ConcurrentServer} instance for chaining
     */

    public ConcurrentServer setInitRunnable(IRunnable runnable)
    {
        m_initRunnable = runnable;

        return this;
    }

    /**
     * Sets the runnable to be executed before accepting each client connection.
     *
     * @param runnable the pre-accept runnable
     * @return the current {@code ConcurrentServer} instance for chaining
     */

    public ConcurrentServer setBeforeAcceptRunnable(IRunnable runnable)
    {
        m_beforeAcceptRunnable = runnable;

        return this;
    }

    /**
     * Sets the consumer that handles client sockets.
     *
     * @param clientSocketConsumer the consumer for handling client sockets
     * @return the current {@code ConcurrentServer} instance for chaining
     */

    public ConcurrentServer setClientSocketConsumer(IConsumer<Socket> clientSocketConsumer)
    {
        m_clientSocketConsumer = clientSocketConsumer;

        return this;
    }

    /**
     * Sets the consumer to handle server-side exceptions.
     *
     * @param serverExceptionConsumer the consumer for handling exceptions
     * @return the current {@code ConcurrentServer} instance for chaining
     */

    public ConcurrentServer setServerExceptionConsumer(IConsumer<Throwable> serverExceptionConsumer)
    {
        m_serverExceptionConsumer = serverExceptionConsumer;

        return this;
    }

    /**
     * <p>Initiates the server logic in a separate non-daemon thread, allowing it to accept
     * and handle client connections concurrently.</p>
     *
     * <p>If the port and backlog values are not customized, the server will start on the
     * default port 6767 with a backlog of 512.</p>
     *
     */

    public void start()
    {
        m_threadPool.execute(this::serverThreadCallback);
    }

    /**
     * Stops the server by closing the {@link ServerSocket}.
     *
     * <p>Once stopped, the server will no longer accept client connections, and any ongoing
     * connections will be handled by the existing threads.</p>
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
