package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetReverseTests {

    @Test
    public void getReverseTest()
    {
        int val1 = 1234567890;
        int val2 = -1234567890;
        int val3 = 5940495;

        assertEquals(987654321, NumberUtil.getReverse(val1));
        assertEquals(-987654321, NumberUtil.getReverse(val2));
        assertEquals(5940495, NumberUtil.getReverse(val3));
    }
}
