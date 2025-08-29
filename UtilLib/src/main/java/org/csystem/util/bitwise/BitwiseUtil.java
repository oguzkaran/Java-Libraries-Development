package org.csystem.util.bitwise;

/**
 * Utility class providing common bitwise operations for primitive data types.
 * This class includes methods to manipulate, inspect, and format the bits of
 * byte, short, char, int, and long values. It provides capabilities such as setting,
 * clearing, toggling individual bits, counting the number of set or clear bits,
 * retrieving indices of set bits, and generating binary string representations.
 *
 * <p>This class is final and has a private constructor to prevent instantiation.</p>
 *
 * @author CSD Java group
 * @since 15.12.2021
 */

public final class BitwiseUtil {
    private BitwiseUtil()
    {}

    /**
     * Clears the {@code k}th bit of the given integer value.
     */
    public static int clearBit(int val, int k) //k -> [0, 31]
    {
        return val & ~(1 << k);
    }

    /**
     * Clears the {@code k}th bit of the given long value.
     */
    public static long clearBit(long val, int k) //k -> [0, 63]
    {
        return val & ~(1L << k);
    }

    /**
     * Returns the indices of set bits in the given character.
     */
    public static int [] indicesOfSetBits(char ch)
    {
        int [] bitIndices = new int[numberOfSetBits(ch)];
        int index = 0;

        for (int k = 0; k < 16; ++k)
            if ((ch & 1 << k) != 0)
                bitIndices[index++] = k;

        return bitIndices;
    }

    /**
     * Returns the indices of set bits in the given byte.
     */
    public static int [] indicesOfSetBits(byte val)
    {
        int [] bitIndices = new int[numberOfSetBits(val)];
        int index = 0;

        for (int k = 0; k < 8; ++k)
            if ((val & 1 << k) != 0)
                bitIndices[index++] = k;

        return bitIndices;
    }

    /**
     * Returns the indices of set bits in the given short.
     */
    public static int [] indicesOfSetBits(short val)
    {
        int [] bitIndices = new int[numberOfSetBits(val)];
        int index = 0;

        for (int k = 0; k < 16; ++k)
            if ((val & 1 << k) != 0)
                bitIndices[index++] = k;

        return bitIndices;
    }

    /**
     * Returns the indices of set bits in the given integer.
     */
    public static int [] indicesOfSetBits(int val)
    {
        int [] bitIndices = new int[numberOfSetBits(val)];
        int index = 0;

        for (int k = 0; k < 32; ++k)
            if ((val & 1 << k) != 0)
                bitIndices[index++] = k;

        return bitIndices;
    }

    /**
     * Returns the indices of set bits in the given long.
     */
    public static int [] indicesOfSetBits(long val)
    {
        int [] bitIndices = new int[numberOfSetBits(val)];
        int index = 0;

        for (int k = 0; k < 64; ++k)
            if ((val & 1L << k) != 0)
                bitIndices[index++] = k;

        return bitIndices;
    }

    /**
     * Checks if the {@code k}th bit is set in the given integer.
     */
    public static boolean isSet(int val, int k)
    {
        return (val & (1 << k)) != 0;
    }

    /**
     * Checks if the {@code k}th bit is clear in the given integer.
     */
    public static boolean isClear(int val, int k)
    {
        return !isSet(val, k);
    }

    /**
     * Checks if the {@code k}th bit is set in the given long.
     */
    public static boolean isSet(long val, int k)
    {
        return (val & 1L << k) != 0;
    }

    /**
     * Checks if the {@code k}th bit is clear in the given long.
     */
    public static boolean isClear(long val, int k)
    {
        return !isSet(val, k);
    }

    /**
     * Returns the number of clear bits in the given byte.
     */
    public static int numberOfClearBits(byte val)
    {
        return 8 - numberOfSetBits(val);
    }

    /**
     * Returns the number of set bits in the given byte.
     */
    public static int numberOfSetBits(byte val)
    {
        int count = 0;

        for (int k = 0; k < 8; ++k)
            if ((val & 1 << k) != 0)
                ++count;

        return count;
    }

    /**
     * Returns the number of clear bits in the given short.
     */
    public static int numberOfClearBits(short val)
    {
        return 16 - numberOfSetBits(val);
    }

    /**
     * Returns the number of set bits in the given short.
     */
    public static int numberOfSetBits(short val)
    {
        int count = 0;

        for (int k = 0; k < 16; ++k)
            if ((val & 1 << k) != 0)
                ++count;

        return count;
    }

    /**
     * Returns the number of clear bits in the given integer.
     */
    public static int numberOfClearBits(int val)
    {
        return 32 - numberOfSetBits(val);
    }

    /**
     * Returns the number of set bits in the given integer.
     */
    public static int numberOfSetBits(int val)
    {
        int count = 0;

        for (int k = 0; k < 32; ++k)
            if ((val & 1 << k) != 0)
                ++count;

        return count;
    }

    /**
     * Returns the number of clear bits in the given long.
     */
    public static int numberOfClearBits(long val)
    {
        return 64 - numberOfSetBits(val);
    }

    /**
     * Returns the number of set bits in the given long.
     */
    public static int numberOfSetBits(long val)
    {
        int count = 0;

        for (int k = 0; k < 64; ++k)
            if ((val & 1L << k) != 0)
                ++count;

        return count;
    }

    /**
     * Returns the number of clear bits in the given char.
     */
    public static int numberOfClearBits(char val)
    {
        return 16 - numberOfSetBits(val);
    }

    /**
     * Returns the number of set bits in the given char.
     */
    public static int numberOfSetBits(char val)
    {
        int count = 0;

        for (int k = 0; k < 16; ++k)
            if ((val & 1 << k) != 0)
                ++count;

        return count;
    }

    /**
     * Sets the {@code k}th bit of the given integer.
     */
    public static int setBit(int val, int k) //k -> [0, 31]
    {
        return val | 1 << k;
    }

    /**
     * Sets the {@code k}th bit of the given long.
     */
    public static long setBit(long val, int k) //k -> [0, 63]
    {
        return val | 1L << k;
    }

    /**
     * Returns a string representation of the given char in binary format.
     */
    public static String toBitsStr(char ch)
    {
        char [] bits = new char[16];

        for (int k = 15; k >= 0; --k)
            bits[15 - k] = (ch & 1 << k) != 0 ? '1' : '0';

        return String.valueOf(bits);
    }

    /**
     * Returns a string representation of the given byte in binary format.
     */
    public static String toBitsStr(byte val)
    {
        char [] bits = new char[8];

        for (int k = 7; k >= 0; --k)
            bits[7 - k] = (val & 1 << k) != 0 ? '1' : '0';

        return String.valueOf(bits);
    }

    /**
     * Returns a string representation of the given short in binary format.
     */
    public static String toBitsStr(short val)
    {
        char [] bits = new char[16];

        for (int k = 15; k >= 0; --k)
            bits[15 - k] = (val & 1 << k) != 0 ? '1' : '0';

        return String.valueOf(bits);
    }

    /**
     * Returns a string representation of the given int in binary format.
     */
    public static String toBitsStr(int val)
    {
        char [] bits = new char[32];

        for (int k = 31; k >= 0; --k)
            bits[31 - k] = (val & 1 << k) != 0 ? '1' : '0';

        return String.valueOf(bits);
    }

    /**
     * Returns a string representation of the given long in binary format.
     */
    public static String toBitsStr(long val)
    {
        char [] bits = new char[64];

        for (int k = 63; k >= 0; --k)
            bits[63 - k] = (val & 1L << k) != 0 ? '1' : '0';

        return String.valueOf(bits);
    }

    /**
     * Toggles the {@code n}th bit of the given int value.
     */
    public static int toggleBit(int val, int n)
    {
        return val ^ 1 << n;
    }

    /**
     * Toggles the {@code n}th bit of the given long value.
     */
    public static long toggleBit(long val, int n)
    {
        return val ^ 1L << n;
    }
}
