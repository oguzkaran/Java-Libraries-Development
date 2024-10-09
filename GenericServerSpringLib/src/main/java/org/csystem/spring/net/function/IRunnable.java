package org.csystem.spring.net.function;

/**
 * A functional generic interface that represents a block of code that can be executed
 * and may throw an exception.
 *
 * <p>This interface is similar to {@link java.lang.Runnable}, but it allows the
 * {@code run} method to throw a checked exception.</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */

@FunctionalInterface
public interface IRunnable {

    /**
     * Executes a block of code.
     *
     * @throws Exception if an error occurs during execution
     */
    void run() throws Exception;
}
