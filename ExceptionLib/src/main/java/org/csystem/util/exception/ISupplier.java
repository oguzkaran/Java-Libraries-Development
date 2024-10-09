package org.csystem.util.exception;

/**
 * A functional interface that represents a supplier of results.
 * This interface has a single abstract method {@code get()} that supplies a result of type {@code R}.
 * The method can throw an {@code Exception} if an error occurs during the result generation.
 * @param <R> the type of result supplied by this supplier
 * @see Exception
 */

@FunctionalInterface
public interface ISupplier<R> {
    R get() throws Exception;
}
