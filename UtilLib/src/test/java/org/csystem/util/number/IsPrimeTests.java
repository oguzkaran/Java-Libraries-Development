package org.csystem.util.number;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsPrimeTests {

    @Test
    public void isPrimeTest()
    {
        assertTrue(NumberUtil.isPrime(23));
        assertTrue(NumberUtil.isPrime(37));
        assertTrue(NumberUtil.isPrime(23497));
        assertTrue(NumberUtil.isPrime(45613));
        assertTrue(NumberUtil.isPrime(9999999967L));
        assertTrue(NumberUtil.isPrime(99999999019L));
        assertTrue(NumberUtil.isPrime(100000000003L));
        assertTrue(NumberUtil.isPrime(new BigInteger("9999999967")));
        assertTrue(NumberUtil.isPrime(new BigInteger("99999999019")));
        assertTrue(NumberUtil.isPrime(new BigInteger("100000000003")));

        assertFalse(NumberUtil.isPrime(42));
        assertFalse(NumberUtil.isPrime(44));
        assertFalse(NumberUtil.isPrime(45));
        assertFalse(NumberUtil.isPrime(46));
        assertFalse(NumberUtil.isPrime(40L));
        assertFalse(NumberUtil.isPrime(42L));
        assertFalse(NumberUtil.isPrime(44L));
        assertFalse(NumberUtil.isPrime(45L));
        assertFalse(NumberUtil.isPrime(new BigInteger("40")));
        assertFalse(NumberUtil.isPrime(new BigInteger("42")));
        assertFalse(NumberUtil.isPrime(new BigInteger("44")));
        assertFalse(NumberUtil.isPrime(new BigInteger("48")));
    }

    @Test
    @Ignore
    public void testIsPrimeWithLargeNumbers()
    {
        assertTrue(NumberUtil.isPrime(9223372036854775783L));
        assertTrue(NumberUtil.isPrime(new BigInteger("9223372036854775783")));
    }
}
