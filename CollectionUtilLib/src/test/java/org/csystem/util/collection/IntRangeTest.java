package org.csystem.util.collection;

import org.csystem.util.iterable.range.DoubleRange;
import org.csystem.util.iterable.range.IntRange;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IntRangeTest {

    @Test
    public void testIteratorProducesCorrectCountForOpenRange()
    {
        var min = 0;
        var max = 6;
        var intRange = IntRange.of(min, max);
        var expectedCount = 6;
        var actualCount = 0;

        for (var ignored : intRange)
            ++actualCount;

        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void testIteratorProducesCorrectCountForClosedRange()
    {
        var min = 0;
        var max = 6;
        var intRange = IntRange.ofClosed(min, max);
        var expectedCount = 7;
        var actualCount = 0;

        for (var ignored : intRange)
            ++actualCount;

        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void testIteratorProducesCorrectCountWithCustomStepForOpenRange()
    {
        var min = 1;
        var max = 5;
        var step = 1;
        var intRange = IntRange.of(min, max, step);
        var expectedValue = min;
        for (var value : intRange) {
            assertEquals(expectedValue, value);
            expectedValue += step;
        }
    }

    @Test
    public void testIteratorProducesCorrectCountWithCustomStepForClosedRange()
    {
        var min = 1;
        var max = 5;
        var step = 1;
        var intRange = IntRange.ofClosed(min, max, step);
        var expectedValue = min;
        for (var value : intRange) {
            assertEquals(expectedValue, value);
            expectedValue += step;
        }
    }

    @Test
    public void testIteratorProducesCorrectCountWithCustomOperatorForOpenRange()
    {
        var min = 1;
        var max = 100;
        var intRange = IntRange.of(min, max, val -> val * 3);
        var expectedValue = min;

        for (var actualValue : intRange) {
            assertEquals(expectedValue, actualValue);
            expectedValue *= 3;
        }
    }

    @Test
    public void testIteratorProducesCorrectCountWithCustomOperatorForClosedRange()
    {
        var min = 1;
        var max = 100;
        var intRange = IntRange.ofClosed(min, max, val -> val * 3);
        var expectedValue = min;

        for (var actualValue : intRange) {
            assertEquals(expectedValue, actualValue);
            expectedValue *= 3;
        }
    }

    @Test
    public void testThrowsExceptionWhenNextExhaustedForOpenRange()
    {
        var min = 1;
        var max = 3;
        var step = 1;
        var intRange = IntRange.of(min, max, step);
        var iterator = intRange.iterator();

        iterator.next();
        iterator.next();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testThrowsExceptionWhenNextExhaustedForClosedRange()
    {
        var min = 1;
        var max = 3;
        var step = 1;
        var intRange = IntRange.ofClosed(min, max, step);
        var iterator = intRange.iterator();

        iterator.next();
        iterator.next();
        iterator.next();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testGetMinAndMaxForOpenRange()
    {
        var min = 1;
        var max = 5;
        var intRange = IntRange.of(min, max);

        assertEquals(min, intRange.getMin());
        assertEquals(max - 1, intRange.getMax());
    }

    @Test
    public void testGetMinAndMaxForClosedRange()
    {
        var min = 1;
        var max = 5;
        var intRange = IntRange.ofClosed(min, max);

        assertEquals(min, intRange.getMin());
        assertEquals(max, intRange.getMax());
    }
}
