package org.csystem.util.converter;

/**
 * Converter functional interface for converting an int value to another type.
 *
 * @param <R> the result type
 * @author JavaApp2-Jan-2024 Group
 * @version 2.0.0
 */
@FunctionalInterface
public interface IIntConverter<R> {
    /**
     * Converts an int value to type R.
     *
     * @param val the int value to convert
     * @return the converted value
     * @throws Exception if conversion fails
     */
    R convert(int val) throws Exception;

    /**
     * Converts an object of type R back to an int value.
     * By default, this operation is not supported and throws an exception.
     *
     * @param r the object to convert back
     * @return the converted int value
     * @throws Exception if conversion fails or not supported
     */
    default int convertBack(R r) throws Exception
    {
        throw new UnsupportedOperationException("convertBack not supported");
    }
}
