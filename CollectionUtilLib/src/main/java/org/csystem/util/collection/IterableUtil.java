package org.csystem.util.collection;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * Utility class for iterable operations including {@link java.util.Collection} and {@link java.util.Map}
 * <p>1993 by C and System Programmers Association (CSD) All Rights Free</p>
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public final class IterableUtil {
    private IterableUtil()
    {
    }

    /**
     *  Checks if all elements in the specified {@link Iterable} are distinct.
     * @param iterable the {@link Iterable} to check if all elements are distinct
     * @return true if all elements are distinct otherwise return false
     * @param <T> the type of the elements in the {@link Iterable}
     */
    public static <T> boolean areAllDistinct(Iterable<? extends T> iterable)
    {
        throw new UnsupportedOperationException("TODO");
    }


    /**
     * Checks if all elements in the specified {@link Iterable} are not null.
     * @param iterables the {@link Iterable} to check if all elements are not null
     * @return true if all elements are not null otherwise return false
     * @param <?> the type of the elements in the {@link Iterable}
     * @throws NullPointerException if collection is null
     */
    public static boolean checkAllNotNull(Iterable<?>... iterables)
    {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Merges two iterables into a single, sorted List by comparator
     * @param a the {@link Iterable} to collate
     * @param b the {@link Iterable} to collate
     * @param comparator the {@link Comparator} to compare the elements to determine the order
     * @param <T> the type of the elements
     * @return List<T> the collated {@link List} of the two {@link Iterable}
     * @throws NullPointerException if iterables is null
     */
    public static <T> List<T> collate(Iterable<? extends T> a, Iterable<? extends T> b,
                                      Comparator<? super T> comparator)
    {
        return collate(a, b, comparator, true);
    }

    /** Checks if the object is contained in the given iterable.
     * Instead of dealing with a loop, this method with two parameters can be used for a quick check.
     * @param iterable the {@link Iterable} iterable to check if the object is contained
     * @param object the object to check if it is contained in the iterable
     * @return  true if the object is contained in the iterable, false otherwise
     * @param <T> the type of the elements in the iterable
     * @throws NullPointerException if collection or object is null
     */
    public static <T> boolean contains(Iterable<T> iterable, Object object)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Merges two iterables into a single, sorted List by comparator
     * @param a the {@link Iterable} to collate
     * @param b the {@link Iterable} to collate
     * @param includeDuplicates if true, duplicate elements are included
     * @param comparator the {@link Comparator} to compare the elements to determine the order
     * @param <T> the type of the elements
     * @return List<T> the collated {@link List} of the two {@link Iterable}
     * @throws NullPointerException if iterables is null
     */
    public static <T> List<T> collate(Iterable<? extends T> a, Iterable<? extends T> b,
                                      Comparator<? super T> comparator, boolean includeDuplicates)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    /**
     * Combines multiple iterables into a single iterable
     * @param iterable the {@link Iterable} to combine
     * @return iterable the combined {@link Iterable}
     * @param <T>  the type of the elements in the iterable
     * @throws NullPointerException if iterable is null
     */
    public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> iterable)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Returns an iterable whose iterators cycle indefinitely over the elements of iterable.
    public static <T> Iterable<T> cycle(Iterable<T> iterable)
    {
        throw new UnsupportedOperationException("Will be written by Oğuz Karan");
    }


    /**
     * Find the first element in iterable that satisfies the given predicate.
     * @param iterable the {@link Iterable} to searched
     * @param predicate the {@link Predicate} to search by given condition
     * @return {@link Optional} containing the first element in iterable that satisfies the given predicate
     * @param <T> the type of the elements in the iterable
     * @throws NullPointerException if iterable or predicate is null
     */
    static <T> Optional<T> findFirst(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * It is used to search for a component of a specific type within collections containing multiple types.
     * @param  collection   collection containing elements to search through
     * @param  types        array of types to search for
     * @return              the found object of the specified type
     * @throws NullPointerException if the collection is null
     */
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
