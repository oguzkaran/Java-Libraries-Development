package org.csystem.util.data.error;

/**
 * A functional interface that represents a runnable of results.
 * This interface has a single abstract method {@code run()} that runnable has not a result.
 * The method can throw an {@code Exception} if an error occurs during the result generation.
 * @see Exception
 * @author Oğuz Karan
 * @version 3.3.0
 *
 */

@FunctionalInterface
public interface IRunnable {
    void run() throws Exception;
}
