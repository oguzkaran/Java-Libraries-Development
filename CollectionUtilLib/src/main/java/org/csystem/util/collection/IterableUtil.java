package org.csystem.util.collection;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class IterableUtil {
    private IterableUtil()
    {
    }

    public static <T> boolean areAllDistinct(Iterable<? extends T> iterable)
    {
        throw new UnsupportedOperationException("TODO");
    }


    // Check if all are not null
    public static boolean checkAllNotNull(Iterable<?>... iterables)
    {
        throw new UnsupportedOperationException("TODO");
    }

    public static <T> List<T> collate(Iterable<? extends T> a, Iterable<? extends T> b,
                                      Comparator<? super T> comparator)
    {
        return collate(a, b, comparator, true);
    }

    // Checks if the object is contained in the given iterable.
    // Instead of dealing with a loop, this method with two parameters can be used for a quick check.
    public static <T> boolean contains(Iterable<T> iterable, Object object)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Merges two sorted Collections into a single, sorted List
    // such that the ordering of the elements according to Comparator c is retained.
    public static <T> List<T> collate(Iterable<? extends T> a, Iterable<? extends T> b,
                                      Comparator<? super T> comparator, boolean includeDuplicates)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Combines multiple iterables into a single iterable.
    public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> iterable)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Returns an iterable whose iterators cycle indefinitely over the elements of iterable.
    public static <T> Iterable<T> cycle(Iterable<T> iterable)
    {
        throw new UnsupportedOperationException("Will be written by Oğuz Karan");
    }

    //Returns an Optional containing the first element in iterable that satisfies the given predicate, if such an element exists.
    static <T> Optional<T> findFirst(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //It is used to search for a component of a specific type within collections containing multiple types.
    public static Object findValueOfType(Iterable<?> collection, Class<?>[] types)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Returns the number of occurrences of the provided object in the iterable.
    public static <E, T extends E> int frequency(Iterable<E> iterable, T obj)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Returns a {@link Map} mapping each unique element in the given collection to its cardinality.
    public static <T> Map<T, Integer> getCardinalityMap(Iterable<? extends T> coll)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Returns a {@link Collection} containing the intersection of the given collections.
    public static <T> Collection<T> intersection(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Returns {@code true} iff <em>a</em> is a sub-collection of <em>b</em>.
    public static boolean isSubCollection(Collection<?> a, Collection<?> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }



    //Returns a view of iterable containing its first limitSize elements.
    public static <T> Iterable<T> limit(Iterable<T> iterable, int limitSize)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Returns an iterable over the merged contents of all given iterables.
    static <T> Iterable<T> mergeSorted(Iterable<? extends Iterable<? extends T>> iterables,
                                      Comparator<? super T> comparator)
    {
        throw new UnsupportedOperationException("Will be written by Oğuz Karan");
    }

    //Divides an iterable into unmodifiable sublists of the given size, padding the final iterable with null values if necessary.
    public static <T> Iterable<List<T>> paddedPartition(Iterable<T> iterable, int size)
    {
        throw new UnsupportedOperationException("Will be written by Oğuz Karan");
    }

    //Removes, from an iterable, every element that does not belong to the provided collection.
    public static boolean retainAll(Iterable<?> removeFrom, Collection<?> elementsToRetain)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T, R> List<R> toList(Iterable<T> iterable, Function<? super T, R> func)
    {
        return toList(iterable, func, false);
    }

    public static <T, R> Iterable<R> toIterable(Iterable<T> iterable, Function<? super T, R> func)
    {
        return toIterable(iterable, func, false);
    }

    public static <T, R> List<R> toList(Iterable<T> iterable, Function<? super T, R> func, boolean parallel)
    {
        return StreamSupport.stream(iterable.spliterator(), parallel).map(func).collect(Collectors.toList());
    }

    public static <T, R> Iterable<R> toIterable(Iterable<T> iterable, Function<? super T, R> func, boolean parallel)
    {
        return toList(iterable, func, parallel);
    }

    public static <T> void forEach(Iterable<T> iterable, Consumer<? super T> consumer)
    {
        forEach(iterable, consumer, false);
    }

    public static <T> void forEach(Iterable<T> iterable, Consumer<? super T> consumer, boolean parallel)
    {
        StreamSupport.stream(iterable.spliterator(), parallel).forEach(consumer);
    }

    // Returns a {@link Collection} containing the union of the given collections.
    // Two lists can be combined and made unique using getCardinalityMap.
    public static <O> Collection<O> union(Iterable<? extends O> a, Iterable<? extends O> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Returns a {@link Collection} containing the union of the given collections.
    // Two lists can be combined with all elements
    public static <O> Collection<O> unionAll(Iterable<? extends O> a, Iterable<? extends O> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
