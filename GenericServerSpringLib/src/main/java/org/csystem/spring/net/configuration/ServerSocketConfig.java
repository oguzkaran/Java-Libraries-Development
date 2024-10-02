package org.csystem.spring.net.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Configuration class for creating and managing {@link ServerSocket} beans.
 *
 * <p>This class provides two different {@code ServerSocket} configurations:
 * one for creating a default unbound {@link ServerSocket}, and another for creating
 * a {@link ServerSocket} bound to a specific port and backlog.</p>
 *
 * <p>These configurations allow for flexible server socket management, enabling users to create
 * unbound sockets as well as sockets that are directly tied to a port and backlog, which can be
 * configured using external properties.</p>
 *
 * @see ServerSocket
 * @see Configuration
 * @see Bean
 * @see Scope
 * @see Lazy
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */

@Configuration("org.csystem.spring.net.concurrentServer.serverSocket.config")
public class ServerSocketConfig {

    /**
     * Creates a default, unbound {@link ServerSocket}.
     *
     * <p>This method defines a Spring bean that provides a new unbound {@code ServerSocket},
     * which can be bound to a specific port later using the {@code bind()} method. This socket is
     * defined with prototype scope, meaning each time it is requested, a new instance is created.</p>
     *
     * @return a new unbound {@link ServerSocket}.
     * @throws IOException if an I/O error occurs while creating the socket.
     */

    @Bean("org.csystem.spring.net.concurrentServer.serverSocket")
    @Scope("prototype")
    public ServerSocket createDefaultServerSocket() throws IOException
    {
        return new ServerSocket();
    }

    /**
     * Creates a bound {@link ServerSocket} with specified port and backlog values.
     *
     * <p>This method defines a Spring bean that creates a {@link ServerSocket} bound to a specific
     * port and backlog. The port and backlog values are retrieved from external configuration properties,
     * with default values of 6767 for the port and 512 for the backlog if not provided.</p>
     *
     * <p>The bean is marked as {@code @Lazy}, meaning it will only be instantiated when first requested.</p>
     *
     * @param port the port number to bind the server socket to (default is 6767).
     * @param backlog the maximum number of pending connections (default is 512).
     * @return a new {@link ServerSocket} bound to the specified port and backlog.
     * @throws IOException if an I/O error occurs while creating or binding the socket.
     */

    @Bean("org.csystem.spring.net.serverSocket")
    @Lazy
    public ServerSocket createServerSocket(@Value("${org.csystem.spring.net.port:6767}") int port,
                                           @Value("${org.csystem.spring.net.backlog:512}") int backlog) throws IOException
    {
        return new ServerSocket(port, backlog);
    }
}
