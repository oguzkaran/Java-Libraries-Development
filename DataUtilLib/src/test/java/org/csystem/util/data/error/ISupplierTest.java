package org.csystem.util.data.error;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ISupplierTest {

    @Test
    void testSupplierReturnsResultSuccessfully() throws Exception {
        ISupplier<String> supplier = () -> "Test result";

        assertEquals("Test result", supplier.get());
    }

    @Test
    void testSupplierThrowsException() {
        ISupplier<String> supplier = () -> {
            throw new Exception("Failed to get result");
        };

        Exception exception = assertThrows(Exception.class, supplier::get);
        assertEquals("Failed to get result", exception.getMessage());
    }
}
