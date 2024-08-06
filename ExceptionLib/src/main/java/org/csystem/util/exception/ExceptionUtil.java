/*----------------------------------------------------------------------
FILE        : ExceptionUtil.java
AUTHOR      : Oğuz Karan
LAST UPDATE : 19.09.2021

ExceptionUtil class for exception managing

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.exception;

import java.io.Closeable;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;
import java.util.function.Function;

// TODO : write javadoc comment for ExceptionUtil
public final class ExceptionUtil {

    /**
     * Throws specified type of RuntimeException with the provided message and the original exception as the cause.
     *
     * @param <T> the type of the exception that extends {@link RuntimeException}
     * @param msg the message to include in the RuntimeException
     * @param cls the class of the RuntimeException to be thrown
     * @param ex  the cause of the exception
     * @throws UnsupportedOperationException if the specified RuntimeException class does not have a constructor that accepts a String and a Throwable
     */
    private static <T extends RuntimeException> void throwException(String msg, Class<T> cls, Throwable ex) {
        try {
            throw cls.getConstructor(String.class, Throwable.class).newInstance(msg, ex);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                 InstantiationException e) {
            throw new UnsupportedOperationException("Fault for exception class");
        }
    }

    private ExceptionUtil() {
    }


    /**
     * Executes the given action and handles any thrown exceptions by wrapping and rethrowing them as specified exceptions.
     * If an exception is thrown, a new RuntimeException is thrown with the provided message and the original exception as the cause.
     *
     * @param actionCallback the action to be executed
     * @param msg            the detail message for the exception if one is thrown
     * @param cls            the class of the exception to be thrown if an exception occurs during the action execution
     * @throws T if an exception occurs during the action execution, and it's rethrown as the specified type
     */

    public static <T extends RuntimeException> void doWorkForRunnable(IAction actionCallback, String msg, Class<T> cls) {
        try {
            actionCallback.run();
        } catch (Throwable ex) {
            throwException(msg, cls, ex);
        }
    }


    /**
     * Executes the given action and handles any thrown exceptions by wrapping and rethrowing them as specified exceptions.
     * If an exception is thrown, it is passed to the provided consumer and then a new RuntimeException is thrown with the
     * provided message and the original exception as the cause.
     *
     * @param <T> the type of the exception that extends {@link RuntimeException}
     * @param actionCallback the action to be executed
     * @param consumer a {@link Consumer} to handle the thrown exception before it's rethrown
     * @param msg the detail message for the exception if one is thrown
     * @param cls the class of the exception to be thrown if an exception occurs during the action execution
     * @throws T if an exception occurs during the action execution, and it's rethrown as the specified type
     */
    public static <T extends RuntimeException> void doWorkForRunnable(IAction actionCallback, Consumer<Throwable> consumer, String msg, Class<T> cls) {
        try {
            actionCallback.run();
        } catch (Throwable ex) {
            consumer.accept(ex);
            throwException(msg, cls, ex);
        }
    }

    // TODO: Add Javadocs for below methods

    public static <T extends RuntimeException, R> R doWorkFor(ISupplier<R> supplier, String msg, Class<T> cls) {
        R result = null;

        try {
            result = supplier.get();
        } catch (Throwable ex) {
            throwException(msg, cls, ex);
        }

        return result;
    }


    public static <T extends RuntimeException, R> R doWorkFor(ISupplier<R> supplier, Consumer<Throwable> consumer, String msg, Class<T> cls) {
        R result = null;

        try {
            result = supplier.get();
        } catch (Throwable ex) {
            consumer.accept(ex);
            throwException(msg, cls, ex);
        }

        return result;
    }


    public static <R> R doWorkForRuntimeException(ISupplier<R> supplier, String msg) {
        try {
            return supplier.get();
        } catch (Throwable ex) {
            throw new RuntimeException(msg, ex);
        }
    }


    public static void doWorkForRuntimeException(IAction actionCallback, String msg) {
        try {
            actionCallback.run();
        } catch (Throwable ex) {
            throw new RuntimeException(msg, ex);
        }
    }


    public static <R> R subscribe(ISupplier<R> supplier, Function<Throwable, R> function) {
        try {
            return supplier.get();
        } catch (Throwable ex) {
            return function.apply(ex);
        }
    }


    public static void subscribeRunnable(IAction actionCallback, Consumer<Throwable> consumer) {
        try {
            actionCallback.run();
        } catch (Throwable ex) {
            consumer.accept(ex);
        }
    }


    public static <R> R subscribe(ISupplier<R> supplier, Function<Throwable, R> function, Runnable runnableCompleted) {
        try {
            return supplier.get();
        } catch (Throwable ex) {
            return function.apply(ex);
        } finally {
            runnableCompleted.run();
        }
    }


    public static void subscribeRunnable(IAction actionCallback, Consumer<Throwable> consumer, Runnable runnableCompleted) {
        try {
            actionCallback.run();
        } catch (Throwable ex) {
            consumer.accept(ex);
        } finally {
            runnableCompleted.run();
        }
    }


    public static <R> R subscribe(ISupplier<R> supplier, Closeable closeable, Function<Throwable, R> function, Runnable runnableCompleted) {
        try (closeable) {
            return supplier.get();
        } catch (Throwable ex) {
            return function.apply(ex);
        } finally {
            runnableCompleted.run();
        }
    }


    public static void subscribeRunnable(IAction actionCallback, Closeable closeable, Consumer<Throwable> consumer, Runnable runnableCompleted) {
        try (closeable) {
            actionCallback.run();
        } catch (Throwable ex) {
            consumer.accept(ex);
        } finally {
            runnableCompleted.run();
        }
    }


    public static <R> R subscribe(ISupplier<R> supplier, Closeable closeable, Function<Throwable, R> function) {
        try (closeable) {
            return supplier.get();
        } catch (Throwable ex) {
            return function.apply(ex);
        }
    }


    public static void subscribeRunnable(IAction actionCallback, Closeable closeable, Consumer<Throwable> consumer) {
        try (closeable) {
            actionCallback.run();
        } catch (Throwable ex) {
            consumer.accept(ex);
        }
    }
}
