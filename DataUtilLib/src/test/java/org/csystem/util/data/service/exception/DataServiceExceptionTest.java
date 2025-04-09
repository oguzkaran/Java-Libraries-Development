package org.csystem.util.data.service.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceExceptionTest {

    @Test
    void testExceptionWithMessageOnly() {
        String message = "Data service error occurred";
        DataServiceException exception = new DataServiceException(message);

        assertEquals("Message: " + message + " ", exception.getMessage());
    }

    @Test
    void testExceptionWithMessageAndCause() {
        String message = "Data service error occurred";
        String failedMessage = "Database connection failed";

        Throwable cause = new RuntimeException(failedMessage);
        DataServiceException exception = new DataServiceException(message, cause);

        assertEquals("Message: " + message + " , Cause Message:" + failedMessage, exception.getMessage());

        assertEquals(cause, exception.getCause());
    }

    @Test
    void testExceptionWithNullCause() {
        String message = "Data service error occurred";
        DataServiceException exception = new DataServiceException(message, null);

        assertEquals("Message: " + message + " ", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testExceptionChaining() {
        String message = "Underlying issue";
        Throwable cause = new RuntimeException(message);
        DataServiceException exception = new DataServiceException("Top-level service error", cause);

        Throwable retrievedCause = exception.getCause();

        assertNotNull(retrievedCause);
        assertEquals(message, retrievedCause.getMessage());
    }
}