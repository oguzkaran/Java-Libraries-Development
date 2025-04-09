package org.csystem.util.data.service.exception;

/**
 * Exception thrown to indicate an error in the data service layer.
 *
 * <p>This exception is typically used to wrap lower-level exceptions, providing additional context and
 * a more meaningful message for service-layer errors.</p>
 *
 * <p>Example usage</p>
 *
 * <pre>
 *     {@code
 *     // This method simulates a report generation process that could fail due to service issues in service layer.
 *
 *      public String generateReport(String topic) {
 *         try {
 *             // Simulating report generation logic
 *             if (topic == null || topic.isEmpty()) {
 *                 throw new IllegalArgumentException("The topic cannot be null or empty");
 *             }
 *
 *             // Simulating a potential failure in the service layer (e.g., connection error)
 *             if (Math.random() > 0.5) {
 *                 throw new RuntimeException("Failed to connect to the writing service");
 *             }
 *
 *             // Return the generated report
 *             return "Generated English report for the topic: " + topic;
 *         }
 *         catch (RuntimeException ex) {
 *             // Wrap the original exception into a DataServiceException
 *             throw new DataServiceException("Error while generating the report for topic: " + topic, ex);
 *         }
 *      }
 *
 *     }
 * </pre>
 *
 * <p>
 *     The DataServiceException is generally used to handle exceptions that occur in the service layer of an application,
 * <b>where data is being manipulated, accessed, or processed.</b> It abstracts lower-level exceptions (like database, file I/O,
 * external API, or security-related issues) and provides a clear, meaningful context about the failure, allowing the
 * application to handle service layer errors in a standardized way.
 *
 * <p><b>Common Scenarios Where DataServiceException Might Be Used:</b></p>
 * <ul>
 *     <li><b>1. Database Connection Failure:</b> </li>
 *     <li><b>2. Data Access Issues:</b> </li>
 *     <li><b>3. External API Failure:</b> </li>
 *     <li><b>4. Data Parsing or Transformation Errors:</b> </li>
 *     <li><b>5. File I/O Issues:</b> </li>
 *     <li><b>6. Service Layer Logic Errors:</b> </li>
 *     <li><b>7. Security or Authentication Failures:</b> </li>
 *     <li><b>8. Timeouts in Service Layer:</b> </li>
 *     <li><b>9. Concurrency Issues:</b> </li>
 * </ul>
 * </p>

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
