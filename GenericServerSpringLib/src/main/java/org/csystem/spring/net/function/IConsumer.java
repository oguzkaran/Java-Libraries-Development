package org.csystem.spring.net.function;

/**
 * A functional generic interface that represents an operation which accepts a single input argument
 * and may throw an exception.
 *
 * <p>This interface is similar to {@link java.util.function.Consumer}, but it allows the
 * {@code accept} method to throw a checked exception.</p>
 *
 * @param <T> the type of the input to the operation
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */

@FunctionalInterface
public interface IConsumer<T> {

    /**
     * Performs the operation on the given argument.
     *
     * @param t the input argument
     * @throws Exception if an error occurs during the operation
     */
    void accept(T t) throws Exception;
}
