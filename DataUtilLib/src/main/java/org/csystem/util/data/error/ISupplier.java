package org.csystem.util.data.error;

/**
 * A functional interface that represents a supplier of results.
 * This interface has a single abstract method {@code get()} that supplies a result of type {@code R}.
 * The method can throw an {@code Exception} if an error occurs during the result generation.
 * @param <R> the type of result supplied by this supplier
 * @see Exception
 * @author Oğuz Karan
 * @version 3.3.0
 */

@FunctionalInterface
public interface ISupplier<R> {
    R get() throws Exception;
}
