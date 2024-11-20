package org.csystem.util.data.error;

/**
 * A functional interface that acts as a supplier of results. This interface can
 * be used as the assignment target for a lambda expression or method reference.
 * <p>
 * This supplier can throw an {@link Exception} when producing the result.
 *
 * @param <R> the type of the result supplied by this interface.
 */

@FunctionalInterface
public interface ISupplier<R> {
    /**
     * Gets a result.
     *
     * @return a result of type R
     * @throws Exception if unable to get the result
     */
    R get() throws Exception;
}
