package org.csystem.util.number;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GetRandomNumberUniqueDigitsTests {

    @Test
    public void getRandomNumberUniqueDigitsWithRandomTest()
    {
        var random  = new Random();

        for (var n = 1; n <= 10; n++)
        {
            var val = NumberUtil.getRandomNumberUniqueDigits(random, n);
            var valStr = String.valueOf(val);

            assertEquals(n, valStr.length());

            var firstDigit = Integer.parseInt(String.valueOf(valStr.charAt(0)));
            assertNotEquals(0, firstDigit);

            var digits = valStr.toCharArray();
            var set = new HashSet<>();

            for (var c : digits)
                set.add(c);

            assertEquals(digits.length, set.size());
        }
    }

    @Test
    public void getRandomNumberUniqueDigitsTest()
    {

        for (var n = 1; n <= 10; n++)
        {
            var val = NumberUtil.getRandomNumberUniqueDigits( n);
            var valStr = String.valueOf(val);

            assertEquals(n, valStr.length());

            var firstDigit = Integer.parseInt(String.valueOf(valStr.charAt(0)));
            assertNotEquals(0, firstDigit);

            var digits = valStr.toCharArray();
            var set = new HashSet<>();

            for (var c : digits)
                set.add(c);

            assertEquals(digits.length, set.size());
        }
    }
}
