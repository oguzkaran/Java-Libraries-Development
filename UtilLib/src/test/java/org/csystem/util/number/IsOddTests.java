package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsOddTests {

    @Test
    public void isOddTest()
    {
        assertTrue(NumberUtil.isOdd(1));
        assertTrue(NumberUtil.isOdd(3));
        assertTrue(NumberUtil.isOdd(5));
        assertTrue(NumberUtil.isOdd(7));
        assertTrue(NumberUtil.isOdd(9));
        assertFalse(NumberUtil.isOdd(0));
        assertFalse(NumberUtil.isOdd(2));
        assertFalse(NumberUtil.isOdd(4));
        assertFalse(NumberUtil.isOdd(-6));
        assertFalse(NumberUtil.isOdd(-8));
    }
}
