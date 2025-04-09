package org.csystem.util.data.repository.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryExceptionTest {

    @Test
    void testRepositoryExceptionWithMessage() {
        RepositoryException exception = new RepositoryException("Test message");

        assertEquals("Message: Test message ", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testRepositoryExceptionWithMessageAndCause() {
        Throwable cause = new Exception("Cause message");

        RepositoryException exception = new RepositoryException("Test message", cause);

        assertEquals("Message: Test message , Cause Message:Cause message", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testRepositoryExceptionWithoutCauseMessage() {
        RepositoryException exception = new RepositoryException("Test message", null);

        assertEquals("Message: Test message ", exception.getMessage());
        assertNull(exception.getCause());
    }
}