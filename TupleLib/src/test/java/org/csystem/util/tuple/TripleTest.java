package org.csystem.util.tuple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TripleTest {

    @Test
    void createAnIntegerIntegerIntegerTriple_withConstructor_ThenCheckItsValues()
    {
        var triple = new Triple<>(1,2, 3);

        Assertions.assertEquals(1, triple.getFirst());
        Assertions.assertEquals(2, triple.getSecond());
        Assertions.assertEquals(3, triple.getThird());
    }

    @Test
    void createTwoIntegerIntegerIntegerTriples_withConstructor_whenTheValuesAreEqual_thenCheckItsEquality()
    {
        var tripleFirst = new Triple<>(1,2, 3);
        var tripleSecond = new Triple<>(1,2, 3);

        Assertions.assertEquals(tripleFirst, tripleSecond);
    }

    @Test
    void createTwoIntegerIntegerTriples_withConstructor_whenTheValuesAreNotEqual_thenCheckItsEquality()
    {
        var tripleFirst = new Triple<>(1,2,3);
        var tripleSecond = new Triple<>(3,2,1);

        Assertions.assertNotEquals(tripleFirst, tripleSecond);
    }

    @Test
    void createAnIntegerIntegerIntegerTriple_withConstructor_ThenCheckItsToString()
    {
        var triple = new Triple<>(1,2, 3);
        var tripleToStringExpectedValue = "(1, 2, 3)";

        Assertions.assertEquals(tripleToStringExpectedValue, triple.toString());
    }

    @Test
    void createAnIntegerIntegerIntegerTriple_withFactoryMethod_ThenCheckItsValues()
    {
        var triple = Triple.of(1,2, 3);

        Assertions.assertEquals(1, triple.getFirst());
        Assertions.assertEquals(2, triple.getSecond());
        Assertions.assertEquals(3, triple.getThird());
    }

    @Test
    void createTwoIntegerIntegerIntegerTriples_withFactoryMethod_whenTheValuesAreEqual_thenCheckItsEquality()
    {
        var tripleFirst = Triple.of(1,2, 3);
        var tripleSecond = Triple.of(1,2, 3);

        Assertions.assertEquals(tripleFirst, tripleSecond);
    }

    @Test
    void createTwoIntegerIntegerTriples_withFactoryMethod_whenTheValuesAreNotEqual_thenCheckItsEquality()
    {
        var tripleFirst = Triple.of(1,2,3);
        var tripleSecond = Triple.of(3,2,1);

        Assertions.assertNotEquals(tripleFirst, tripleSecond);
    }

    @Test
    void createAnIntegerIntegerIntegerTriple_withFactoryMethod_ThenCheckItsToString()
    {
        var triple = Triple.of(1,2, 3);
        var tripleToStringExpectedValue = "(1, 2, 3)";

        Assertions.assertEquals(tripleToStringExpectedValue, triple.toString());
    }
}
