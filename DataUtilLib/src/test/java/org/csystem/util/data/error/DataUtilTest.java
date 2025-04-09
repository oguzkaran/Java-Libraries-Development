package org.csystem.util.data.error;

import org.csystem.util.data.repository.exception.RepositoryException;
import org.csystem.util.data.service.exception.DataServiceException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.function.Consumer;

class DataUtilTest {

    @Test
    void testDoForRepository_SupplierSuccess() throws Exception {
        String successStatus = "Success";
        ISupplier<String> supplier = mock(ISupplier.class);
        when(supplier.get()).thenReturn(successStatus);

        String result = DataUtil.doForRepository(supplier, "Error message");

        assertEquals(successStatus, result);
        verify(supplier, times(1)).get();
    }

    @Test
    void testDoForRepository_SupplierThrowsException() throws Exception {
        ISupplier<String> supplier = mock(ISupplier.class);
        when(supplier.get()).thenThrow(new RuntimeException("Test exception"));

        RepositoryException exception = assertThrows(RepositoryException.class, () ->
                DataUtil.doForRepository(supplier, "Error occurred"));

        assertEquals("Message: Error occurred , Cause Message:Test exception", exception.getMessage());
        assertTrue(exception.getCause() instanceof RuntimeException);
        verify(supplier, times(1)).get();
    }

    @Test
    void testDoForRepository_SupplierWithConsumer() throws Exception {
        ISupplier<String> supplier = mock(ISupplier.class);
        Consumer<Throwable> consumer = mock(Consumer.class);
        when(supplier.get()).thenThrow(new RuntimeException("Test exception"));

        RepositoryException exception = assertThrows(RepositoryException.class, () ->
                DataUtil.doForRepository(supplier, consumer, "Error occurred"));

        assertEquals("Message: Error occurred , Cause Message:Test exception", exception.getMessage());
        verify(supplier, times(1)).get();
        verify(consumer, times(1)).accept(any(Throwable.class));
    }

    @Test
    void testDoForRepository_RunnableSuccess() throws Exception {
        IRunnable action = mock(IRunnable.class);

        DataUtil.doForRepository(action, "Error message");

        verify(action, times(1)).run();
    }

    @Test
    void testDoForRepository_RunnableThrowsException() throws Exception {
        IRunnable action = mock(IRunnable.class);
        doThrow(new RuntimeException("Test exception")).when(action).run();

        RepositoryException exception = assertThrows(RepositoryException.class, () ->
                DataUtil.doForRepository(action, "Error occurred"));

        assertEquals("Message: Error occurred , Cause Message:Test exception", exception.getMessage());
        assertTrue(exception.getCause() instanceof RuntimeException);
        verify(action, times(1)).run();
    }

    @Test
    void testDoForDataService_SupplierSuccess() throws Exception {
        ISupplier<String> supplier = mock(ISupplier.class);
        when(supplier.get()).thenReturn("Success");

        String result = DataUtil.doForDataService(supplier, "Error message");

        assertEquals("Success", result);
        verify(supplier, times(1)).get();
    }

    @Test
    void testDoForDataService_SupplierThrowsRepositoryException() throws Exception {
        ISupplier<String> supplier = mock(ISupplier.class);
        when(supplier.get()).thenThrow(new RepositoryException("Repository issue", new RuntimeException()));

        DataServiceException exception = assertThrows(DataServiceException.class, () ->
                DataUtil.doForDataService(supplier, "Error occurred"));

        assertEquals("Message: Error occurred , Cause Message:null", exception.getMessage());
        assertTrue(exception.getCause() instanceof RuntimeException);
        verify(supplier, times(1)).get();
    }

    @Test
    void testDoForDataService_RunnableThrowsException() throws Exception {
        IRunnable action = mock(IRunnable.class);
        doThrow(new RuntimeException("Test exception")).when(action).run();

        DataServiceException exception = assertThrows(DataServiceException.class, () ->
                DataUtil.doForDataService(action, "Error occurred"));

        assertEquals("Message: Error occurred , Cause Message:Test exception", exception.getMessage());
        assertTrue(exception.getCause() instanceof RuntimeException);
        verify(action, times(1)).run();
    }

    @Test
    void testDoForDataService_RunnableWithConsumer() throws Exception {
        IRunnable action = mock(IRunnable.class);
        Consumer<Throwable> consumer = mock(Consumer.class);
        doThrow(new RuntimeException("Test exception")).when(action).run();

        DataServiceException exception = assertThrows(DataServiceException.class, () ->
                DataUtil.doForDataService(action, consumer, "Error occurred"));

        assertEquals("Message: Error occurred , Cause Message:Test exception", exception.getMessage());
        verify(action, times(1)).run();
        verify(consumer, times(1)).accept(any(Throwable.class));
    }
}