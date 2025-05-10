package org.csystem.util.array;

import org.junit.Test;

import static org.csystem.util.array.ArrayUtil.*;
import static org.junit.Assert.*;

public class ArrayUtilTests {

    @Test
    public void givenIntArray_thenIncreaseTheLength_AndCheckEquals()
    {
        var testArray = new int[] { 1, 2, 3, 4, 5 };
        var expectedLength  = 10;

        assertEquals(expectedLength, enlarge(testArray, expectedLength).length);
    }

    @Test
    public void givenTwo2DIntArray_whenTheyContainsSameElements_ThenCheckEquals()
    {
        var testArrayFirst = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        var testArraySecond = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };

        assertTrue(ArrayUtil.equals(testArrayFirst, testArraySecond));
    }

    @Test
    public void givenTwo2DIntArray_whenTheyContainsDifferentElements_ThenCheckEquals()
    {
        var testArrayFirst = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        var testArraySecond = new int[][] { {7, 8, 9}, {1, 2, 3}, {4, 5, 6} };

        assertFalse(ArrayUtil.equals(testArrayFirst, testArraySecond));
    }

    @Test
    public void givenIntArray_whenContainsSameElements_returnsHistogramOfTheElements_AndThenCheckEquals()
    {
        var testArray = new int[] {0, 1, 2, 3, 1, 1, 1, 2, 3, 1 };
        var expectedArray = new int[] { 1, 5, 2, 2, 0, 0, 0, 0, 0, 0};

        assertArrayEquals(expectedArray, getHistogramData(testArray, 9));
    }

    @Test
    public void given2DIntArray_ThenTransposedAndCheckEquals()
    {
        var testArray = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        var expectedArray = new int[][] { {1, 4, 7}, {2, 5, 8}, {3, 6, 9} };

        assertArrayEquals(expectedArray, transposed(testArray));
    }

    @Test
    public void givenIntArray_thenResizeTheLength_AndCheckEquals()
    {
        var testArray = new int[] { 1, 2, 3, 4, 5 };
        var newLength = 10;
        var expectedArray  = new int[] { 1, 2, 3, 4, 5, 0, 0, 0, 0, 0 };

        assertArrayEquals(expectedArray, resize(testArray, newLength));
    }

    @Test
    public void givenIntArray_thenSwapElements_AndCheckEquals()
    {
        var testArray = new int[] { 1, 2, 3, 4, 5 };
        var swapFirstIndex = 0;
        var swapSecondIndex = 1;
        var expectedArray  = new int[] { 2, 1, 3, 4, 5 };

        swap(testArray, swapFirstIndex, swapSecondIndex);
        assertArrayEquals(expectedArray,testArray);
    }

    @Test
    public void givenCharArray_thenSwapElements_AndCheckEquals()
    {
        var testArray = new char[] { '1', '2', '3', '4', '5' };
        var swapFirstIndex = 0;
        var swapSecondIndex = 1;
        var expectedArray  = new char[] { '2', '1', '3', '4', '5' };

        swap(testArray, swapFirstIndex, swapSecondIndex);
        assertArrayEquals(expectedArray,testArray);
    }

    @Test
    public void givenIntArray_thenConcatenateAllElementsAsLongNumber_AndCheckEquals()
    {
        var testArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        var expected = 123456789L;
        assertEquals(expected,toNumber(testArray));
    }
}
