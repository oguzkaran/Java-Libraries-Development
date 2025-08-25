package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinMaxTests {

    @Test
    public void minTest()
    {
        assertEquals(101, NumberUtil.min(101, 200, 300));
        assertEquals(101, NumberUtil.min(200, 101, 300));
        assertEquals(101, NumberUtil.min(200, 300, 101));
    }

    @Test
    public void maxTest()
    {
        assertEquals(300, NumberUtil.max(101, 200, 300));
        assertEquals(300, NumberUtil.max(200, 300, 101));
        assertEquals(300, NumberUtil.max(300, 101, 200));
    }
}
