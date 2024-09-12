package org.csystem.util.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;


public class CollectionUtilTest {

    public void testAddIfNotNull()
    {
        var collection = new ArrayList<String>();
        String object = null;
        assertFalse(CollectionUtil.addIfNotNull(collection, object));
        assertTrue(collection.isEmpty());
    }

    public void testAreAllDistinct()
    {
        var collection = Arrays.asList("a", "b", "c");
        assertTrue(CollectionUtil.areAllDistinct(collection));

        collection = Arrays.asList("a", "b", "c", "a");
        assertFalse(CollectionUtil.areAllDistinct(collection));
    }

    public void testContains()
    {
    }

    public void testExtractSingleton()
    {
    }

    public void testFindValueOfType()
    {
    }

    public void testGet()
    {
    }

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