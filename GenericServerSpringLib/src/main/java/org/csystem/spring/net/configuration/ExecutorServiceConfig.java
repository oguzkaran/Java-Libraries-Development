package org.csystem.spring.net.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Configuration class for creating and managing an {@link ExecutorService} bean.
 *
 * <p>This class provides a configuration for an {@code ExecutorService} that creates a thread pool using
 * {@code Executors.newCachedThreadPool()}. The cached thread pool dynamically creates new threads as needed
 * and reuses previously constructed threads when they are available, making it suitable for applications
 * that experience varying levels of concurrency.</p>
 *
 * <p>The created {@link ExecutorService} bean is defined with prototype scope, meaning a new instance will be
 * provided each time it is injected or requested.</p>
 *
 * @see ExecutorService
 * @see Executors
 * @see Configuration
 * @see Bean
 * @see Scope
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */

@Configuration("org.csystem.spring.net.executorService.config")
public class ExecutorServiceConfig {

    /**
     * Creates a new {@link ExecutorService} with a cached thread pool.
     *
     * <p>This method defines a Spring bean that produces a cached thread pool {@link ExecutorService}, which
     * can dynamically allocate threads based on the needs of the application. Threads that are idle for
     * some time are terminated and removed from the pool.</p>
     *
     * @return a new instance of {@link ExecutorService} with a cached thread pool.
     */

    @Bean("org.csystem.spring.net.executorService")
    @Scope("prototype")
    public ExecutorService createExecutorService()
    {
        return Executors.newCachedThreadPool();
    }    
}
