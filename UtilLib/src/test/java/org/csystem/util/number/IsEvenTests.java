package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsEvenTests {

    @Test
    public void isEvenTest()
    {
        assertTrue(NumberUtil.isEven(0));
        assertTrue(NumberUtil.isEven(82));
        assertTrue(NumberUtil.isEven(46));
        assertTrue(NumberUtil.isEven(60));
        assertTrue(NumberUtil.isEven(84));
        assertFalse(NumberUtil.isEven(77));
        assertFalse(NumberUtil.isEven(39));
        assertFalse(NumberUtil.isEven(51));
        assertFalse(NumberUtil.isEven(-1));
        assertFalse(NumberUtil.isEven(-97));
    }
}
