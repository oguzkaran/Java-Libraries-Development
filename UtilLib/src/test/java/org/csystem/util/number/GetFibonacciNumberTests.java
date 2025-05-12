package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GetFibonacciNumberTests {

    @Test
    public void getFibonacciNumberTest()
    {
        assertEquals(0, NumberUtil.getFibonacciNumber(1));
        assertEquals(1, NumberUtil.getFibonacciNumber(2));
        assertEquals(1, NumberUtil.getFibonacciNumber(3));
        assertEquals(2, NumberUtil.getFibonacciNumber(4));
        assertEquals(3, NumberUtil.getFibonacciNumber(5));
        assertEquals(5, NumberUtil.getFibonacciNumber(6));
        assertEquals(8, NumberUtil.getFibonacciNumber(7));
        assertEquals(13, NumberUtil.getFibonacciNumber(8));
        assertEquals(21, NumberUtil.getFibonacciNumber(9));
        assertEquals(34, NumberUtil.getFibonacciNumber(10));
    }

    @Test
    public void getFibonacciNumbers()
    {
        int [] test1 = {0, 1, 1, 2, 3, 5, 8};
        int [] test2 = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
        int [] test3 = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987};

        assertArrayEquals(test1, NumberUtil.getFibonacciNumbers(7));
        assertArrayEquals(test2, NumberUtil.getFibonacciNumbers(10));
        assertArrayEquals(test3, NumberUtil.getFibonacciNumbers(17));
    }

    @Test
    public void getNextFibonacciNumber()
    {
        assertEquals(1, NumberUtil.getNextFibonacciNumber(0));
        assertEquals(2, NumberUtil.getNextFibonacciNumber(1));
        assertEquals(89, NumberUtil.getNextFibonacciNumber(70));
        assertEquals(144, NumberUtil.getNextFibonacciNumber(140));
        assertEquals(987, NumberUtil.getNextFibonacciNumber(800));
    }
}
