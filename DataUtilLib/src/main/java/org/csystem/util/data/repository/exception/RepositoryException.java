package org.csystem.util.data.repository.exception;

/**
 * RepositoryException class which can wrap the exception in repository
 * This Service has two ctor and one method
 * {@code getMessage}
 * that service a result of types {@code String}.
 * The method can throw an {@code RuntimeException} if an error occurs during the result generation.
 * @see RuntimeException
 * @author Oğuz Karan
 * @version 3.3.0
 */

public class RepositoryException extends RuntimeException {
    public RepositoryException(String message)
    {
        this(message, null);
    }

    public RepositoryException(String message, Throwable cause)
    {
        super(message, cause);
    }

    @Override
    public String getMessage()
    {
        var cause = getCause();

        return String.format("Message: %s %s", super.getMessage(), cause != null ? ", Cause Message:" + cause.getMessage() : "");
    }
}
