package com.karandev.util.net.exception;

/**
 * Unchecked exception class for network-related errors in network applications.
 * <p>This exception is typically thrown to indicate issues encountered during network operations such as socket communication, data transfer, or protocol handling.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public class NetworkException extends RuntimeException {
    /**
     * Constructs a new NetworkException with {@code null} as its detail message.
     */
    public NetworkException()
    {
    }
    
    /**
     * Constructs a new NetworkException with the specified detail message.
     *
     * @param message the detail message.
     */
    public NetworkException(String message)
    {
        this(message, null);
    }

    /**
     * Constructs a new NetworkException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public NetworkException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Returns the detail message string of this exception, including the cause message if present.
     *
     * @return the detail message string of this {@code NetworkException} instance.
     */
    @Override
    public String getMessage()
    {
        Throwable cause = getCause();

        return String.format("{message : %s%s", super.getMessage(), cause != null ? ", causeMessage : " + cause.getMessage()  + "}": "}");
    }
}