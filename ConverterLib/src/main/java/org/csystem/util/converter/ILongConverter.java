package org.csystem.util.converter;

/**
 * Converter functional interface for converting a long value to another type.
 *
 * @param <R> the result type
 * @author JavaApp2-Jan-2024 Group
 * @version 2.0.0
 */
@FunctionalInterface
public interface ILongConverter<R> {
    /**
     * Converts a long value to type R.
     *
     * @param val the long value to convert
     * @return the converted value
     * @throws Exception if conversion fails
     */
    R convert(long val) throws Exception;

    /**
     * Converts an object of type R back to a long value.
     * By default, this operation is not supported and throws an exception.
     *
     * @param r the object to convert back
     * @return the converted long value
     * @throws Exception if conversion fails or not supported
     */
    default long convertBack(R r) throws Exception
    {
        throw new UnsupportedOperationException("convertBack not supported");
    }
}
