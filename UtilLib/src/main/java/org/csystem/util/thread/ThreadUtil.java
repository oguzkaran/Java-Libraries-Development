/**
 * A utility class that provides helper methods for thread-related operations,
 * such as joining, sleeping, synchronization, and semaphore control.
 * <p>
 * All methods handle checked exceptions internally where applicable, making
 * multithreaded code simpler and cleaner.
 * </p>
 *
 * <p><b>Note:</b> This class cannot be instantiated.</p>
 *
 * @author Oğuz Karan
 * @since 13.09.2021
 */
package org.csystem.util.thread;

import java.util.concurrent.Semaphore;

public final class ThreadUtil {
    private ThreadUtil()
    {
    }

    /**
     * Waits for the specified thread to die.
     *
     * @param thread the thread to wait for
     */
    public static void join(Thread thread)
    {
        try {
            thread.join();
        }
        catch (InterruptedException ignore) {

        }
    }

    /**
     * Waits at most the given time for the specified thread to die.
     *
     * @param thread       the thread to wait for
     * @param milliseconds the time to wait in milliseconds
     */
    public static void join(Thread thread, long milliseconds)
    {
        try {
            thread.join(milliseconds);
        }
        catch (InterruptedException ignore) {

        }
    }

    /**
     * Causes the currently executing thread to sleep for the specified time.
     *
     * @param milliseconds the length of time to sleep in milliseconds
     */
    public static void sleep(long milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException ignore) {

        }
    }

    /**
     * Wakes up a single thread that is waiting on the object's monitor.
     *
     * @param object the object to notify
     */
    public static void notify(Object object)
    {
        object.notify();
    }

    /**
     * Causes the current thread to wait until it is notified.
     *
     * @param object the object whose monitor is being waited on
     */
    public static void wait(Object object)
    {
        try {
            object.wait();
        }
        catch (InterruptedException ignore) {

        }
    }

    /**
     * Acquires a permit from the given semaphore, blocking until one is available.
     *
     * @param semaphore the semaphore to acquire from
     */
    public static void acquire(Semaphore semaphore)
    {
        try {
            semaphore.acquire();
        }
        catch (InterruptedException ignore) {

        }
    }

    /**
     * Releases a permit, returning it to the given semaphore.
     *
     * @param semaphore the semaphore to release to
     */
    public static void release(Semaphore semaphore)
    {
        semaphore.release();
    }

    /**
     * Acquires the given number of permits from the specified semaphore,
     * blocking until they become available.
     *
     * @param semaphore the semaphore to acquire from
     * @param permits   the number of permits to acquire
     */
    public static void acquire(Semaphore semaphore, int permits)
    {
        try {
            semaphore.acquire(permits);
        }
        catch (InterruptedException ignore) {

        }
    }

    /**
     * Releases the specified number of permits to the given semaphore.
     *
     * @param semaphore the semaphore to release to
     * @param permits   the number of permits to release
     */
    public static void release(Semaphore semaphore, int permits)
    {
        semaphore.release(permits);
    }

    /**
     * Executes a synchronized block on the given object using the provided callback.
     * Any exception thrown inside the callback will be wrapped in a {@link RuntimeException}.
     *
     * @param actionCallback the callback to run within the synchronized block
     * @param object         the object to synchronize on
     * @throws RuntimeException if the callback throws an exception
     */
    public static void synchronize(IActionCallback actionCallback, Object object)
    {
        synchronized (object) {
            try {
                actionCallback.run();
            }
            catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }
}
