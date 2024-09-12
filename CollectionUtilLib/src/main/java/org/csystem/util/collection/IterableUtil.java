package org.csystem.util.collection;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
     * Checks if all elements in the specified {@link Iterable} are distinct.
     * @param iterable the {@link Iterable} to check if all elements are distinct
     * @return true if all elements are distinct otherwise return false
     * @param <T> the type of the elements in the {@link Iterable}
     * @throws NullPointerException if {@code iterable} is null
     */
    public static <T> boolean areAllDistinct(Iterable<? extends T> iterable)
    {
        Objects.requireNonNull(iterable,"Iterable cannot be null");

        var iterableList = StreamSupport.stream(iterable.spliterator(), false)
            .collect(Collectors.toList());

        return iterableList.size() == new HashSet<T>(iterableList).size();
    }

    /**
     * Checks if all elements in the specified {@link Iterable} are not null.
     * @param iterables the {@link Iterable} to check if all elements are not null
     * @return true if all elements are not null otherwise return false
     * @param <?> the type of the elements in the {@link Iterable}
     * @throws NullPointerException if  {@code iterables} is null
     */
    public static boolean checkAllNotNull(Iterable<?>... iterables)
    {
       Objects.requireNonNull(iterables,"Iterable cannot be null");

       return Stream.of(iterables).flatMap(i -> StreamSupport.stream(i.spliterator(), false))
           .allMatch(Objects::nonNull);

    }

    /**
     * Merges two iterables into a single, sorted List by comparator
     * @param a the {@link Iterable} to collate
     * @param b the {@link Iterable} to collate
     * @param comparator the {@link Comparator} to compare the elements to determine the order
     * @param <T> the type of the elements
     * @return List<T> the collated {@link List} of the two {@link Iterable}
     * @throws NullPointerException if {@code a}, {@code b} or {@code comparator} is null
     */
    public static <T> List<T> collate(Iterable<? extends T> a, Iterable<? extends T> b,
                                      Comparator<? super T> comparator)
    {
        Objects.requireNonNull(a, "a cannot be null");
        Objects.requireNonNull(b, "b cannot be null");
        Objects.requireNonNull(comparator, "comparator cannot be null");

        return collate(a, b, comparator, true);
    }

    /** Checks if the object is contained in the given iterable.
     * Instead of dealing with a loop, this method with two parameters can be used for a quick check.
     * @param iterable the {@link Iterable} iterable to check if the object is contained
     * @param object the object to check if it is contained in the iterable
     * @return  true if the object is contained in the iterable, false otherwise
     * @param <T> the type of the elements in the iterable
     * @throws NullPointerException if {@code iterable} or {@code object} is null
     */
    public static <T> boolean contains(Iterable<T> iterable, Object object)
    {
        Objects.requireNonNull(iterable, "iterable cannot be null");
        Objects.requireNonNull(object, "object cannot be null");
        //TODO: How to approach this?
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
     * @throws NullPointerException if {@code a}, {@code b} or {@code comparator} is null
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
     * @throws NullPointerException if {@code iterable} is null
     */
    public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> iterable)
    {
        Objects.requireNonNull(iterable, "iterable cannot be null");

        return StreamSupport.stream(iterable.spliterator(), false)
            .flatMap(i -> StreamSupport.stream(i.spliterator(), false))
            .collect(Collectors.toList());

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
     * @throws NullPointerException if {@code predicate} or {@code iterable} is null
     */
    static <T> Optional<T> findFirst(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        Objects.requireNonNull(iterable, "iterable cannot be null");
        Objects.requireNonNull(predicate, "predicate cannot be null");

        return StreamSupport.stream(iterable.spliterator(), false)
            .filter(predicate)
            .findFirst();

    }

    /**
     * It is used to search for a component of a specific type within collections containing multiple types.
     * @param  collection   the {@link Iterable} containing elements to search through
     * @param  types        array of types to search for
     * @param <?>           the type of the object to search for
     * @return              the found object of the specified type
     * @throws NullPointerException if the {@code collection} or {@code types} is null
     */
    public static Object findValueOfType(Iterable<?> collection, Class<?>[] types)
    {
        Objects.requireNonNull(collection, "collection cannot be null");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Calculates the frequency of a given object in an iterable collection.
     * @param  iterable  the {@link Iterable} to search for the object
     * @param  obj       the object to search for
     * @param  <E>       the type of elements in the collection
     * @param  <T>      the type of the object to search for, which must be a subtype of E
     * @return           the frequency of the object in the collection
     * @throws NullPointerException if the {@code iterable} or {@code obj} is null
     */
    public static <E, T extends E> int frequency(Iterable<E> iterable, T obj)
    {
        Objects.requireNonNull(iterable, "iterable cannot be null");
        Objects.requireNonNull(obj, "obj cannot be null");

        return (int)StreamSupport.stream(iterable.spliterator(), false)
                .filter(obj::equals).count();
    }

    /**
     * Returns a map containing the cardinality of each element in the given iterable.
     * @param  coll the {@link Iterable} to get the cardinality map from
     * @param  <T>  the type of elements in the iterable
     * @return      map the {@link Map} containing the cardinality of each element in the iterable
     * @throws      NullPointerException if {@code coll} is null
     */
    public static <T> Map<T, Integer> getCardinalityMap(Iterable<? extends T> coll)
    {
        Objects.requireNonNull(coll, "coll cannot be null");

        return StreamSupport.stream(coll.spliterator(), false)
                .collect(Collectors.toMap(Function.identity(), t -> 1, Integer::sum));
    }


    /**
     * Returns a collection containing the intersection of two iterables. The intersection is the set of elements that
     * are common to both iterables.
     * @param  a   the first {@link Iterable} to get the intersection from
     * @param  b   the second {@link Iterable} to get the intersection from
     * @param  <T> the type of elements in the iterables
     * @return     a collection containing the intersection of the two iterables
     * @throws NullPointerException if {@code a} or {@code a} is null
     */
    public static <T> Collection<T> intersection(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        Objects.requireNonNull(a, "a cannot be null");
        Objects.requireNonNull(b, "b cannot be null");

        return StreamSupport.stream(a.spliterator(), false)
                .filter(t -> StreamSupport.stream(b.spliterator(), false)
                .anyMatch(t::equals)).collect(Collectors.toSet());
    }


    /**
     * Checks if the first collection is a subset of the second collection.
     * @param a   the {@link Collection} to check as a subset
     * @param b   the {@link Collection} to check against
     * @return    true if {@code a} is a subset of {@code b}, false otherwise
     * @throws NullPointerException if {@code a} or {@code a} is null
     */
    public static boolean isSubCollection(Collection<?> a, Collection<?> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }




    /**
     * Returns a limited iterable from the given iterable.
     * @param  iterable  the {@link Iterable} to limit
     * @param  limitSize the maximum number of elements to return
     * @param  <T>       the type of elements in the iterable
     * @return          an iterable containing at most limitSize elements from the given iterable
     * @throws          NullPointerException if {@code iterable} is null
     * @throws          IllegalArgumentException if {@code limitSize} is negative
     */
    public static <T> Iterable<T> limit(Iterable<T> iterable, int limitSize)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    static <T> Iterable<T> mergeSorted(Iterable<? extends Iterable<? extends T>> iterables,
                                      Comparator<? super T> comparator)
    {
        throw new UnsupportedOperationException("Will be written by Oğuz Karan");
    }


    public static <T> Iterable<List<T>> paddedPartition(Iterable<T> iterable, int size)
    {
        throw new UnsupportedOperationException("Will be written by Oğuz Karan");
    }


    /**
     * Retains only the elements in the given iterable that are also contained in the given collection.
     * @param  removeFrom    the {@link Iterable} from which elements will be removed
     * @param  elementsToRetain the {@link Collection} containing the elements to retain
     * @param <?>         the type of elements in the {@code removeFrom} and {@code elementsToRetain}
     * @return               true if any elements were removed from the iterable, false otherwise
     * @throws UnsupportedOperationException if the {@code iterable} does not support the remove operation
     * @throws NullPointerException if {@code removeFrom} or {@code elementsToRetain} is null
     */
    public static boolean retainAll(Iterable<?> removeFrom, Collection<?> elementsToRetain)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns a list containing the result of applying the given function to each element of the given iterable.
     * @param iterable the {@link Iterable} to transform
     * @param func the {@link Function} function to apply to each element
     * @param <T> the type of elements in the iterable
     * @param <R> the type of elements in the list
     * @return a {@link List} containing the result of applying the given function to each element of the given iterable
     * @throws NullPointerException if {@code iterable} or {@code func} is null
     */
    public static <T, R> List<R> toList(Iterable<T> iterable, Function<? super T, R> func)
    {
        return toList(iterable, func, false);
    }

    /**
     * Returns an iterable containing the result of applying the given function to each element of the given iterable.
     * @param iterable the {@link Iterable} to transform
     * @param func the {@link Function} function to apply to each element
     * @param <T> the type of elements in the iterable
     * @param <R> the type of elements in the Iterable
     * @return an {@link Iterable} containing the result of applying the given function to each element of the given iterable
     * @throws NullPointerException if {@code iterable} or {@code func} is null
     */
    public static <T, R> Iterable<R> toIterable(Iterable<T> iterable, Function<? super T, R> func)
    {
        return toIterable(iterable, func, false);
    }

    /**
     * Returns a list containing the result of applying the given function to each element of the given iterable.
     * @param iterable the {@link Iterable} to transform
     * @param func the {@link Function} function to apply to each element
     * @param parallel whether to use parallel streams
     * @param <T> the type of elements in the iterable
     * @param <R> the type of elements in the list
     * @return a {@link List} containing the result of applying the given function to each element of the given iterable
     * @throws NullPointerException if {@code iterable} or {@code func} is null
     */
    public static <T, R> List<R> toList(Iterable<T> iterable, Function<? super T, R> func, boolean parallel)
    {
        return StreamSupport.stream(iterable.spliterator(), parallel).map(func).collect(Collectors.toList());
    }

    /**
     * Returns an iterable containing the result of applying the given function to each element of the given iterable.
     * @param iterable the {@link Iterable} to transform
     * @param func the {@link Function} function to apply to each element
     * @param parallel whether to use parallel streams
     * @param <T> the type of elements in the iterable
     * @param <R> the type of elements in the Iterable
     * @return an {@link Iterable} containing the result of applying the given function to each element of the given iterable
     * @throws NullPointerException if {@code iterable} or {@code func} is null
     */
    public static <T, R> Iterable<R> toIterable(Iterable<T> iterable, Function<? super T, R> func, boolean parallel)
    {
        return toList(iterable, func, parallel);
    }

    /**
     * Performs an action for each element of the given iterable.
     * @param iterable the {@link Iterable} to loop over
     * @param consumer the {@link Consumer} action to perform on each element
     * @param <T> the type of elements in the iterable
     * @throws NullPointerException if {@code iterable} or {@code consumer} is null
     */
    public static <T> void forEach(Iterable<T> iterable, Consumer<? super T> consumer)
    {
        forEach(iterable, consumer, false);
    }

    /**
     * Performs an action for each element of the given iterable, optionally using parallel streams.
     * @param iterable the {@link Iterable} to loop over
     * @param consumer the {@link Consumer} action to perform on each element
     * @param <T> the type of elements in the iterable and in the consumer
     * @param parallel whether to use parallel streams
     * @throws NullPointerException if {@code iterable} or {@code consumer} is null
     */
    public static <T> void forEach(Iterable<T> iterable, Consumer<? super T> consumer, boolean parallel)
    {
        StreamSupport.stream(iterable.spliterator(), parallel).forEach(consumer);
    }


    /**
     * Returns a {@link Collection} containing the union of the given collections which were distincted.
     * @param  a  the first {@link Iterable} to include in the union
     * @param  b  the second {@link Iterable} to include in the union
     * @param <T> the type of the elements in the collection
     * @return  a {@link Collection} containing the union of the given collections
     * @throws NullPointerException if the both parameters are null
     */
    public static <T> Collection<T> union(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        if (a == null && b == null)
            throw new NullPointerException("Null pointer exception..!");
        else if (a == null)
            return StreamSupport.stream(b.spliterator(), false).collect(Collectors.toList());
        else if (b == null)
            return StreamSupport.stream(a.spliterator(), false).collect(Collectors.toList());

        return Stream.concat(StreamSupport.stream(a.spliterator(), false), StreamSupport.stream(b.spliterator(), false))
                .distinct().collect(Collectors.toList());
    }

    // TODO: THESE 2 METHODS WILL BE DISCUSED LATER

    /**
     * Returns a {@link Collection} containing all elements from the given collections.
     * @param a  the first {@link Iterable} to include in the collection
     * @param b  the second {@link Iterable} to include in the collection
     * @param <T> the type of the elements in the collection
     * @return  a {@link Collection} containing all elements from the given collections
     * @throws NullPointerException if the both parameters are null
     */
    public static <T> Collection<T> unionAll(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        if (a == null && b == null)
            throw new NullPointerException("Null pointer exception..!");
        else if (a == null)
            return StreamSupport.stream(b.spliterator(), false).collect(Collectors.toList());
        else if (b == null)
            return StreamSupport.stream(a.spliterator(), false).collect(Collectors.toList());

        return Stream.concat(StreamSupport.stream(a.spliterator(), false), StreamSupport.stream(b.spliterator(), false))
                .collect(Collectors.toList());
    }
}
