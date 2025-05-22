package org.csystem.util.tuple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValueTest {

    @Test
    void createAnBoxedIntValue_withConstructor_ThenCheckItsUnboxedValue()
    {
        var value = new Value<>(1);
        Assertions.assertEquals(1, value.getValue());
    }

    @Test
    void createTwoBoxedIntValues_withConstructor_whenTheValuesAreEqual_thenCheckItsEquality()
    {
        var firstValue = new Value<>(1);
        var secondValue = new Value<>(1);

        Assertions.assertEquals(firstValue, secondValue);
    }

    @Test
    void createTwoBoxedIntValues_withConstructor_whenTheValuesAreNotEqual_thenCheckItsEquality()
    {
        var firstValue = new Value<>(1);
        var secondValue = new Value<>(2);

        Assertions.assertNotEquals(firstValue, secondValue);
    }

    @Test
    void createABoxedIntValue_withConstructor_ThenCheckItsToString()
    {
        var value = new Value<>(1);
        var pairToStringExpectedValue = "1";

        Assertions.assertEquals(pairToStringExpectedValue, value.toString());
    }

    @Test
    void createAnBoxedIntValue_withFactoryMethod_ThenCheckItsUnboxedValue()
    {
        var value = Value.of(1);
        Assertions.assertEquals(1, value.getValue());
    }

    @Test
    void createTwoBoxedIntValues_withFactoryMethod_whenTheValuesAreEqual_thenCheckItsEquality()
    {
        var firstValue = Value.of(1);
        var secondValue = Value.of(1);

        Assertions.assertEquals(firstValue, secondValue);
    }

    @Test
    void createTwoBoxedIntValues_withFactoryMethod_whenTheValuesAreNotEqual_thenCheckItsEquality()
    {
        var firstValue = Value.of(1);
        var secondValue = Value.of(2);

        Assertions.assertNotEquals(firstValue, secondValue);
    }

    @Test
    void createABoxedIntValue_withFactoryMethod_ThenCheckItsToString()
    {
        var value = Value.of(1);
        var pairToStringExpectedValue = "1";

        Assertions.assertEquals(pairToStringExpectedValue, value.toString());
    }
}
