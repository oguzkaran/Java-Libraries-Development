package org.csystem.util.exception;

import java.io.Closeable;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Utility class for exception handling operations, including throwing specified types of runtime exceptions
 * and executing actions or suppliers with exception handling and resource management.
 * <p>1993 by C and System Programmers Association (CSD) All Rights Free</p>
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public final class ExceptionUtil {

    /**
     * Throws specified type of RuntimeException with the provided message and the original exception as the cause.
     * @param <T> the type of the exception that extends {@link RuntimeException}
     * @param msg the message to include in the RuntimeException
     * @param cls the class of the RuntimeException to be thrown
     * @param ex  the cause of the exception
     * @throws UnsupportedOperationException if the specified RuntimeException class doesn't have a constructor that accepts a String and a Throwable
     */
    private static <T extends RuntimeException> void throwException(String msg, Class<T> cls, Throwable ex)
    {
        try {
            throw cls.getConstructor(String.class, Throwable.class).newInstance(msg, ex);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
               InstantiationException e) {
            throw new UnsupportedOperationException("Fault for exception class");
        }
    }

    private ExceptionUtil()
    {
    }


    /**
     * Executes the given action and handles any thrown exceptions by wrapping and rethrowing them as specified exceptions.
     * If an exception is thrown, a new RuntimeException is thrown with the provided message and the original exception as the cause.
     * @param actionCallback the {@link IAction} action to be executed
     * @param msg the detail message for the exception if one is thrown
     * @param cls the class of the exception to be thrown if an exception occurs during the action execution
     * @throws T if an exception occurs during the action execution, and it's rethrown as the specified type
     */
    public static <T extends RuntimeException> void doWorkForRunnable(IAction actionCallback, String msg, Class<T> cls)
    {
        try {
            actionCallback.run();
        }
        catch (Throwable ex) {
            throwException(msg, cls, ex);
        }
    }


    /**
     * Executes the given action and handles any thrown exceptions by wrapping and rethrowing them as specified exceptions.
     * If an exception is thrown, it is passed to the provided consumer and then a new RuntimeException is thrown with the
     * provided message and the original exception as the cause.
     * @param <T> the type of the exception that extends {@link RuntimeException}
     * @param actionCallback the action to be executed
     * @param consumer the {@link Consumer} that processes the exception before it is wrapped and rethrown.
     * @param msg the detail message for the exception if one is thrown
     * @param cls the class of the exception to be thrown if an exception occurs during the action execution
     * @throws T if an exception occurs during the action execution, and it's rethrown as the specified type
     */
    public static <T extends RuntimeException> void doWorkForRunnable(IAction actionCallback, Consumer<Throwable> consumer, String msg, Class<T> cls)
    {
        try {
            actionCallback.run();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throwException(msg, cls, ex);
        }
    }


    /**
     * Executes a given supplier function and handles any exceptions thrown during execution.
     * @param <T>      the type of the runtime exception to be thrown in case of failure. It must extend {@link RuntimeException}.
     * @param <R>      the type of result returned by the {@code supplier}.
     * @param supplier the {@link ISupplier} function to be executed. It must have a method {@code get()} that returns a result of type {@code R}.
     * @param msg      the message to be included in the exception thrown if an error occurs.
     * @param cls      the class of the RuntimeException to be thrown
     * @return R the result produced by the {@code supplier}.
     * @throws T if an exception occurs during the action execution, and it's rethrown as the specified type
     */
    public static <T extends RuntimeException, R> R doWorkFor(ISupplier<R> supplier, String msg, Class<T> cls)
    {
        R result = null;

        try {
            result = supplier.get();
        }
        catch (Throwable ex) {
            throwException(msg, cls, ex);
        }

        return result;
    }

    /**
     * Executes a given supplier function and handles any exceptions thrown during execution.
     * @param <T>      the type of the runtime exception to be thrown in case of failure. It must extend {@link RuntimeException}.
     * @param <R>      the type of result returned by the {@code supplier}.
     * @param supplier the {@link ISupplier} function to be executed. It must have a method {@code get()} that returns a result of type {@code R}.
     * @param msg      the message to be included in the exception thrown if an error occurs.
     * @param cls      the class of the RuntimeException to be thrown
     * @param consumer the {@link Consumer} that processes the exception before it is wrapped and rethrown.
     * @return R the result produced by the {@code supplier}.
     * @throws T if an exception occurs during the action execution, and it's rethrown as the specified type
     */
    public static <T extends RuntimeException, R> R doWorkFor(ISupplier<R> supplier, Consumer<Throwable> consumer, String msg, Class<T> cls)
    {
        R result = null;

        try {
            result = supplier.get();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throwException(msg, cls, ex);
        }

        return result;
    }


    /**
     * Executes a given supplier function and wraps any exceptions thrown during execution in a {@link RuntimeException}.
     * @param <R>      the type of result returned by the {@code supplier}.
     * @param supplier the {@link ISupplier} function to be executed. It must have a method {@code get()} that returns a result of type {@code R}.
     * @param msg      the message to be included in the {@link RuntimeException} if an error occurs.
     * @return R the result produced by the {@code supplier}.
     * @throws RuntimeException if an exception occurs during the action execution, it'll be caught and wrapped in a {@link RuntimeException} with the provided message and the original exception as the cause.
     */
    public static <R> R doWorkForRuntimeException(ISupplier<R> supplier, String msg)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            throw new RuntimeException(msg, ex);
        }
    }

    /**
     * Executes a given action and wraps any exceptions thrown during execution in a {@link RuntimeException}.
     * @param actionCallback the {@link IAction} action to be executed
     * @param msg the message to be included in the {@link RuntimeException} if an error occurs.
     * @throws RuntimeException if an exception occurs during the action execution, it'll be caught and wrapped in a {@link RuntimeException} with the provided message and the original exception as the cause.
     */
    public static void doWorkForRuntimeException(IAction actionCallback, String msg)
    {
        try {
            actionCallback.run();
        }
        catch (Throwable ex) {
            throw new RuntimeException(msg, ex);
        }
    }

    /**
     * Executes a given supplier function and applies a provided function to handle any exceptions thrown during execution.
     * @param <R>      the type of result returned by both the {@code supplier} and the {@code function}.
     * @param supplier the {@link ISupplier} function to be executed. It must have a method {@code get()} that returns a result of type {@code R}.
     * @param function the {@link Function} that processes the exception if one is thrown. It takes a {@link Throwable} as input and returns a result of type {@code R}.
     * @return the result produced by the {@code supplier} if no exception occurs, or the result produced by the {@code function} if an exception is thrown.
     */
    public static <R> R subscribe(ISupplier<R> supplier, Function<Throwable, R> function)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            return function.apply(ex);
        }
    }

    /**
     * Executes a given action and processes any exceptions thrown during execution with a provided consumer.
     * @param actionCallback the {@link IAction} callback to be executed. Provide the {@code run()} method to execute the action.
     * @param consumer       the {@link Consumer} that processes the exception before it is wrapped and rethrown.
     */
    public static void subscribeRunnable(IAction actionCallback, Consumer<Throwable> consumer)
    {
        try {
            actionCallback.run();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
        }
    }


    /**
     * Executes a given supplier function, handles any exceptions thrown during execution with a provided function,
     * and always runs a specified runnable upon completion.
     * @param <R> the type of result returned by both the {@code supplier} and the {@code function}.
     * @param supplier the {@link ISupplier} function to be executed. It must have a method {@code get()} that returns a result of type {@code R}.
     * @param function the {@link Function} that processes the exception if one is thrown. It takes a {@link Throwable}
     * as input and returns a result of type {@code R}.
     * @param runnableCompleted the {@link Runnable} that is always executed after the {@code supplier} function
     * completes, regardless of whether an exception occurred or not.
     * @return the result produced by the {@code supplier} if no exception occurs, or the result produced by the {@code function} if an exception is thrown.
     */
    public static <R> R subscribe(ISupplier<R> supplier, Function<Throwable, R> function, Runnable runnableCompleted)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            return function.apply(ex);
        }
        finally {
            runnableCompleted.run();
        }
    }

    /**
     * Executes a given action and processes any exceptions thrown during execution with a provided consumer,
     * while always executing a specified runnable upon completion.
     * @param actionCallback the {@link IAction} callback to be executed. Provide the {@code run()} method to execute the action.
     * @param consumer the {@link Consumer} that processes any {@link Throwable} that occurs during execution. It accepts the exception and allows for custom handling.
     * @param runnableCompleted the {@link Runnable} that is always executed after the {@code actionCallback} completes, regardless of whether an exception occurred or not.
     */
    public static void subscribeRunnable(IAction actionCallback, Consumer<Throwable> consumer, Runnable runnableCompleted)
    {
        try {
            actionCallback.run();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
        }
        finally {
            runnableCompleted.run();
        }
    }

    /**
     * Executes a supplier function, handles any exceptions thrown during execution with a provided function,
     * and ensures a closeable resource is managed properly, while always running a specified runnable upon completion.
     * @param <R> the type of result returned by both the {@code supplier} and the {@code function}.
     * @param supplier the {@link ISupplier} function to be executed. It must have a method {@code get()} that returns a result of type {@code R}.
     * @param closeable the {@link Closeable} resource that will be automatically closed after the {@code supplier} function completes, whether an exception occurs or not.
     * @param function the {@link Function} that processes the exception if one is thrown. It takes a {@link Throwable} as input and returns a result of type {@code R}.
     * @param runnableCompleted the {@link Runnable} that is always executed after the {@code supplier} function completes, regardless of whether an exception occurred or not.
     * @return the result produced by the {@code supplier} if no exception occurs, or the result produced by the {@code function} if an exception is thrown.
     */
    public static <R> R subscribe(ISupplier<R> supplier, Closeable closeable, Function<Throwable, R> function, Runnable runnableCompleted)
    {
        try (closeable) {
            return supplier.get();
        }
        catch (Throwable ex) {
            return function.apply(ex);
        }
        finally {
            runnableCompleted.run();
        }
    }

    /**
     * Executes a given action, handles any exceptions thrown during execution with a provided consumer,
     * ensures a closeable resource is managed properly, and always runs a specified runnable upon completion.
     * @param actionCallback the {@link IAction} callback to be executed. Provide the {@code run()} method to execute the action.
     * @param closeable the {@link Closeable} resource that will be automatically closed after the {@code actionCallback} completes, whether an exception occurs or not.
     * @param consumer the {@link Consumer} that processes any {@link Throwable} that occurs during execution. It accepts the exception and allows for custom handling.
     * @param runnableCompleted the {@link Runnable} that is always executed after the {@code actionCallback} completes, regardless of whether an exception occurred or not.
     */
    public static void subscribeRunnable(IAction actionCallback, Closeable closeable, Consumer<Throwable> consumer, Runnable runnableCompleted)
    {
        try (closeable) {
            actionCallback.run();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
        }
        finally {
            runnableCompleted.run();
        }
    }

    /**
     * Executes a supplier function, handles any exceptions thrown during execution with a provided function,
     * and ensures a closeable resource is managed properly.
     * @param <R> the type of result returned by both the {@code supplier} and the {@code function}.
     * @param supplier the {@link ISupplier} function to be executed. It must have a method {@code get()} that returns a result of type {@code R}.
     * @param closeable the {@link Closeable} resource that'll be automatically closed after the {@code supplier} function completes, whether an exception occurs or not.
     * @param function  the {@link Function} that processes the exception if one is thrown. It takes a {@link Throwable} as input and returns a result of type {@code R}.
     * @return the result produced by the {@code supplier} if no exception occurs, or the result produced by the {@code function} if an exception is thrown.
     */
    public static <R> R subscribe(ISupplier<R> supplier, Closeable closeable, Function<Throwable, R> function)
    {
        try (closeable) {
            return supplier.get();
        }
        catch (Throwable ex) {
            return function.apply(ex);
        }
    }

    /**
     * Executes a given action, handles any exceptions thrown during execution with a provided consumer,
     * and ensures a closeable resource is managed properly.
     * @param actionCallback the {@link IAction} callback to be executed. Provide the {@code run()} method to execute the action.
     * @param closeable the {@link Closeable} resource that'll be automatically closed after the {@code actionCallback} completes, whether an exception occurs or not.
     * @param consumer       the {@link Consumer} that processes any {@link Throwable} that occurs during execution. It accepts the exception and allows for custom handling.
     */
    public static void subscribeRunnable(IAction actionCallback, Closeable closeable, Consumer<Throwable> consumer)
    {
        try (closeable) {
            actionCallback.run();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
        }
    }
}
