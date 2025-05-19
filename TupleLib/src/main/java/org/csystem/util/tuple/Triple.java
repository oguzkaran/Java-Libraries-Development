package org.csystem.util.tuple;

/**
 * Represents an immutable triple (tuple) of three values.
 * <p>
 * This class can be used to hold three related objects of possibly different types.
 *
 * @param <F> the type of the first element
 * @param <S> the type of the second element
 * @param <T> the type of the third element
 */
public class Triple<F, S, T> {
    private final F m_first;
    private final S m_second;
    private final T m_third;

    /**
     * Creates a new {@code Triple} with the specified values.
     *
     * @param first  the first value
     * @param second the second value
     * @param third  the third value
     */
    public Triple(F first, S second, T third)
    {
        m_first = first;
        m_second = second;
        m_third = third;
    }

    /**
     * Static factory method for creating a {@code Triple} instance.
     *
     * @param first  the first value
     * @param second the second value
     * @param third  the third value
     * @param <F>    the type of the first value
     * @param <S>    the type of the second value
     * @param <T>    the type of the third value
     * @return a new {@code Triple} containing the specified values
     */
    public static <F, S, T> Triple<F, S, T> of(F first, S second, T third)
    {
        return new Triple<>(first, second, third);
    }

    /**
     * Returns the first value of the triple.
     *
     * @return the first element
     */
    public F getFirst()
    {
        return m_first;
    }

    /**
     * Returns the second value of the triple.
     *
     * @return the second element
     */
    public S getSecond()
    {
        return m_second;
    }

    /**
     * Returns the third value of the triple.
     *
     * @return the third element
     */
    public T getThird()
    {
        return m_third;
    }

    /**
     * Returns a string representation of the triple in the format (first, second, third).
     *
     * @return a string representation of this triple
     */
    @Override
    public String toString()
    {
        return String.format("(%s, %s, %s)", m_first, m_second, m_third);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two triples are equal if their corresponding elements are equal.
     *
     * @param other the object to compare with
     * @return {@code true} if this object is the same as the {@code other} argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Triple))
            return false;

        var t = (Triple<F, S, T>)other;

        return m_first.equals(t.m_first) &&
                m_second.equals(t.m_second) &&
                m_third.equals(t.m_third);
    }
}
