package org.csystem.util.collection;

import org.csystem.util.iterable.range.DoubleRange;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleRangeTest {

    @Test
    public void testIteratorProducesCorrectCount()
    {
        var min = 1.0;
        var max = 5.0;
        var step = 0.5;
        var expectedCount = (int)((max - min) / step);
        var doubleRange = DoubleRange.of(min, max, step);
        var actualCount = 0;

        for (var ignored : doubleRange)
            ++actualCount;

        assertEquals(expectedCount,actualCount);

    }

    @Test
    public void testValuesIfInRangeForOf()
    {
        var min = 1.0;
        var max = 5.0;
        var step = 0.5;
        var doubleRange = DoubleRange.of(min, max, step);

        for (var value : doubleRange)
            assertTrue(value >= min && value < max);
    }

    @Test
    public void testWithCustomOperator()
    {
        var min = 1.0;
        var max = 100.0;
        var doubleRange = DoubleRange.of(min, max, val -> val * 2);
        var expectedValue = min;

        for (var actualValue : doubleRange) {
            assertEquals(expectedValue, actualValue);
            expectedValue *= 2;
        }
    }

    @Test
    public void testThrowsExceptionWhenNextExhausted()
    {
        var min = 1.0;
        var max = 2.5;
        var step = 0.5;
        var doubleRange = DoubleRange.of(min, max, step);
        var iterator = doubleRange.iterator();

        iterator.next();
        iterator.next();
        iterator.next();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testGetMinAndMax()
    {
        var min = 1.0;
        var max = 5.0;
        var step = 0.5;
        var doubleRange = DoubleRange.of(min, max, step);

        assertEquals(min, doubleRange.getMin());
        assertEquals(max, doubleRange.getMax());
    }
}
