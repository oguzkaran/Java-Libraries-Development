package org.csystem.util.data.service.exception;

/**
 * Exception thrown to indicate an error in the data service layer.
 *
 * <p>This exception is typically used to wrap lower-level exceptions, providing additional context and
 * a more meaningful message for service-layer errors.</p>
 */

public class DataServiceException extends RuntimeException {
    /**
     * Constructs a new {@code DataServiceException} with the specified detail message.
     *
     * <p>This constructor passes {@code null} as the cause of the exception.</p>
     *
     * @param message the detail message explaining the reason for the exception
     */
    public DataServiceException(String message)
    {
        this(message, null);
    }

    /**
     * Constructs a new {@code DataServiceException} with the specified detail message and cause.
     *
     * <p>This constructor allows both a message and a cause to be specified, with the cause being saved
     * for later retrieval by the {@link Throwable#getCause()} method.</p>
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause the underlying cause of the exception, which can be retrieved later with {@link Throwable#getCause()}, or {@code null} if no cause is provided
     */
    public DataServiceException(String message, Throwable cause)
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
