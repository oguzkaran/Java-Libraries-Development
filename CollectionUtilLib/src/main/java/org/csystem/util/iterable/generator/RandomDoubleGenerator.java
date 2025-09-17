/*
 * Copyleft (c) 1993 by C and System Programmers Association (CSD)
 * All Rights Free
 */
package org.csystem.util.iterable.generator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.DoubleSupplier;

/**
 * An iterable generator class for producing random double values.
 * <p>
 * This class allows the creation of a sequence of random double values using a {@link DoubleSupplier}.
 * The number of values to generate is specified at construction.
 * Factory methods are provided for generating values within a range, with a custom {@link Random}, or with a custom supplier.
 * </p>
 *
 *  @author CSD Development group
 */
public final class RandomDoubleGenerator implements Iterable<Double> {
    private final int m_count;
    private final DoubleSupplier m_supplier;

    /**
     * Constructs a RandomDoubleGenerator object.
     *
     * @param count The number of double values to generate
     * @param supplier The DoubleSupplier that provides double values
     */
    private RandomDoubleGenerator(int count, DoubleSupplier supplier)
    {
        m_count = count;
        m_supplier = supplier;
    }

    /**
     * Creates a RandomDoubleGenerator instance that generates random double values within the specified range.
     *
     * @param count The number of double values to generate
     * @param min The lower bound (inclusive) of the generated values
     * @param max The upper bound (exclusive) of the generated values
     * @return A RandomDoubleGenerator instance
     */
    public static RandomDoubleGenerator of(int count, double min, double max)
    {
        return of(new Random(), count, min, max);
    }

    /**
     * Creates a RandomDoubleGenerator instance using a custom Random object and range.
     *
     * @param random The Random object to use for generating values
     * @param count The number of double values to generate
     * @param min The lower bound (inclusive) of the generated values
     * @param max The upper bound (exclusive) of the generated values
     * @return A RandomDoubleGenerator instance
     */
    public static RandomDoubleGenerator of(Random random, int count, double min, double max)
    {
        return of(count, () -> random.nextDouble() * (max - min) + min);
    }

    /**
     * Creates a RandomDoubleGenerator instance using a custom DoubleSupplier.
     *
     * @param count The number of double values to generate
     * @param supplier The DoubleSupplier that provides double values
     * @return A RandomDoubleGenerator instance
     */
    public static RandomDoubleGenerator of(int count, DoubleSupplier supplier)
    {
        return new RandomDoubleGenerator(count, supplier);
    }

    /**
     * Returns the number of double values to be generated.
     *
     * @return the count of double values
     */
    public int getCount()
    {
        return m_count;
    }
    /**
     * Returns an iterator over elements of type {@code Double}.
     * The iterator generates random double values using the provided {@code DoubleSupplier}.
     * The number of values generated is determined by {@code m_count}.
     *
     * @return an Iterator that produces random double values
     */
    @Override
    public Iterator<Double> iterator()
    {
        return new Iterator<>() {
            private int m_count = -1;

            /**
             * Checks if there are more double values to generate.
             *
             * @return {@code true} if more values can be generated, {@code false} otherwise
             */
            @Override
            public boolean hasNext()
            {
                return m_count + 1 < RandomDoubleGenerator.this.m_count;
            }

            /**
             * Returns the next random double value.
             *
             * @return the next random double value
             * @throws NoSuchElementException if no more values are available
             */
            @Override
            public Double next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("No value to generate");

                ++m_count;
                return m_supplier.getAsDouble();
            }
        };
    }
}