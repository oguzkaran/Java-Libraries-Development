package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GetDigitsTests {

    @Test
    public void getDigitsIntegerTest()
    {
        var value1 = 123456789;
        var value2 = -123456789;
        var value3 = 0;
        var value4 = 1234567890;

        var test1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        var test2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        var test3 = new int[]{0};
        var test4 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

        assertArrayEquals(test1, NumberUtil.getDigits(value1));
        assertArrayEquals(test2, NumberUtil.getDigits(value2));
        assertArrayEquals(test3, NumberUtil.getDigits(value3));
        assertArrayEquals(test4, NumberUtil.getDigits(value4));

    }

    @Test
    public void getDigitsLongTest()
    {
        var value1 = 123456789L;
        var value2 = -123456789L;
        var value3 = 0L;
        var value4 = 1234567890L;

        var test1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        var test2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        var test3 = new int[]{0};
        var test4 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

        assertArrayEquals(test1, NumberUtil.getDigits(value1));
        assertArrayEquals(test2, NumberUtil.getDigits(value2));
        assertArrayEquals(test3, NumberUtil.getDigits(value3));
        assertArrayEquals(test4, NumberUtil.getDigits(value4));

    }

    @Test
    public void getDigitsInTwoTest()
    {
        var value1 = 123456789L;
        var value2 = -123456789L;
        var value3 = 0L;
        var value4 = 1234567890L;

        var test1 = new int[]{1, 23, 45, 67, 89};
        var test2 = new int[]{1, 23, 45, 67, 89};
        var test3 = new int[]{0};
        var test4 = new int[]{12, 34, 56, 78, 90};

        assertArrayEquals(test1, NumberUtil.getDigitsInTwo(value1));
        assertArrayEquals(test2, NumberUtil.getDigitsInTwo(value2));
        assertArrayEquals(test3, NumberUtil.getDigitsInTwo(value3));
        assertArrayEquals(test4, NumberUtil.getDigitsInTwo(value4));

    }

    @Test
    public void getDigitsInThreeTest()
    {
        var value1 = 123456789L;
        var value2 = -123456789L;
        var value3 = 0L;
        var value4 = 1234567890L;

        var test1 = new int[]{123, 456, 789};
        var test2 = new int[]{123, 456, 789};
        var test3 = new int[]{0};
        var test4 = new int[]{1, 234, 567, 890};

        assertArrayEquals(test1, NumberUtil.getDigitsInThree(value1));
        assertArrayEquals(test2, NumberUtil.getDigitsInThree(value2));
        assertArrayEquals(test3, NumberUtil.getDigitsInThree(value3));
        assertArrayEquals(test4, NumberUtil.getDigitsInThree(value4));

    }

    @Test
    public void getDigitsCountIntegerTest()
    {
        var value1 = 123456789;
        var value2 = -123456789;
        var value3 = 0;
        var value4 = 1234567890;

        var tes1 = 9;
        var tes2 = 9;
        var tes3 = 1;
        var tes4 = 10;

        assertEquals(tes1, NumberUtil.getDigitsCount(value1));
        assertEquals(tes2, NumberUtil.getDigitsCount(value2));
        assertEquals(tes3, NumberUtil.getDigitsCount(value3));
        assertEquals(tes4, NumberUtil.getDigitsCount(value4));

    }

    @Test
    public void getDigitsCountLongTest()
    {
        var value1 = 123456789L;
        var value2 = -123456789L;
        var value3 = 0L;
        var value4 = 1234567890L;

        var tes1 = 9;
        var tes2 = 9;
        var tes3 = 1;
        var tes4 = 10;

        assertEquals(tes1, NumberUtil.getDigitsCount(value1));
        assertEquals(tes2, NumberUtil.getDigitsCount(value2));
        assertEquals(tes3, NumberUtil.getDigitsCount(value3));
        assertEquals(tes4, NumberUtil.getDigitsCount(value4));

    }

    @Test
    public void getDigitsSumTest()
    {
        var value1 = 123456789;
        var value2 = -123456789;
        var value3 = 0;
        var value4 = 1234;

        var test1 = 45;
        var test2 = 45;
        var test3 = 0;
        var test4 = 10;

        assertEquals(test1, NumberUtil.getDigitsSum(value1));
        assertEquals(test2, NumberUtil.getDigitsSum(value2));
        assertEquals(test3, NumberUtil.getDigitsSum(value3));
        assertEquals(test4, NumberUtil.getDigitsSum(value4));
    }

}
