/*
 * Copyleft (c) 1993 by C and System Programmers Association (CSD)
 * All Rights Free
 */
package org.csystem.util.iterable.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.IntUnaryOperator;

/**
 * Represents a range of integers, providing an iterable sequence from min to max.
 * The range can be customized with a step or an IntUnaryOperator for value progression.
 *
 * @author CSD Development group
 * @since 23.07.2021
 */
public class IntRange implements Iterable<Integer> {
    private final int m_min;
    private final int m_max;
    private final IntUnaryOperator m_intUnaryOperator;

    /**
     * Constructs an IntRange with the specified minimum, maximum, and progression operator.
     *
     * @param min the starting value of the range (inclusive)
     * @param max the ending value of the range (inclusive)
     * @param intUnaryOperator the operator to determine the next value in the range
     * @throws IllegalArgumentException if min is greater than max
     */
    private IntRange(int min, int max, IntUnaryOperator intUnaryOperator)
    {
        if (min > max)
            throw new IllegalArgumentException("Invalid Arguments");

        m_min = min;
        m_max = max;
        m_intUnaryOperator = intUnaryOperator;
    }

    /**
     * Creates an open range [min, max) with a default step of 1.
     *
     * @param min the starting value (inclusive)
     * @param max the ending value (exclusive)
     * @return an IntRange instance
     */
    public static IntRange of(int min, int max)
    {
        return of(min, max, 1);
    }

    /**
     * Creates an open range [min, max) with a specified step.
     * <p>
     * If {@code step <= 1}, the step defaults to {@code 1}.
     * </p>
     *
     * @param min the starting value (inclusive)
     * @param max the ending value (exclusive)
     * @param step the increment value; if less than or equal to 1, defaults to 1
     * @return an IntRange instance
     */
    public static IntRange of(int min, int max, int step)
    {
        return ofClosed(min, max - 1, step);
    }

    /**
     * Creates an open range [min, max) with a custom progression operator.
     *
     * @param min the starting value (inclusive)
     * @param max the ending value (exclusive)
     * @param intUnaryOperator the operator to determine the next value
     * @return an IntRange instance
     */
    public static IntRange of(int min, int max, IntUnaryOperator intUnaryOperator)
    {
        return ofClosed(min, max - 1, intUnaryOperator);
    }

    /**
     * Creates a closed range [min, max] with a default step of 1.
     *
     * @param min the starting value (inclusive)
     * @param max the ending value (inclusive)
     * @return an IntRange instance
     */
    public static IntRange ofClosed(int min, int max)
    {
        return ofClosed(min, max, 1);
    }

    /**
     * Creates a closed range [min, max] with a specified step.
     * <p>
     * If {@code step <= 1}, the step defaults to {@code 1}.
     * </p>
     *
     * @param min the starting value (inclusive)
     * @param max the ending value (inclusive)
     * @param step the increment value; if less than or equal to 1, defaults to 1
     * @return an IntRange instance
     */
    public static IntRange ofClosed(int min, int max, int step)
    {
        return ofClosed(min, max, (step <= 1) ? (val -> val + 1) : (val -> val + step));
    }

    /**
     * Creates a closed range [min, max] with a custom progression operator.
     *
     * @param min the starting value (inclusive)
     * @param max the ending value (inclusive)
     * @param intUnaryOperator the operator to determine the next value
     * @return an IntRange instance
     */
    public static IntRange ofClosed(int min, int max, IntUnaryOperator intUnaryOperator)
    {
        return new IntRange(min, max, intUnaryOperator);
    }

    /**
     * Gets the minimum value of the range.
     *
     * @return the minimum value
     */
    public int getMin()
    {
        return m_min;
    }

    /**
     * Gets the maximum value of the range.
     *
     * @return the maximum value
     */
    public int getMax()
    {
        return m_max;
    }

    /**
     * Returns an iterator over the elements in the range.
     *
     * @return an Iterator of Integer values
     */
    @Override
    public Iterator<Integer> iterator()
    {
        return new Iterator<>() {
            int m_val = m_min;

            /**
             * Checks if there are more elements in the range.
             *
             * @return true if there are more elements, false otherwise
             */
            @Override
            public boolean hasNext()
            {
                return m_val <= m_max;
            }

            /**
             * Returns the next element in the range.
             * <p>
             * Note: If the provided {@link IntUnaryOperator} does not eventually
             * produce a value greater than {@code max}, the iteration may never terminate.
             * </p>
             *
             * @return the next integer in the range
             * @throws NoSuchElementException if no more elements are available
             */

            @Override
            public Integer next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("No such value");

                int val = m_val;

                m_val = m_intUnaryOperator.applyAsInt(m_val);

                return val;
            }
        };
    }
}