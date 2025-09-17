package org.csystem.util.collection;

import org.csystem.util.iterable.generator.RandomDoubleGenerator;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RandomDoubleGeneratorTest {

    @Test
    public void testCountAndIteratorSizeForOf()
    {
        var count = 10;
        var doubleGenerator = RandomDoubleGenerator.of(count, 10.0, 20.0);
        var iterator = doubleGenerator.iterator();
        var iteratorSize = 0;

        while (iterator.hasNext()) {
            iterator.next();
            ++iteratorSize;
        }

        assertEquals(count, iteratorSize);
    }

    @Test
    public void testValuesIfInRangeForOf()
    {
        var count = 10;
        var min = 10.0;
        var max = 20.0;
        var doubleGenerator = RandomDoubleGenerator.of(count, min, max);

        for (var value : doubleGenerator)
            assertTrue(value >= min && value < max);

    }

    @Test
    public void testOfWithSupplier()
    {
        var count = 10;
        var expectedValue = 3.14;
        var doubleGenerator = RandomDoubleGenerator.of(count, () -> expectedValue);

        for (var actualValue : doubleGenerator)
            assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testThrowsExceptionWhenNextExhausted()
    {
        var count = 3;
        var doubleGenerator = RandomDoubleGenerator.of(count, () -> 3.14);
        var iterator = doubleGenerator.iterator();

        iterator.next();
        iterator.next();
        iterator.next();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testGetCount()
    {
        var count = 10;
        var doubleGenerator = RandomDoubleGenerator.of(count, () -> 3.14);
        assertEquals(count, doubleGenerator.getCount());
    }

}
