package org.csystem.util.number;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class FactorialBigIntegerTests {

    @Test
    public void factorialReturn1WhenInputIs0()
    {
        assertEquals(BigInteger.ONE, NumberUtil.factorial(0L));
    }

    @Test
    public void factorialReturn1WhenInputIs1()
    {
        assertEquals(BigInteger.ONE, NumberUtil.factorial(1L));
    }

    @Test
    public void factorialReturn1WhenInputIsNegative()
    {
        assertEquals(BigInteger.ONE, NumberUtil.factorial(-1L));
        assertEquals(BigInteger.ONE, NumberUtil.factorial(-5L));
        assertEquals(BigInteger.ONE, NumberUtil.factorial(-10L));
    }

    @Test
    public void factorialWithPositiveNumber()
    {
        assertEquals(BigInteger.valueOf(120), NumberUtil.factorial(5L));
        assertEquals(BigInteger.valueOf(720), NumberUtil.factorial(6L));
        assertEquals(BigInteger.valueOf(5040), NumberUtil.factorial(7L));
        assertEquals(BigInteger.valueOf(40320), NumberUtil.factorial(8L));
        assertEquals(BigInteger.valueOf(362880), NumberUtil.factorial(9L));
        assertEquals(BigInteger.valueOf(3628800), NumberUtil.factorial(10L));
    }
}
