package org.csystem.util.tuple;

/**
 * Represents an immutable pair (tuple) of two values.
 * <p>
 * This class is useful for holding two related values of potentially different types.
 *
 * @param <F> the type of the first element
 * @param <S> the type of the second element
 */
public class Pair<F, S> {
    private final F m_first;
    private final S m_second;

    /**
     * Creates a new {@code Pair} instance with the given values.
     *
     * @param first  the first value
     * @param second the second value
     */
    public Pair(F first, S second)
    {
        m_first = first;
        m_second = second;
    }

    /**
     * A static factory method for creating a {@code Pair} instance.
     *
     * @param first  the first value
     * @param second the second value
     * @param <F>    the type of the first value
     * @param <S>    the type of the second value
     * @return a new {@code Pair} containing the specified values
     */
    public static <F, S> Pair<F, S> of(F first, S second)
    {
        return new Pair<>(first, second);
    }

    /**
     * Returns the first value of the pair.
     *
     * @return the first element
     */
    public F getFirst()
    {
        return m_first;
    }

    /**
     * Returns the second value of the pair.
     *
     * @return the second element
     */
    public S getSecond()
    {
        return m_second;
    }

    /**
     * Returns a string representation of this pair in the format (first, second).
     *
     * @return a string representation of the pair
     */
    @Override
    public String toString()
    {
        return String.format("(%s, %s)", m_first, m_second);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two pairs are equal if both their first and second elements are equal.
     *
     * @param other the reference object with which to compare
     * @return {@code true} if this object is the same as the other argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Pair))
            return false;

        var p = (Pair<F, S>)other;

        return m_first.equals(p.m_first) && m_second.equals(p.m_second);
    }
}
