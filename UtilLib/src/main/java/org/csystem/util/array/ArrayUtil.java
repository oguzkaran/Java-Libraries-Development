/*----------------------------------------------------------------------
	FILE        : ArrayUtil.java
	AUTHOR      : CSD Java group
	LAST UPDATE : 01.11.2021

	Utility class for array operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.array;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Utility class providing common operations for array manipulation and display.
 * Supports operations on primitive arrays (int, char) and multidimensional arrays.
 */
public final class ArrayUtil {
    private ArrayUtil()
    {
    }

    /**
     * Displays array elements with specified minimum width formatting
     * @param n minimum field width for each element
     * @param a array to display
     */
    public static void display(int n, int... a)
    {
        display(n, a.length, a);
    }

    /**
     * Displays first 'count' elements of array with specified width formatting
     * @param n minimum field width for each element
     * @param count number of elements to display
     * @param a array to display
     */
    public static void display(int n, int count, int... a)
    {
        String fmt = String.format("%%0%dd ", n);

        IntStream.range(0,  count).forEach(i -> System.out.printf(fmt, a[i]));
        System.out.println();
    }

    /**
     * Displays all elements of an integer array with default formatting
     * @param a array to display
     */
    public static void display(int... a)
    {
        display(1, a.length, a);
    }

    /**
     * Displays a 2D integer array with default formatting
     * @param a 2D array to display
     */
    public static void display(int[]...a)
    {
        display(1, a);
    }

    /**
     * Displays a 2D integer array with specified element width
     * @param n minimum field width for each element
     * @param a 2D array to display
     */
    public static void display(int n, int[]...a)
    {
        Stream.of(a).forEach(array -> display(n, array));
    }

    /**
     * Displays a character array
     * @param c character array to display
     */
    public static void display(char...c)
    {
        IntStream.range(0, c.length).forEach(i -> System.out.printf("%c ", c[i]));
        System.out.println();
    }

    /**
     * Displays an array of BigInteger objects
     * @param bigIntegers array of BigIntegers to display
     */
    public static void display(BigInteger...bigIntegers)
    {
        Stream.of(bigIntegers).forEach(System.out::println);
    }

    /**
     * Draws a horizontal histogram from array data
     * @param data array of values to visualize
     * @param n scaling factor for histogram bars
     * @param ch character to use for histogram bars
     */
    public static void drawHistogram(int[] data, int n, char ch)
    {
        OptionalInt optInt = Arrays.stream(data).max();

        if (optInt.isEmpty())
            return;

        int maxVal = optInt.getAsInt();

        IntStream.of(data).forEach(value -> {
            int val = (int) Math.round(value * n / (double) maxVal);

            System.out.println((ch + "").repeat(val));
        });
    }

    /**
     * Creates a new array with increased length, preserving original values
     * @param a original array
     * @param newLength desired length of the new array
     * @return new array with original values plus padding
     */
    public static int[] enlarge(int[] a, int newLength)
    {
        if (newLength <= a.length)
            return a;

        return Arrays.copyOf(a, newLength);
    }

    /**
     * Checks if two 2D arrays are equal in dimensions and content
     * @param a first 2D array
     * @param b second 2D array
     * @return true if arrays are equal, false otherwise
     */
    public static boolean equals(int [][] a, int [][] b)
    {
        if (a.length != b.length)
            return false;

        return IntStream.range(0, a.length).allMatch(i -> Arrays.equals(a[i], b[i]));
    }

    /**
     * Generates histogram frequency counts for values in range [0, n]
     * @param a input array of values
     * @param n upper bound of value range
     * @return array containing counts for each value in range
     */
    public static int[] getHistogramData(int[] a, int n) //[0, n]
    {
        int[] counts = new int[n + 1];

        IntStream.of(a).forEach(val -> ++counts[val]);

        return counts;
    }

    /**
     * Creates a transposed version of a 2D array (rows become columns)
     * @param a 2D array to transpose
     * @return new transposed array
     */
    public static int[][] transposed(int[][] a)
    {
        int row = a.length;
        int col = a[0].length;

        int[][] t = new int[col][row];

        IntStream.range(0, row).forEach(i -> IntStream.range(0, col).forEach(j -> t[j][i] = a[i][j]));

        return t;
    }

    /**
     * Resizes an array while preserving existing values
     * @param a original array
     * @param newLength desired length of the new array
     * @return new array with resized length
     */
    public static int[] resize(int[] a, int newLength)
    {
        if (a.length == newLength)
            return a;

        int[] newArray = new int[newLength];
        int length = Math.min(a.length, newLength);

        System.arraycopy(a, 0, newArray, 0, length);

        return newArray;
    }

    /**
     * Swaps elements at specified indices in an integer array
     * @param a array to modify
     * @param i first index
     * @param k second index
     */
    public static void swap(int [] a, int i, int k)
    {
        int temp = a[i];

        a[i] = a[k];
        a[k] = temp;
    }

    /**
     * Swaps elements at specified indices in a character array
     * @param c array to modify
     * @param i first index
     * @param k second index
     */
    public static void swap(char [] c, int i, int k)
    {
        char temp = c[i];

        c[i] = c[k];
        c[k] = temp;
    }

    /**
     * Converts an array of digits into a single number
     * @param a array of digits (0-9)
     * @return numeric value represented by the digit array
     */
    public static long toNumber(int[] a)
    {
        return LongStream.range(0, a.length).reduce(0, (r, i) -> r + (long) a[(int)i] * (long) Math.pow(10, a.length - 1 - i));
    }
}
