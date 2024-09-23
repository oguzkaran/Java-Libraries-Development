package org.csystem.util.data.service.exception;

/**
 * DataServiceException class which can wrap the exception in service
 * This Service has two ctor and one method
 * {@code getMessage}
 * that service a result of types {@code String}.
 * @author Oğuz Karan
 * @version 3.3.0
 */

public class DataServiceException extends RuntimeException {
    public DataServiceException(String message)
    {
        this(message, null);
    }

    public DataServiceException(String message, Throwable cause)
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
