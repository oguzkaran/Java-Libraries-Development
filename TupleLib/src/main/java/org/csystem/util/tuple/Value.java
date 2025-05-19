package org.csystem.util.tuple;

/**
 * Represents an immutable container for a single value.
 *
 * <p>This class can be used when you want to treat a single value as a tuple or wrap it
 * in a consistent data structure.</p>
 *
 * @param <T> the type of the value
 */
public class Value<T> {
    private final T m_value;

    /**
     * Constructs a {@code Value} object with the specified value.
     *
     * @param value the value to store
     */
    public Value(T value)
    {
        m_value = value;
    }

    /**
     * Static factory method to create a {@code Value} instance.
     *
     * @param value the value to wrap
     * @param <T>   the type of the value
     * @return a new {@code Value} containing the specified value
     */
    public static <T> Value<T> of(T value)
    {
        return new Value<>(value);
    }

    /**
     * Returns the stored value.
     *
     * @return the wrapped value
     */
    public T getValue()
    {
        return m_value;
    }

    /**
     * Returns a string representation of the value.
     *
     * @return a string representation of the contained value
     */
    @Override
    public String toString()
    {
        return m_value + "";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two {@code Value} objects are equal if their contained values are equal.
     *
     * @param other the object to compare with
     * @return {@code true} if the other object is a {@code Value} and the values are equal;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object other)
    {
        return other instanceof Value && m_value.equals(((Value<T>)other).m_value);
    }
}
