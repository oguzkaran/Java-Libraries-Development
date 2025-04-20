package org.csystem.util.io;

/**
 * StreamException is a custom exception class derived from {@link RuntimeException}.
 * This class is used to indicate errors that may occur during stream operations.
 */
public class StreamException extends RuntimeException {
    /**
     * Default constructor. Does not contain a default error message.
     */
    public StreamException()
    {
    }

    /**
     * Creates a {@code StreamException} instance with the specified message.
     *
     * @param message The error message.
     */
    public StreamException(String message)
    {
        this(message, null);
    }

    /**
     * Creates a {@code StreamException} instance with the specified message and cause.
     *
     * @param message The error message.
     * @param cause The underlying exception that caused this error.
     */
    public StreamException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Returns the error message. If a cause is specified, its message is also included.
     *
     * @return The error message and, if applicable, the cause message.
     */
    @Override
    public String getMessage()
    {
        Throwable cause = getCause();

        return String.format("{message : %s%s", super.getMessage(), cause != null ? ", causeMessage : " + cause.getMessage()  + "}" : "}");
    }
}
