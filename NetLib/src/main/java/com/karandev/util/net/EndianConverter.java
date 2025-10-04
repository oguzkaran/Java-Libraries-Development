package com.karandev.util.net;

/**
 * Utility class for converting numeric values between little-endian and big-endian byte orders.
 * <p>Provides static methods to convert short, int, and long values to and from different endianness formats.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public final class EndianConverter {
    /**
     * Private constructor to prevent instantiation.
     */
    private EndianConverter()
    {
    }

    /**
     * Converts a short value to little-endian byte order.
     *
     * @param value the short value to convert.
     * @return the value in little-endian byte order.
     */
    public static short toLittleEndian(short value)
    {
        return BitConverter.toLittleEndian(value);
    }

    /**
     * Converts a short value to big-endian byte order.
     *
     * @param value the short value to convert.
     * @return the value in big-endian byte order.
     */
    public static short toBigEndian(short value)
    {
        return BitConverter.toBigEndian(value);
    }

    /**
     * Converts an int value to little-endian byte order.
     *
     * @param value the int value to convert.
     * @return the value in little-endian byte order.
     */
    public static int toLittleEndian(int value)
    {
        return BitConverter.toLittleEndian(value);
    }

    /**
     * Converts an int value to big-endian byte order.
     *
     * @param value the int value to convert.
     * @return the value in big-endian byte order.
     */
    public static int toBigEndian(int value)
    {
        return BitConverter.toBigEndian(value);
    }

    /**
     * Converts a long value to little-endian byte order.
     *
     * @param value the long value to convert.
     * @return the value in little-endian byte order.
     */
    public static long toLittleEndian(long value)
    {
        return BitConverter.toLittleEndian(value);
    }

    /**
     * Converts a long value to big-endian byte order.
     *
     * @param value the long value to convert.
     * @return the value in big-endian byte order.
     */
    public static long toBigEndian(long value)
    {
        return BitConverter.toBigEndian(value);
    }
}
