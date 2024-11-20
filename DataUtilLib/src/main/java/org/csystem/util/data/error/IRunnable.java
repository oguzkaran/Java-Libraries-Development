package org.csystem.util.data.error;

/**
 * A functional interface representing a task that can be run and may throw an exception.
 *
 * <p>This interface is similar to {@link Runnable}, but allows the task to throw a checked {@code Exception}.
 * It can be used in lambda expressions or method references where a task needs to be executed and exception handling is required.</p>
 *
 * <p>As a {@code FunctionalInterface}, it can be used as the assignment target for a lambda expression
 * or method reference.</p>
 */

@FunctionalInterface
public interface IRunnable {
    /**
     * Executes a task, potentially throwing an exception.
     *
     * @throws Exception if an error occurs during task execution
     */
    void run() throws Exception;
}
