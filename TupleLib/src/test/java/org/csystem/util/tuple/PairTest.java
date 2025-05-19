package org.csystem.util.tuple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PairTest {

    @Test
    void createAnIntegerIntegerPair_withConstructor_ThenCheckItsValues()
    {
        var pair = new Pair<>(1,2);

        Assertions.assertEquals(1, pair.getFirst());
        Assertions.assertEquals(2, pair.getSecond());
    }

    @Test
    void createTwoIntegerIntegerPairs_withConstructor_whenTheValuesAreEqual_thenCheckItsEquality()
    {
        var pairFirst = new Pair<>(1,2);
        var pairSecond = new Pair<>(1,2);

        Assertions.assertEquals(pairFirst, pairSecond);
    }

    @Test
    void createTwoIntegerIntegerPairs_withConstructor_whenTheValuesAreNotEqual_thenCheckItsEquality()
    {
        var pairFirst = new Pair<>(1,2);
        var pairSecond = new Pair<>(2,1);

        Assertions.assertNotEquals(pairFirst, pairSecond);
    }

    @Test
    void createAnIntegerIntegerPair_withConstructor_ThenCheckItsToString()
    {
        var pair = new Pair<>(1,2);
        var pairToStringExpectedValue = "(1, 2)";

        Assertions.assertEquals(pairToStringExpectedValue, pair.toString());
    }

    @Test
    void createAnIntegerIntegerPair_withFactoryMethod_ThenCheckItsValues()
    {
        var pair = Pair.of(1,2);

        Assertions.assertEquals(1, pair.getFirst());
        Assertions.assertEquals(2, pair.getSecond());
    }

    @Test
    void createTwoIntegerIntegerPairs_withFactoryMethod_whenTheValuesAreEqual_thenCheckItsEquality()
    {
        var pairFirst = Pair.of(1,2);
        var pairSecond = Pair.of(1,2);

        Assertions.assertEquals(pairFirst, pairSecond);
    }

    @Test
    void createTwoIntegerIntegerPairs_withFactoryMethod_whenTheValuesAreNotEqual_thenCheckItsEquality()
    {
        var pairFirst = Pair.of(1,2);
        var pairSecond = Pair.of(2,1);

        Assertions.assertNotEquals(pairFirst, pairSecond);
    }

    @Test
    void createAnIntegerIntegerPair_withFactoryMethod_ThenCheckItsToString()
    {
        var pair = Pair.of(1,2);
        var pairToStringExpectedValue = "(1, 2)";

        Assertions.assertEquals(pairToStringExpectedValue, pair.toString());
    }
}
