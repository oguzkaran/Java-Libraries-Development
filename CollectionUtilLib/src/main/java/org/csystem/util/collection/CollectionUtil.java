package org.csystem.util.collection;

import java.util.*;

/**
 * Utility class for collection operations including {@link java.util.Collection} and {@link java.util.Map}
 * <p>1993 by C and System Programmers Association (CSD) All Rights Free</p>
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public final class CollectionUtil {
    private CollectionUtil()
    {
    }

    /**
     * Adds the specified object to the given collection if the object is not null.
     * @param collection the {@link Collection} to add the object to
     * @param object the {@link Object} to add
     * @return true if the {@code object} was added, false otherwise
     * @param <E> the type of the elements in the collection
     * @throws NullPointerException if {@code collection}
     */
    public static <E> boolean addIfNotNull(Collection<E> collection, E object)
    {

        if (collection == null)
            throw new NullPointerException("Collection cannot be null");

        return object != null && collection.add(object);
    }

    /**
     *  Checks if all elements in the specified {@link Collection} are distinct.
     * @param collection the {@link Collection} to check if all elements are distinct
     * @return true if all elements are distinct otherwise return false
     * @param <E> the type of the elements in the {@link Collection}
     */
    public static <E> boolean areAllDistinct(Collection<? extends E> collection)
    {
        return collection.size() == new HashSet<E>(collection).size();
    }

    /**
     * Checks if the object is contained in the given iterable.
     * Instead of dealing with a loop, this method with two parameters can be used for a quick check.
     * @param iterable the {@link Collection} iterable to check if the object is contained
     * @param object the {@link Object} to check if it is contained in the iterable
     * @return true if the object is contained in the iterable, false otherwise
     * @param <E> the type of the elements in the iterable
     * @throws NullPointerException if {@code iterable} or {@code object} is null
     */
    public static <E> boolean contains(Collection<E> iterable, Object object)
    {
        if (iterable == null || object == null)
            throw new NullPointerException();

        throw new UnsupportedOperationException("Not yet implemented"); //TODO: Binary search ????
    }

    /**
     * Returns the single element contained in the given collection,throws an {@link Exception} if the collection is empty
     * or contains more than one element.
     * @param collection the {@link Collection} to extract a single element from
     * @return the single element contained in the given collection
     * @throws NullPointerException if {@code collection} is null
     * @throws IllegalArgumentException if {@code collection} is empty or contains more than one element
     * @param <E> the type of the elements in the {@code collection}
     */
    public static <E> E extractSingleton(Collection<E> collection)
    {
        Objects.requireNonNull(collection, "collection cannot be null");
        if (collection.size() != 1)
            throw new IllegalArgumentException("collection cannot contain more than one element");
        return collection.iterator().next();
    }

    /**
     * It is used to search for a component of a specific type within collections containing multiple types.
     * @param collection the {@link Collection} to search for the object of the specific type
     * @param types the types of the objects to search for in the collection
     * @return the object of the specific type if found, otherwise return null
     * @throws NullPointerException if {@code collection} is null or {@code types}
     */
    public static Object findValueOfType(Collection<?> collection, Class<?>[] types)
    {
        if (collection == null)
            throw new NullPointerException("collection cannot be null");
        if (types == null)
            throw new NullPointerException("types cannot be null");

        for (var item : collection)
            if (item != null)
                for (var type : types)
                    if (type.isInstance(item))
                        return item;

        return null;
    }

    /**
     * Returns the element at the given index from the given map.
     * @param map the {@link Map} to get the element from
     * @param index the index to get the element from
     * @return the element at the given index from the given map
     * @throws NullPointerException if {@code map} is null
     * @throws IndexOutOfBoundsException if {@code index} is out of range
     * @param <K> the type of the keys in the {@code map}
     * @param <V> the type of the values in the {@code map}
     */
    public static <K, V> Map.Entry<K, V> get(Map<K, V> map, int index)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");
        if (index < 0 || map.size() < index)
            throw new IndexOutOfBoundsException("index cannot be out of range");

        return map.entrySet().stream().
                skip(index).
                findFirst().
                orElse(null);

    }

    /**
     * Removes the specified number of elements from the start index in the collection and returns them.
     * This method modifies the input collections.
     * @param input the {@link Collection} to remove elements from
     * @param startIndex the index to start removing from
     * @param count the number of elements to remove
     * @return a new {@link Collection} with the elements that have been removed
     * @throws NullPointerException if {@code input} is null
     * @throws IndexOutOfBoundsException if {@code startIndex} is out of range
     */
    public static <E> Collection<E> removeCount(Collection<E> input, int startIndex, int count)
    {
        if (input == null)
            throw new NullPointerException("input cannot be null");
        if (startIndex < 0 || input.size() < startIndex)
            throw new IndexOutOfBoundsException("startIndex cannot be out of range");
        if (input.size() < startIndex + count || count < 0)
            throw new IndexOutOfBoundsException("count cannot be out of range");

        var removedElements = new ArrayList<E>();

        input.stream().
            skip(startIndex).
            limit(count).
            forEach(removedElements::add);

        input.removeAll(removedElements);

        return removedElements;
    }

}
