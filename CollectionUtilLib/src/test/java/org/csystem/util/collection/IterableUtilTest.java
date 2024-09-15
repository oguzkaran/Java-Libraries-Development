package org.csystem.util.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

class IterableUtilTest {

    @Test
    void areAllDistinct() {
        var iterable = List.of(1, 2, 3, 4, 5);
        assertTrue(IterableUtil.areAllDistinct(iterable));
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

    @org.junit.jupiter.api.Test
    void collate() {
    }

    @org.junit.jupiter.api.Test
    void contains() {
    }

    @org.junit.jupiter.api.Test
    void testCollate() {
    }

    @Test
    void testConcat() {

        var iterable = List.of(1, 2, 3, 4, 5);
        var iterable2 = List.of(6, 7, 8, 9, 10);
        var excepted = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var result = List.of(iterable, iterable2);
        assertEquals(excepted, IterableUtil.concat(result));
    }

    @org.junit.jupiter.api.Test
    void cycle() {
    }

    @Test
    void testFindFirst() {
        var iterable = List.of(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i % 2 == 0;
        var excepted = Optional.of(2);
        assertEquals(excepted, IterableUtil.findFirst(iterable, predicate));
    }

    @org.junit.jupiter.api.Test
    void findValueOfType() {
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

    @org.junit.jupiter.api.Test
    void isSubCollection() {
    }

    @org.junit.jupiter.api.Test
    void limit() {
    }

    @org.junit.jupiter.api.Test
    void mergeSorted() {
    }

    @org.junit.jupiter.api.Test
    void paddedPartition() {
    }

    @org.junit.jupiter.api.Test
    void retainAll() {
    }

    @org.junit.jupiter.api.Test
    void toList() {
    }

    @org.junit.jupiter.api.Test
    void toIterable() {
    }

    @org.junit.jupiter.api.Test
    void testToList() {
    }

    @org.junit.jupiter.api.Test
    void testToIterable() {
    }

    @org.junit.jupiter.api.Test
    void forEach() {
    }

    @org.junit.jupiter.api.Test
    void testForEach() {
    }

    @Test
    void union() {
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
    void unionAll() {
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