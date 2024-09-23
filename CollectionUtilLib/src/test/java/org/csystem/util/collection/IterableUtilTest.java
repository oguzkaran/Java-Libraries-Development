package org.csystem.util.collection;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
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
    void testConcat()
    {
        List<List<String>> iterable = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d"),
                Arrays.asList("e", "f")
        );

        var excepted = List.of("a", "b", "c", "d", "e", "f");
        assertEquals(excepted, IterableUtil.concat(iterable));

    }

    @Test
    void testFindValueOfType()
    {
        Class<?>[] types = {String.class, Integer.class};

        Collection<Object> objs1 = Arrays.asList("Ezgisu", "Deniz", null, 1);
        Collection<Object> objs2 = Arrays.asList(2, "Ezgisu", "Deniz", null, 1);

        var val1 = CollectionUtil.findValueOfType(objs1, types);
        var val2 = CollectionUtil.findValueOfType(objs2, types);

        assertEquals("Ezgisu", val1);
        assertEquals(2, val2);
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

    @Test
    void testToList()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);
        Function f = i -> i + "*";
        var expected = Arrays.asList("1*", "2*", "3*", "4*", "5*");

        assertEquals(expected, IterableUtil.toList(a, f));
    }

    @Test
    void testToIterableParallel()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);
        Function f = i -> i + "*";
        var expected = Arrays.asList("1*", "2*", "3*", "4*", "5*");

        assertEquals(expected, IterableUtil.toIterable(a, f, true));
    }

    @Test
    void testToIterable()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);
        Function f = i -> i + "*";
        var expected = Arrays.asList("1*", "2*", "3*", "4*", "5*");

        assertEquals(expected, IterableUtil.toIterable(a, f));
    }


    @Test
    void testToListParallel()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);
        Function f = i -> i + "*";
        var expected = Arrays.asList("1*", "2*", "3*", "4*", "5*");

        assertEquals(expected, IterableUtil.toList(a, f,true));
    }

    @Test
    void testIsSubCollection()
    {
        var a = Arrays.asList(1, 2, 3, 4, 5);
        var b = Arrays.asList(3, 4, 5);
        assertTrue(IterableUtil.isSubCollection(a, b));
    }

    @Test
    void frequency() {
        var iterable = List.of(1, 1, 1, 4, 5);
        assertEquals(3, IterableUtil.frequency(iterable, 1));
    }

    @Test
    void testGetCardinalityMap() {
        var iterable = List.of(1, 1, 1, 4, 5);
        var excepted = Map.of(1, 3, 4, 1, 5, 1);
        assertEquals(excepted, IterableUtil.getCardinalityMap(iterable));
    }

    @Test
    void testIntersection() {
        var iterable1 = List.of(1, 2, 3, 4, 5);
        var iterable2 = List.of(3, 4, 5, 6, 7);
        var excepted = Set.of(3, 4, 5);
        assertEquals(excepted, IterableUtil.intersection(iterable1, iterable2));
    }



    @Test
    void testAreAllDistinct()
    {
        var a = Arrays.asList(1, 2, 3);
        var b = Arrays.asList(1, 2,2, 3, 4);

        assertTrue(IterableUtil.areAllDistinct(a));
        assertFalse(IterableUtil.areAllDistinct(b));
    }

    @Test
    void checkAllNotNull() {
        var iterable = List.of(1, 2, 3, 4, 5);
        assertTrue(IterableUtil.checkAllNotNull(iterable));

        List<String> list1 = Arrays.asList("a", null, "c");
        List<String> list2 = Arrays.asList("d", "e", "f");
        assertFalse(IterableUtil.checkAllNotNull(list1, list2));

        List<String> list3 = Arrays.asList(null, null, null);
        List<String> list4 = Arrays.asList(null, null, null);
        assertFalse(IterableUtil.checkAllNotNull(list1, list2));

    }

    @Test
    void testFindFirst() {
        var iterable = List.of(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i % 2 == 0;
        var excepted = Optional.of(2);
        assertEquals(excepted, IterableUtil.findFirst(iterable, predicate));
    }

    @Test
    void testUnion() {
        var iterable1 = List.of(1, 2);
        var iterable2 = List.of(3, 4);
        var iterable3 = List.of(1, 2, 3, 3, 2);
        var iterable4 = List.of(1, 4, 5, 3, 2);

        var excepted1 = List.of(1, 2, 3, 4);
        var excepted2 = List.of(1, 2, 3, 4, 5);

        assertEquals(excepted1, IterableUtil.union(iterable1, iterable2));
        assertEquals(excepted2, IterableUtil.union(iterable3, iterable4));
    }


    @Test
    void testUnionAll() {
        var iterable1 = List.of(1, 2);
        var iterable2 = List.of(3, 4);
        var iterable3 = List.of(1, 2, 3, 3, 2);
        var iterable4 = List.of(1, 4, 5, 3, 2);

        var excepted1 = List.of(1, 2, 3, 4);
        var excepted2 = List.of(1, 2, 3, 3, 2, 1, 4, 5, 3, 2);

        assertEquals(excepted1, IterableUtil.unionAll(iterable1, iterable2));
        assertEquals(excepted2, IterableUtil.unionAll(iterable3, iterable4));
    }
}