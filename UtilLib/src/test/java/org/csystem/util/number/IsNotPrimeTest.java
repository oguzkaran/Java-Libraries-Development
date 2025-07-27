package org.csystem.util.number;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class IsNotPrimeTest {

    @Test
    public void isNotPrimeTest()
    {
        assertTrue(NumberUtil.isNotPrime(42));
        assertTrue(NumberUtil.isNotPrime(44));
        assertTrue(NumberUtil.isNotPrime(45));
        assertTrue(NumberUtil.isNotPrime(46));
        assertTrue(NumberUtil.isNotPrime(40L));
        assertTrue(NumberUtil.isNotPrime(42L));
        assertTrue(NumberUtil.isNotPrime(44L));
        assertTrue(NumberUtil.isNotPrime(45L));
        assertTrue(NumberUtil.isNotPrime(new BigInteger("40")));
        assertTrue(NumberUtil.isNotPrime(new BigInteger("42")));
        assertTrue(NumberUtil.isNotPrime(new BigInteger("44")));
        assertTrue(NumberUtil.isNotPrime(new BigInteger("48")));

        assertFalse(NumberUtil.isNotPrime(23));
        assertFalse(NumberUtil.isNotPrime(37));
        assertFalse(NumberUtil.isNotPrime(23497));
        assertFalse(NumberUtil.isNotPrime(45613));
        assertFalse(NumberUtil.isNotPrime(9999999967L));
        assertFalse(NumberUtil.isNotPrime(99999999019L));
        assertFalse(NumberUtil.isNotPrime(100000000003L));
        assertFalse(NumberUtil.isNotPrime(new BigInteger("9999999967")));
        assertFalse(NumberUtil.isNotPrime(new BigInteger("99999999019")));
        assertFalse(NumberUtil.isNotPrime(new BigInteger("100000000003")));
    }
}
