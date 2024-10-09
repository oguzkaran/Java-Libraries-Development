package org.csystem.util.data.repository.exception;

/**
 * A custom exception that indicates an error occurred in the repository layer.
 * This exception can wrap another exception as its cause, providing detailed error information.
 * @author Oğuz Karan
 * @version 3.3.0
 */

public class RepositoryException extends RuntimeException {

    /**
     * Constructs a new {@code RepositoryException} with the specified detail message.
     *
     * <p>This constructor calls another constructor in the same class that also accepts a cause,
     * but in this case, it passes {@code null} as the cause.</p>
     *
     * @param message the detail message explaining the reason for the exception
     */
    public RepositoryException(String message)
    {
        this(message, null);
    }

    /**
     * Constructs a new {@code RepositoryException} with the specified detail message and cause.
     *
     * <p>This constructor allows both a message and a cause to be specified. The cause is saved for
     * later retrieval by the {@code Throwable.getCause()} method.</p>
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause the underlying cause of the exception, which can be retrieved later with {@link Throwable#getCause()}, or {@code null} if no cause is provided
     */
    public RepositoryException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Returns the detail message string of this exception, including the message of the cause if available.
     *
     * <p>This method overrides {@link Throwable#getMessage()} to include the cause's message (if present),
     * in addition to the exception's main message. If the cause is {@code null}, only the main message is returned.</p>
     *
     * @return a string containing the exception message, and the cause message if applicable
     */
    @Override
    public String getMessage()
    {
        var cause = getCause();

        return String.format("Message: %s %s", super.getMessage(), cause != null ? ", Cause Message:" + cause.getMessage() : "");
    }
}
