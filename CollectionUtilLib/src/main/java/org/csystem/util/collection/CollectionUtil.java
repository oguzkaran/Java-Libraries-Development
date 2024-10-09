package org.csystem.util.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public final class CollectionUtil {
    private CollectionUtil()
    {
    }

    // Adds an element to the collection unless the element is null.
    public static <E> boolean addIfNotNull(Collection<E> collection, E object)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <E> boolean areAllDistinct(Collection<? extends E> collection)
    {
        return collection.size() == new HashSet<E>(collection).size();
    }

    public static <E> boolean contains(Collection<E> iterable, Object object)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Extract the lone element of the specified Collection
    //Can be used as ImmutableSingleton Collections.singleton(collection.iterator().next());
    public static <E> E extractSingleton(Collection<E> collection)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //It is used to search for a component of a specific type within collections containing multiple types.
    public static Object findValueOfType(Collection<?> collection, Class<?>[] types)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <K, V> Map.Entry<K, V> get(Map<K, V> map, int index)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Removes elements whose index are between startIndex, inclusive, and endIndex.
    public static <E> Collection<E> removeCount(Collection<E> input, int startIndex, int count)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //...
}
