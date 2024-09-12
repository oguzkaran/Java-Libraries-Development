package org.csystem.util.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    }

    @Test
    public void testExtractSingleton()
    {
    }
    @Test
    public void testFindValueOfType()
    {
    }
    @Test
    public void testGet()
    {
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