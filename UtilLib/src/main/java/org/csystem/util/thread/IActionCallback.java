package org.csystem.util.thread;

/**
 * Represents a functional interface for a callback action.
 * This interface is intended to be used with lambda expressions or method references
 * to encapsulate an action that may throw an exception.
 *
 * @author Oğuz Karan
 * @since 13.09.2021
 */

@FunctionalInterface
public interface IActionCallback {
    /**
     * Executes the action defined by the callback.
     *
     * @throws Exception if any error occurs during execution
     */
    void run() throws Exception;
}
