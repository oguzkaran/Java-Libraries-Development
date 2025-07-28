package org.csystem.util.converter;

/**
 * Generic functional converter interface for converting objects of type T to type R.
 *
 * @param <T> the source type
 * @param <R> the result type
 * @author JavaApp2-Jan-2024 Group
 * @version 2.0.0
 */
@FunctionalInterface
public interface IConverter<T, R> {
    /**
     * Converts an object of type T to type R.
     *
     * @param t the object to convert
     * @return the converted object
     * @throws Exception if conversion fails
     */
    R convert(T t) throws Exception;

    /**
     * Converts an object of type R back to type T.
     * By default, this operation is not supported and throws an exception.
     *
     * @param r the object to convert back
     * @return the converted object
     * @throws Exception if conversion fails or not supported
     */
    default T convertBack(R r) throws Exception
    {
        throw new UnsupportedOperationException("convertBack not supported");
    }
}
