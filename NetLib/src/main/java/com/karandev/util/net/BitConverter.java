package com.karandev.util.net;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for byte operations and endianness conversions with built-in types.
 * <p>Provides static methods for allocating and wrapping byte buffers, and for converting short, int, long, and other primitive values between different byte orders and byte arrays.</p>
 * <p>Includes methods for converting between strings and byte arrays using various charsets, and for handling arrays of primitive types.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
final class BitConverter {
	/**
	 * Allocates a new ByteBuffer with the specified capacity.
	 * @param capacity the buffer capacity in bytes
	 * @return a new ByteBuffer
	 */
	private static ByteBuffer allocate(int capacity)
	{
		return ByteBuffer.allocate(capacity);
	}

	/**
	 * Wraps a byte array into a ByteBuffer with the specified offset and length.
	 * @param data the byte array
	 * @param offset the starting offset
	 * @param length the number of bytes to wrap
	 * @return a ByteBuffer wrapping the specified bytes
	 */
	private static ByteBuffer wrap(byte [] data, int offset, int length)
	{
		return ByteBuffer.wrap(data, offset, length);
	}

	/**
	 * Changes the endianness of a short value.
	 * @param value the value to convert
	 * @param byteOrder the target byte order
	 * @return the value in the specified byte order
	 */
	private static short changeEndian(short value,  ByteOrder byteOrder)
	{
		return allocate(2).putShort(value).order(byteOrder).getShort(0);
	}

	/**
	 * Changes the endianness of an int value.
	 * @param value the value to convert
	 * @param byteOrder the target byte order
	 * @return the value in the specified byte order
	 */
	private static int changeEndian(int value, ByteOrder byteOrder)
	{
		return allocate(4).putInt(value).order(byteOrder).getInt(0);
	}

	/**
	 * Changes the endianness of a long value.
	 * @param value the value to convert
	 * @param byteOrder the target byte order
	 * @return the value in the specified byte order
	 */
	private static long changeEndian(long value, ByteOrder byteOrder)
	{
		return allocate(8).putLong(value).order(byteOrder).getLong(0);
	}

	/**
	 * Private constructor to prevent instantiation.
	 */
	private BitConverter()
	{
	}

	/**
	 * Converts a String to a byte array using UTF-8 encoding.
	 * @param str the string to convert
	 * @return the byte array representation
	 */
	public static byte [] getBytes(String str)
	{
		return getBytes(str, StandardCharsets.UTF_8);
	}

	/**
	 * Converts a String to a byte array using the specified charset.
	 * @param str the string to convert
	 * @param charset the charset to use
	 * @return the byte array representation
	 */
	public static byte [] getBytes(String str, Charset charset)
	{
		return str.getBytes(charset);
	}

	/**
	 * Converts a byte value to a single-element byte array.
	 * @param value the byte value
	 * @return a byte array containing the value
	 */
	public static byte [] getBytes(byte value)
	{
		return new byte [] {value};
	}

	/**
	 * Converts a short value to a byte array.
	 * @param value the short value
	 * @return a byte array containing the value
	 */
	public static byte[] getBytes(short value)
	{
		return allocate(2).putShort(value).array();
	}

	/**
	 * Converts an int value to a byte array.
	 * @param value the int value
	 * @return a byte array containing the value
	 */
	public static byte[] getBytes(int value)
	{
		return allocate(4).putInt(value).array();
	}

	/**
	 * Converts a long value to a byte array.
	 * @param value the long value
	 * @return a byte array containing the value
	 */
	public static byte[] getBytes(long value)
	{
		return allocate(8).putLong(value).array();
	}

	/**
	 * Converts a char value to a byte array.
	 * @param value the char value
	 * @return a byte array containing the value
	 */
	public static byte[] getBytes(char value)
	{
		return allocate(2).putChar(value).array();
	}

	/**
	 * Converts a double value to a byte array.
	 * @param value the double value
	 * @return a byte array containing the value
	 */
	public static byte[] getBytes(double value)
	{
		return allocate(8).putDouble(value).array();
	}

	/**
	 * Converts a float value to a byte array.
	 * @param value the float value
	 * @return a byte array containing the value
	 */
	public static byte[] getBytes(float value)
	{
		return allocate(4).putFloat(value).array();
	}

	/**
	 * Converts a boolean value to a byte array.
	 * @param value the boolean value
	 * @return a byte array containing the value
	 */
	public static byte[] getBytes(boolean value)
	{
		return new byte[] {(byte)(value ? 1 : 0)};
	}

	/**
	 * Converts an array of byte values to a byte array.
	 * @param bytes the byte values
	 * @return a byte array containing the values
	 */
	public static byte [] getBytes(byte...bytes)
	{
		ByteBuffer bb = allocate(bytes.length * Byte.BYTES);

		for (byte val : bytes)
			bb.put(val);

		return bb.array();
	}

	/**
	 * Converts an array of short values to a byte array.
	 * @param shorts the short values
	 * @return a byte array containing the values
	 */
	public static byte [] getBytes(short...shorts)
	{
		ByteBuffer bb = allocate(shorts.length * Short.BYTES);

		for (short val : shorts)
			bb.putShort(val);

		return bb.array();
	}

	/**
	 * Converts an array of int values to a byte array.
	 * @param ints the int values
	 * @return a byte array containing the values
	 */
	public static byte [] getBytes(int...ints)
	{
		ByteBuffer bb = allocate(ints.length * Integer.BYTES);

		for (int val : ints)
			bb.putInt(val);

		return bb.array();
	}

	/**
	 * Converts an array of long values to a byte array.
	 * @param longs the long values
	 * @return a byte array containing the values
	 */
	public static byte [] getBytes(long...longs)
	{
		ByteBuffer bb = allocate(longs.length * Long.BYTES);

		for (long val : longs)
			bb.putLong(val);

		return bb.array();
	}

	/**
	 * Converts an array of char values to a byte array.
	 * @param chars the char values
	 * @return a byte array containing the values
	 */
	public static byte [] getBytes(char...chars)
	{
		ByteBuffer bb = allocate(chars.length * Character.BYTES);

		for (char val : chars)
			bb.putChar(val);

		return bb.array();
	}

	/**
	 * Converts an array of double values to a byte array.
	 * @param doubles the double values
	 * @return a byte array containing the values
	 */
	public static byte [] getBytes(double...doubles)
	{
		ByteBuffer bb = allocate(doubles.length * Double.BYTES);

		for (double val : doubles)
			bb.putDouble(val);

		return bb.array();
	}

	/**
	 * Converts an array of float values to a byte array.
	 * @param floats the float values
	 * @return a byte array containing the values
	 */
	public static byte [] getBytes(float...floats)
	{
		ByteBuffer bb = allocate(floats.length * Float.BYTES);

		for (float val : floats)
			bb.putFloat(val);

		return bb.array();
	}

	/**
	 * Converts an array of boolean values to a byte array.
	 * @param booleans the boolean values
	 * @return a byte array containing the values
	 */
	public static byte [] getBytes(boolean...booleans)
	{
		byte [] data = new byte[booleans.length];

		for (int i = 0; i < data.length; ++i)
			data[i] = (byte)(booleans[i] ? 1 : 0);

		return data;
	}

	/**
	 * Converts a byte array to a String using UTF-8 encoding.
	 * @param data the byte array
	 * @return the string representation
	 */
	public static String toString(byte [] data)
	{
		return toString(data, StandardCharsets.UTF_8);
	}

	/**
	 * Converts a byte array to a String using the specified charset.
	 * @param data the byte array
	 * @param charset the charset to use
	 * @return the string representation
	 */
	public static String toString(byte [] data, Charset charset)
	{
		return toString(data, 0, data.length, charset);
	}

	/**
	 * Converts a portion of a byte array to a String using UTF-8 encoding.
	 * @param data the byte array
	 * @param offset the starting offset
	 * @param length the number of bytes to convert
	 * @return the string representation
	 */
	public static String toString(byte [] data, int offset, int length)
	{
		return toString(data, offset, length, StandardCharsets.UTF_8);
	}

	/**
	 * Converts a portion of a byte array to a String using the specified charset.
	 * @param data the byte array
	 * @param offset the starting offset
	 * @param length the number of bytes to convert
	 * @param charset the charset to use
	 * @return the string representation
	 */
	public static String toString(byte [] data, int offset, int length, Charset charset)
	{
		return new String(data, offset, length, charset);
	}

	/**
	 * Converts a byte array to a byte value.
	 * @param data the byte array
	 * @return the byte value
	 */
	public static byte toByte(byte [] data)
	{
		return toByte(data, 0);
	}

	/**
	 * Converts a portion of a byte array to a byte value.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @return the byte value
	 */
	public static byte toByte(byte [] data, int startIndex)
	{
		return data[startIndex];
	}

	/**
	 * Converts a byte array to a short value.
	 * @param data the byte array
	 * @return the short value
	 */
	public static short toShort(byte [] data)
	{
		return toShort(data, 0);
	}

	/**
	 * Converts a portion of a byte array to a short value.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @return the short value
	 */
	public static short toShort(byte [] data, int startIndex)
	{
		return wrap(data, startIndex, 2).getShort();
	}

	/**
	 * Converts a byte array to an int value.
	 * @param data the byte array
	 * @return the int value
	 */
	public static int toInt(byte [] data)
	{
		return toInt(data, 0);
	}

	/**
	 * Converts a portion of a byte array to an int value.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @return the int value
	 */
	public static int toInt(byte [] data, int startIndex)
	{
		return wrap(data, startIndex, 4).getInt();
	}

	/**
	 * Converts a byte array to a long value.
	 * @param data the byte array
	 * @return the long value
	 */
	public static long toLong(byte [] data)
	{
		return toLong(data, 0);
	}

	/**
	 * Converts a portion of a byte array to a long value.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @return the long value
	 */
	public static long toLong(byte [] data, int startIndex)
	{
		return wrap(data, startIndex, 8).getLong();
	}

	/**
	 * Converts a byte array to a char value.
	 * @param data the byte array
	 * @return the char value
	 */
	public static char toChar(byte [] data)
	{
		return toChar(data, 0);
	}

	/**
	 * Converts a portion of a byte array to a char value.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @return the char value
	 */
	public static char toChar(byte [] data, int startIndex)
	{
		return wrap(data, startIndex, 2).getChar();
	}

	/**
	 * Converts a byte array to a double value.
	 * @param data the byte array
	 * @return the double value
	 */
	public static double toDouble(byte [] data)
	{
		return toDouble(data, 0);
	}

	/**
	 * Converts a portion of a byte array to a double value.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @return the double value
	 */
	public static double toDouble(byte [] data, int startIndex)
	{
		return wrap(data, startIndex, 8).getDouble();
	}

	/**
	 * Converts a byte array to a float value.
	 * @param data the byte array
	 * @return the float value
	 */
	public static float toFloat(byte [] data)
	{
		return toFloat(data, 0);
	}

	/**
	 * Converts a portion of a byte array to a float value.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @return the float value
	 */
	public static float toFloat(byte [] data, int startIndex)
	{
		return wrap(data, startIndex, 4).getFloat();
	}

	/**
	 * Converts a byte array to a boolean value.
	 * @param data the byte array
	 * @return the boolean value
	 */
	public static boolean toBoolean(byte [] data)
	{
		return toBoolean(data, 0);
	}

	/**
	 * Converts a portion of a byte array to a boolean value.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @return the boolean value
	 */
	public static boolean toBoolean(byte [] data, int startIndex)
	{
		return data[startIndex] != 0;
	}

	/**
	 * Extracts a byte array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @param count the number of bytes to extract
	 * @return the extracted byte array
	 */
	public static byte [] toByteArray(byte [] data, int startIndex, int count)
	{
		byte [] result = new byte[count];

		for (int i = 0, idx = startIndex; i < count; ++i, idx += Byte.BYTES)
			result[i] = toByte(data, idx);

		return result;
	}

	/**
	 * Extracts a short array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param count the number of shorts to extract
	 * @return the extracted short array
	 */
	public static short [] toShortArray(byte [] data, int count)
	{
		return toShortArray(data, 0, count);
	}

	/**
	 * Extracts a short array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @param count the number of shorts to extract
	 * @return the extracted short array
	 */
	public static short [] toShortArray(byte [] data, int startIndex, int count)
	{
		short [] result = new short[count];

		for (int i = 0, idx = startIndex; i < count; ++i, idx += Short.BYTES)
			result[i] = toShort(data, idx);

		return result;
	}

	/**
	 * Extracts an int array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param count the number of ints to extract
	 * @return the extracted int array
	 */
	public static int [] toIntArray(byte [] data, int count)
	{
		return toIntArray(data, 0, count);
	}

	/**
	 * Extracts an int array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @param count the number of ints to extract
	 * @return the extracted int array
	 */
	public static int [] toIntArray(byte [] data, int startIndex, int count)
	{
		int [] result = new int[count];

		for (int i = 0, idx = startIndex; i < count; ++i, idx += Integer.BYTES)
			result[i] = toInt(data, idx);

		return result;
	}

	/**
	 * Extracts a long array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param count the number of longs to extract
	 * @return the extracted long array
	 */
	public static long [] toLongArray(byte [] data, int count)
	{
		return toLongArray(data, 0, count);
	}

	/**
	 * Extracts a long array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @param count the number of longs to extract
	 * @return the extracted long array
	 */
	public static long [] toLongArray(byte [] data, int startIndex, int count)
	{
		long [] result = new long[count];

		for (int i = 0, idx = startIndex; i < count; ++i, idx += Long.BYTES)
			result[i] = toLong(data, idx);

		return result;
	}

	/**
	 * Extracts a char array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param count the number of chars to extract
	 * @return the extracted char array
	 */
	public static char [] toCharArray(byte [] data, int count)
	{
		return toCharArray(data, 0, count);
	}

	/**
	 * Extracts a char array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @param count the number of chars to extract
	 * @return the extracted char array
	 */
	public static char [] toCharArray(byte [] data, int startIndex, int count)
	{
		char [] result = new char[count];

		for (int i = 0, idx = startIndex; i < count; ++i, idx += Character.BYTES)
			result[i] = toChar(data, idx);

		return result;
	}

	/**
	 * Extracts a double array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param count the number of doubles to extract
	 * @return the extracted double array
	 */
	public static double [] toDoubleArray(byte [] data, int count)
	{
		return toDoubleArray(data, 0, count);
	}

	/**
	 * Extracts a double array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @param count the number of doubles to extract
	 * @return the extracted double array
	 */
	public static double [] toDoubleArray(byte [] data, int startIndex, int count)
	{
		double [] result = new double[count];

		for (int i = 0, idx = startIndex; i < count; ++i, idx += Double.BYTES)
			result[i] = toDouble(data, idx);

		return result;
	}

	/**
	 * Extracts a float array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param count the number of floats to extract
	 * @return the extracted float array
	 */
	public static float [] toFloatArray(byte [] data, int count)
	{
		return toFloatArray(data, 0, count);
	}

	/**
	 * Extracts a float array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @param count the number of floats to extract
	 * @return the extracted float array
	 */
	public static float [] toFloatArray(byte [] data, int startIndex, int count)
	{
		float [] result = new float[count];

		for (int i = 0, idx = startIndex; i < count; ++i, idx += Float.BYTES)
			result[i] = toFloat(data, idx);

		return result;
	}

	/**
	 * Extracts a boolean array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param count the number of booleans to extract
	 * @return the extracted boolean array
	 */
	public static boolean [] toBooleanArray(byte [] data, int count)
	{
		return toBooleanArray(data, 0, count);
	}

	/**
	 * Extracts a boolean array from the specified portion of a byte array.
	 * @param data the byte array
	 * @param startIndex the starting index
	 * @param count the number of booleans to extract
	 * @return the extracted boolean array
	 */
	public static boolean [] toBooleanArray(byte [] data, int startIndex, int count)
	{
		boolean [] result = new boolean[count];

		for (int i = 0, idx = startIndex; i < count; ++i, ++idx)
			result[i] = toBoolean(data, idx);

		return result;
	}

	/**
	 * Converts a short value to little-endian byte order.
	 * @param value the short value
	 * @return the value in little-endian byte order
	 */
	public static short toLittleEndian(short value)
	{
		return changeEndian(value, ByteOrder.LITTLE_ENDIAN);
	}

	/**
	 * Converts a short value to big-endian byte order.
	 * @param value the short value
	 * @return the value in big-endian byte order
	 */
	public static short toBigEndian(short value)
	{
		return changeEndian(value, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Converts an int value to little-endian byte order.
	 * @param value the int value
	 * @return the value in little-endian byte order
	 */
	public static int toLittleEndian(int value)
	{
		return changeEndian(value, ByteOrder.LITTLE_ENDIAN);
	}

	/**
	 * Converts an int value to big-endian byte order.
	 * @param value the int value
	 * @return the value in big-endian byte order
	 */
	public static int toBigEndian(int value)
	{
		return changeEndian(value, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Converts a long value to little-endian byte order.
	 * @param value the long value
	 * @return the value in little-endian byte order
	 */
	public static long toLittleEndian(long value)
	{
		return changeEndian(value, ByteOrder.LITTLE_ENDIAN);
	}

	/**
	 * Converts a long value to big-endian byte order.
	 * @param value the long value
	 * @return the value in big-endian byte order
	 */
	public static long toBigEndian(long value)
	{
		return changeEndian(value, ByteOrder.BIG_ENDIAN);
	}
}
