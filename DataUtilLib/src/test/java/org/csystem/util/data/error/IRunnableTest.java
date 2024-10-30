package org.csystem.util.data.error;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IRunnableTest {

    @Test
    void testRunnableExecutesSuccessfully() throws Exception {
        IRunnable runnable = () -> {
            System.out.println("Task executed successfully");
        };

        assertDoesNotThrow(runnable::run);
    }

    @Test
    void testRunnableThrowsException() {
        IRunnable runnable = () -> {
            throw new Exception("Task failed with exception");
        };

        Exception exception = assertThrows(Exception.class, runnable::run);
        assertEquals("Task failed with exception", exception.getMessage());
    }
}