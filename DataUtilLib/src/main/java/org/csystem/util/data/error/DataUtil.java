package org.csystem.util.data.error;

import org.csystem.util.data.repository.exception.RepositoryException;
import org.csystem.util.data.service.exception.DataServiceException;

import java.util.function.Consumer;

/**
 * Utility class for executing operations related to repository and data services,
 * providing consistent exception handling and wrapping exceptions in custom types.
 * This class cannot be instantiated or extended.
 * @author Oğuz Karan
 * @version 1.0.0
 */

public final class DataUtil {
    private DataUtil()
    {}

    /**
     * Executes the given supplier operation and handles any exceptions that occur,
     * wrapping them in a {@link RepositoryException}.
     *
     * @param <R> The type of the result returned by the supplier.
     * @param supplier A supplier functional interface that returns a result of type {@code R}.
     * @param message A message to include in the {@link RepositoryException} if an error occurs.
     * @return The result of type {@code R} produced by the supplier.
     * @throws RepositoryException If an exception occurs while executing the supplier.
     */
    public static <R> R doForRepository(ISupplier<R> supplier, String message)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            throw new RepositoryException(message, ex);
        }
    }

    /**
     * Executes the given supplier operation and handles any exceptions that occur,
     * passing the exception to the provided consumer and then wrapping it in a {@link RepositoryException}.
     *
     * @param <R> The type of the result returned by the supplier.
     * @param supplier A supplier functional interface that returns a result of type {@code R}.
     * @param consumer A consumer that accepts any {@link Throwable} caught during the execution of the supplier.
     * @param message A message to include in the {@link RepositoryException} if an error occurs.
     * @return The result of type {@code R} produced by the supplier.
     * @throws RepositoryException If an exception occurs while executing the supplier.
     */
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

    /**
     * Executes the given action and handles any exceptions that occur,
     * wrapping them in a {@link RepositoryException}.
     *
     * @param action A runnable action that will be executed.
     * @param message A message to include in the {@link RepositoryException} if an error occurs.
     * @throws RepositoryException If an exception occurs while executing the action.
     */
    public static void doForRepository(IRunnable action, String message)
    {
        try {
            action.run();
        }
        catch (Throwable ex) {
            throw new RepositoryException(message, ex);
        }
    }

    /**
     * Executes the given action and handles any exceptions that occur,
     * passing the exception to the provided consumer and then wrapping it in a {@link RepositoryException}.
     *
     * @param action A runnable action that will be executed.
     * @param consumer A consumer that accepts any {@link Throwable} caught during the execution of the action.
     * @param message A message to include in the {@link RepositoryException} if an error occurs.
     * @throws RepositoryException If an exception occurs while executing the action.
     */
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

    /**
     * Executes the given supplier operation and handles any exceptions that occur,
     * rethrowing them as a {@link DataServiceException}. If a {@link RepositoryException} is caught,
     * its underlying cause is wrapped in the {@link DataServiceException}.
     *
     * @param <R> The type of the result returned by the supplier.
     * @param supplier A supplier functional interface that returns a result of type {@code R}.
     * @param message A message to include in the {@link DataServiceException} if an error occurs.
     * @return The result of type {@code R} produced by the supplier.
     * @throws DataServiceException If a {@link RepositoryException} or any other exception occurs during execution.
     */
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

    /**
     * Executes the given supplier operation and handles any exceptions that occur,
     * passing the exception to the provided consumer and rethrowing it as a {@link DataServiceException}.
     * If a {@link RepositoryException} is caught, its underlying cause is wrapped in the {@link DataServiceException}.
     *
     * @param <R> The type of the result returned by the supplier.
     * @param supplier A supplier functional interface that returns a result of type {@code R}.
     * @param consumer A consumer that accepts any {@link Throwable} caught during the execution of the supplier.
     * @param message A message to include in the {@link DataServiceException} if an error occurs.
     * @return The result of type {@code R} produced by the supplier.
     * @throws DataServiceException If a {@link RepositoryException} or any other exception occurs during execution.
     */
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

    /**
     * Executes the given supplier operation and handles any exceptions that occur,
     * passing them to the provided consumers based on the type of exception,
     * and then rethrowing them as a {@link DataServiceException}.
     * If a {@link RepositoryException} is caught, its underlying cause is wrapped in the {@link DataServiceException}.
     *
     * @param <R> The type of the result returned by the supplier.
     * @param supplier A supplier functional interface that returns a result of type {@code R}.
     * @param consumerRepository A consumer that accepts any {@link RepositoryException} caught during the execution of the supplier.
     * @param consumerOthers A consumer that accepts any other {@link Throwable} caught during the execution of the supplier.
     * @param message A message to include in the {@link DataServiceException} if an error occurs.
     * @return The result of type {@code R} produced by the supplier.
     * @throws DataServiceException If a {@link RepositoryException} or any other exception occurs during execution.
     */
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

    /**
     * Executes the given action and handles any exceptions that occur,
     * rethrowing them as a {@link DataServiceException}.
     * If a {@link RepositoryException} is caught, its underlying cause is wrapped in the {@link DataServiceException}.
     *
     * @param action A runnable action that will be executed.
     * @param message A message to include in the {@link DataServiceException} if an error occurs.
     * @throws DataServiceException If a {@link RepositoryException} or any other exception occurs during execution.
     */
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

    /**
     * Executes the given action and handles any exceptions that occur,
     * passing the exception to the provided consumer and rethrowing it as a {@link DataServiceException}.
     * If a {@link RepositoryException} is caught, its underlying cause is wrapped in the {@link DataServiceException}.
     *
     * @param action A runnable action that will be executed.
     * @param consumer A consumer that accepts any {@link Throwable} caught during the execution of the action.
     * @param message A message to include in the {@link DataServiceException} if an error occurs.
     * @throws DataServiceException If a {@link RepositoryException} or any other exception occurs during execution.
     */
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

    /**
     * Executes a task and handles exceptions specific to data services.
     *
     * @param runnable           the task to execute
     * @param consumerRepository consumer to handle RepositoryException
     * @param consumerOthers     consumer to handle other exceptions
     * @param message            the message for the DataServiceException
     * @throws DataServiceException if an exception occurs during task execution
     */
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
