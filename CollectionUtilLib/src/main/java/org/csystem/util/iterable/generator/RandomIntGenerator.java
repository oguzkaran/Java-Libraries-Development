/*
 * Copyleft (c) 1993 by C and System Programmers Association (CSD)
 * All Rights Free
 */
package org.csystem.util.iterable.generator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.IntSupplier;

/**
 * Generates a sequence of random integers.
 * Implements {@link Iterable} to allow iteration over generated values.
 *
 *  @author CSD Development group
 *  @since 23.07.2021
 */
public final class RandomIntGenerator implements Iterable<Integer> {
    private final int m_count;
    private final IntSupplier m_supplier;

    /**
     * Constructs a RandomIntGenerator with the specified count and supplier.
     *
     * @param count     the number of random integers to generate
     * @param supplier  the supplier that provides random integer values
     */
    private RandomIntGenerator(int count, IntSupplier supplier)
    {
        m_count = count;
        m_supplier = supplier;
    }

    /**
     * Creates a RandomIntGenerator that generates random integers in the range [min, max).
     *
     * @param count the number of random integers to generate
     * @param min   the minimum value (inclusive)
     * @param max   the maximum value (exclusive)
     * @return a new RandomIntGenerator instance
     */
    public static RandomIntGenerator of(int count, int min, int max)
    {
        return of(new Random(), count, min, max);
    }

    /**
     * Creates a RandomIntGenerator using the provided Random instance,
     * generating random integers in the range [min, max).
     *
     * @param random the Random instance to use
     * @param count  the number of random integers to generate
     * @param min    the minimum value (inclusive)
     * @param max    the maximum value (exclusive)
     * @return a new RandomIntGenerator instance
     */
    public static RandomIntGenerator of(Random random, int count, int min, int max)
    {
        return of(count, () -> random.nextInt(max - min) + min);
    }

    /**
     * Creates a RandomIntGenerator using the provided IntSupplier.
     *
     * @param count     the number of random integers to generate
     * @param supplier  the supplier that provides random integer values
     * @return a new RandomIntGenerator instance
     */
    public static RandomIntGenerator of(int count, IntSupplier supplier)
    {
        return new RandomIntGenerator(count, supplier);
    }

    /**
     * Returns the number of random integers to generate.
     *
     * @return the count of random integers
     */
    public int getCount()
    {
        return m_count;
    }

    /**
     * Returns an iterator over the generated random integers.
     *
     * @return an Iterator of Integer
     */
    @Override
    public Iterator<Integer> iterator()
    {
        return new Iterator<>() {
            private int m_count = -1;

            /**
             * Checks if there are more random integers to generate.
             *
             * @return true if more integers can be generated, false otherwise
             */
            @Override
            public boolean hasNext()
            {
                return m_count + 1 < RandomIntGenerator.this.m_count;
            }

            /**
             * Returns the next random integer in the sequence.
             *
             * @return the next random integer
             * @throws NoSuchElementException if no more integers are available
             */
            @Override
            public Integer next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("No value to generate");

                ++m_count;
                return m_supplier.getAsInt();
            }
        };
    }
}