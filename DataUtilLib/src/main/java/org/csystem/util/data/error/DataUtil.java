package org.csystem.util.data.error;

import org.csystem.util.data.repository.exception.RepositoryException;
import org.csystem.util.data.service.exception.DataServiceException;

import java.util.function.Consumer;

/**
 * DataUtil class that has high order method for exception handling
 * This Service has one ctor and two method
 * {@code doForRepository, doForDataService}
 * that service a result of types {@code void, <R> R}.
 * @author Oğuz Karan
 * @version 3.3.0
 */

public final class DataUtil {
    private DataUtil()
    {}

    public static <R> R doForRepository(ISupplier<R> supplier, String message)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            throw new RepositoryException(message, ex);
        }
    }

    public static <R> R doForRepository(ISupplier<R> supplier, Consumer<Throwable> consumer, String message)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throw new RepositoryException(message, ex);
        }
    }

    public static void doForRepository(IRunnable action, String message)
    {
        try {
            action.run();
        }
        catch (Throwable ex) {
            throw new RepositoryException(message, ex);
        }
    }

    public static void doForRepository(IRunnable action, Consumer<Throwable> consumer, String message)
    {
        try {
            action.run();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throw new RepositoryException(message, ex);
        }
    }

    public static <R> R doForDataService(ISupplier<R> supplier, String message)
    {
        try {
            return supplier.get();
        }
        catch (RepositoryException ex) {
            throw new DataServiceException(message, ex.getCause());
        }
        catch (Throwable ex) {
            throw new DataServiceException(message, ex);
        }
    }

    public static <R> R doForDataService(ISupplier<R> supplier, Consumer<Throwable> consumer, String message)
    {
        try {
            return supplier.get();
        }
        catch (RepositoryException ex) {
            consumer.accept(ex);
            throw new DataServiceException(message, ex.getCause());
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throw new DataServiceException(message, ex);
        }
    }

    public static <R> R doForDataService(ISupplier<R> supplier, Consumer<Throwable> consumerRepository, Consumer<Throwable> consumerOthers, String message)
    {
        try {
            return supplier.get();
        }
        catch (RepositoryException ex) {
            consumerRepository.accept(ex);
            throw new DataServiceException(message, ex.getCause());
        }
        catch (Throwable ex) {
            consumerOthers.accept(ex);
            throw new DataServiceException(message, ex);
        }
    }

    public static void doForDataService(IRunnable action, String message)
    {
        try {
            action.run();
        }
        catch (RepositoryException ex) {
            throw new DataServiceException(message, ex.getCause());
        }
        catch (Throwable ex) {
            throw new DataServiceException(message, ex);
        }
    }

    public static void doForDataService(IRunnable action, Consumer<Throwable> consumer, String message)
    {
        try {
            action.run();
        }
        catch (RepositoryException ex) {
            consumer.accept(ex);
            throw new DataServiceException(message, ex.getCause());
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throw new DataServiceException(message, ex);
        }
    }

    public static void doForDataService(IRunnable runnable, Consumer<Throwable> consumerRepository, Consumer<Throwable> consumerOthers, String message)
    {
        try {
            runnable.run();
        }
        catch (RepositoryException ex) {
            consumerRepository.accept(ex);
            throw new DataServiceException(message, ex.getCause());
        }
        catch (Throwable ex) {
            consumerOthers.accept(ex);
            throw new DataServiceException(message, ex);
        }
    }
}
