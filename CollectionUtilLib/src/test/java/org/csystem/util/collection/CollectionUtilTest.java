package org.csystem.util.collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;


public class CollectionUtilTest {

    @Test
    public void testAddIfNotNull()
    {
        var collection = new ArrayList<String>();
        String object = null;
        assertFalse(CollectionUtil.addIfNotNull(collection, object));
        assertTrue(collection.isEmpty());
    }

    @Test
    public void testAreAllDistinct()
    {
        var collection = Arrays.asList("a", "b", "c");
        assertTrue(CollectionUtil.areAllDistinct(collection));

        collection = Arrays.asList("a", "b", "c", "a");
        assertFalse(CollectionUtil.areAllDistinct(collection));
    }

    @Test
    public void testContains()
    {
        var item = "a";
        var collection = Arrays.asList("a", "b", "c");

        assertTrue(CollectionUtil.contains(collection, item));
        assertFalse(CollectionUtil.contains(collection, "d"));
    }

    @Test
    public void testExtractSingleton()
    {
        var collection = Arrays.asList("a");
        assertEquals("a", CollectionUtil.extractSingleton(collection));
    }

    @Test
    public void testFindValueOfType()
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
    public void testGet()
    {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 0);
        map.put("b", 1);
        map.put("c", 2);

        var expected = new HashMap<>();
        expected.put("b", 1);

        assertEquals(expected.entrySet().iterator().next(), CollectionUtil.get(map, 1));
    }

    @Test
    public void testRemoveCount()
    {
        var startIndex = 3;
        var count = 2;
        var list = new ArrayList<Integer>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
        var removedElements = CollectionUtil.removeCount(list, startIndex, count);
        assertEquals(2, removedElements.size());
        assertEquals(3, list.size());
    }
}