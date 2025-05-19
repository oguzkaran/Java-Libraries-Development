package org.csystem.util.number;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class FactorialIntegerTests {

    @Test
    public void factorialReturn1WhenInputIs0()
    {
        assertEquals(1, NumberUtil.factorial(0));
    }

    @Test
    public void biginteger_factorial_return1_whenInputIs0()
    {
        assertEquals(BigInteger.ONE, NumberUtil.factorial(0L));
    }

    @Test
    public void factorialReturn1WhenInputIs1()
    {
        assertEquals(1, NumberUtil.factorial(1));
    }

    @Test
    public void factorialReturn1WhenInputIsNegative()
    {
        assertEquals(1, NumberUtil.factorial(-1));
        assertEquals(1, NumberUtil.factorial(-5));
        assertEquals(1, NumberUtil.factorial(-10));
    }

    @Test
    public void factorialWithPositiveNumber()
    {
        assertEquals(120, NumberUtil.factorial(5));
        assertEquals(720, NumberUtil.factorial(6));
        assertEquals(5040, NumberUtil.factorial(7));
        assertEquals(40320, NumberUtil.factorial(8));
        assertEquals(362880, NumberUtil.factorial(9));
        assertEquals(3628800, NumberUtil.factorial(10));
    }
}
