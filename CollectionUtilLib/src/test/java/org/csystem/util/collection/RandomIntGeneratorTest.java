package org.csystem.util.collection;

import org.csystem.util.iterable.generator.RandomIntGenerator;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RandomIntGeneratorTest {

    @Test
    public void testCountAndIteratorSizeForOf()
    {
        var count = 10;
        var intGenerator = RandomIntGenerator.of(count, 10, 20);
        var iterator = intGenerator.iterator();
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
        var min = 10;
        var max = 20;
        var intGenerator = RandomIntGenerator.of(count, min, max);

        for (var value : intGenerator)
            assertTrue(value >= min && value < max);

    }

    @Test
    public void testOfWithSupplier()
    {
        var count = 10;
        var expectedValue = 3;
        var intGenerator = RandomIntGenerator.of(count, () -> expectedValue);

        for (var actualValue : intGenerator)
            assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testThrowsExceptionWhenNextExhausted()
    {
        var count = 3;
        var intGenerator = RandomIntGenerator.of(count, () -> 3);
        var iterator = intGenerator.iterator();

        iterator.next();
        iterator.next();
        iterator.next();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testGetCount()
    {
        var count = 10;
        var intGenerator = RandomIntGenerator.of(count, () -> 3);
        assertEquals(count, intGenerator.getCount());
    }

}
