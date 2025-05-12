package org.csystem.util.number;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GetPrimeTests {

    @Test
    public void getPrimeTest()
    {

        assertEquals(11, NumberUtil.getPrime(5));
        assertEquals(29, NumberUtil.getPrime(10));
        assertEquals(47, NumberUtil.getPrime(15));
        assertEquals(229, NumberUtil.getPrime(50));
        assertEquals(541, NumberUtil.getPrime(100));

    }

    @Test
    public void getPrimesTest()
    {
        int [] test1 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int [] test2 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71};
        int [] test3 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

        assertArrayEquals(test1, NumberUtil.getPrimes(10));
        assertArrayEquals(test2, NumberUtil.getPrimes(20));
        assertArrayEquals(test3, NumberUtil.getPrimes(25));
    }
}
