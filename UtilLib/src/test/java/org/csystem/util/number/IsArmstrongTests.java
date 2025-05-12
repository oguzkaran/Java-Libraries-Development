package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.*;

public class IsArmstrongTests {

    @Test
    public void isArmstrongTest()
    {
        assertFalse(NumberUtil.isArmstrong(-5));
        assertTrue(NumberUtil.isArmstrong(0));
        assertTrue(NumberUtil.isArmstrong(5));
        assertTrue(NumberUtil.isArmstrong(153));
        assertTrue(NumberUtil.isArmstrong(9474));
        assertTrue(NumberUtil.isArmstrong(548834));
        assertTrue(NumberUtil.isArmstrong(9926315));
        assertTrue(NumberUtil.isArmstrong(24678050));
        assertTrue(NumberUtil.isArmstrong(912985153));
    }
}
