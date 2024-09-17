package org.csystem.util.collection;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IterableUtilTest {

    @Test
    void testCollateDuplicates()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);
        var b = Arrays.asList(3, 4, 5, 6, 7);

        var result = IterableUtil.collate(a, b, Integer::compareTo, true);
        var expected = Arrays.asList(1, 2, 3, 3, 4, 4, 5, 5, 6, 7);

        assertEquals(expected, result);
    }

    @Test
    void testCollateWithoutDuplicates()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);
        var b = Arrays.asList(3, 4, 5, 6, 7);

        var result = IterableUtil.collate(a, b, Integer::compareTo, false);
        var expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        assertEquals(expected, result);
    }

    @Test
    void testCollateWithNullIterable()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);

        assertThrows(NullPointerException.class, () -> IterableUtil.collate(a, null, Integer::compareTo, true));
    }

    @Test
    void testCollateWithNullComparator()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);
        var b = Arrays.asList(3, 4, 5, 6, 7);

        assertThrows(NullPointerException.class, () -> IterableUtil.collate(a, b, null, true));
    }

    @Test
    void testLimit()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);
        var size = 3;

        var expected = Arrays.asList(1, 2, 3);

        assertEquals(expected, IterableUtil.limit(a, size));
    }

    @Test
    void testRetainAll()
    {
        var a = new ArrayList<Integer>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);

        var b = Arrays.asList(3, 4, 5);

        assertTrue(IterableUtil.retainAll(a, b));
        assertEquals(3, a.size());
    }
}