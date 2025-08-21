package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumToStrTests {

    @Test
    public void test()
    {
        assertEquals("eksiikiyüzyetmişaltı", NumberUtil.numberToString(-276));
        assertEquals("eksiellisekiz", NumberUtil.numberToString(-58));
        assertEquals("eksibeş", NumberUtil.numberToString(-5));
        assertEquals("sıfır", NumberUtil.numberToString(0));
        assertEquals("bir", NumberUtil.numberToString(1));
        assertEquals("iki", NumberUtil.numberToString(2));
        assertEquals("on", NumberUtil.numberToString(10));
        assertEquals("onbir", NumberUtil.numberToString(11));
        assertEquals("oniki", NumberUtil.numberToString(12));
        assertEquals("yüzyetmiş", NumberUtil.numberToString(170));
        assertEquals("ikiyüzyetmişaltı", NumberUtil.numberToString(276));
        assertEquals("dokuzyüzdoksandokuz", NumberUtil.numberToString(999));
    }
}
