package org.csystem.util.iterable.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.DoubleUnaryOperator;

/**
 * Represents a range of double values, providing an iterable sequence.
 * The range is defined by a minimum and maximum value, and a step function.
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 *  @author CSD Development group
 */
public class DoubleRange implements Iterable<Double> {
    private final double m_min;
    private final double m_max;
    private final DoubleUnaryOperator m_doubleUnaryOperator;

    /**
     * Constructs a DoubleRange with the specified minimum, maximum, and step function.
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @param doubleUnaryOperator the function to compute the next value in the range
     * @throws IllegalArgumentException if min is greater than max
     */
    private DoubleRange(double min, double max, DoubleUnaryOperator doubleUnaryOperator)
    {
        if (min > max)
            throw new IllegalArgumentException("Invalid Arguments");

        m_min = min;
        m_max = max;
        m_doubleUnaryOperator = doubleUnaryOperator;
    }

    /**
     * Creates a DoubleRange with the specified minimum, maximum, and step value.
     * If step is less than or equal to zero, a default step of 1 is used.
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @param step the step value
     * @return a new DoubleRange instance
     */
    public static DoubleRange of(double min, double max, double step)
    {
        return of(min, max, (step <= 0) ? (val -> val + 1) : (val -> val + step));
    }

    /**
     * Creates a DoubleRange with the specified minimum, maximum, and step function.
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @param doubleUnaryOperator the function to compute the next value in the range
     * @return a new DoubleRange instance
     */
    public static DoubleRange of(double min, double max, DoubleUnaryOperator doubleUnaryOperator)
    {
        return new DoubleRange(min, max, doubleUnaryOperator);
    }

    /**
     * Returns the minimum value of the range.
     *
     * @return the minimum value
     */
    public double getMin()
    {
        return m_min;
    }

    /**
     * Returns the maximum value of the range.
     *
     * @return the maximum value
     */
    public double getMax()
    {
        return m_max;
    }

    /**
     * Returns an iterator over elements of type {@code Double}.
     *
     * @return an Iterator
     */
    @Override
    public Iterator<Double> iterator()
    {
        return new Iterator<Double>() {
            double m_val = m_min;

            /**
             * Returns {@code true} if the iteration has more elements.
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext()
            {
                return  m_val < m_max;
            }

            /**
             * Returns the next element in the iteration.
             * <p>
             * Note: If the provided {@link DoubleUnaryOperator} does not eventually
             * produce a value greater than {@code m_max}, the iteration may never terminate.
             * </p>
             *
             * @return the next Double value in the range
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public Double next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("No such element");

                double val = m_val;

                m_val = m_doubleUnaryOperator.applyAsDouble(m_val);

                return val;
            }
        };
    }
}