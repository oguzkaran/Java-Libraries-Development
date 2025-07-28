package org.csystem.util.converter;

/**
 * Converter functional interface for converting a String to another type.
 *
 * @param <R> the result type
 * @author JavaApp2-Jan-2024 Group
 * @version 2.0.0
 */
@FunctionalInterface
public interface IStringConverter<R> extends IConverter<String, R> {
}
