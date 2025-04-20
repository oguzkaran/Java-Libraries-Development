/**
 * Unit tests for {@link StreamException} class.
 */
package org.csystem.util.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link StreamException} class.
 */
public class StreamExceptionTest {

    @Test
    void testDefaultConstructor()
    {
        StreamException exception = new StreamException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testMessageConstructor()
    {
        String message = "Stream error occurred";
        StreamException exception = new StreamException(message);
        assertEquals("{message : " + message + "}", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testMessageAndCauseConstructor()
    {
        String message = "Stream error occurred";
        Throwable cause = new RuntimeException("Root cause");
        StreamException exception = new StreamException(message, cause);
        assertEquals("{message : " + message + ", causeMessage : " + cause.getMessage() + "}", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}