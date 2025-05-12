package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumToStrTests {

    @Test
    public void test()
    {
        assertEquals("eksiikiyüzyetmişaltı", NumberUtil.numToStr(-276));
        assertEquals("eksiellisekiz", NumberUtil.numToStr(-58));
        assertEquals("eksibeş", NumberUtil.numToStr(-5));
        assertEquals("sıfır", NumberUtil.numToStr(0));
        assertEquals("bir", NumberUtil.numToStr(1));
        assertEquals("iki", NumberUtil.numToStr(2));
        assertEquals("on", NumberUtil.numToStr(10));
        assertEquals("onbir", NumberUtil.numToStr(11));
        assertEquals("oniki", NumberUtil.numToStr(12));
        assertEquals("yüzyetmiş", NumberUtil.numToStr(170));
        assertEquals("ikiyüzyetmişaltı", NumberUtil.numToStr(276));
        assertEquals("dokuzyüzdoksandokuz", NumberUtil.numToStr(999));
    }
}
