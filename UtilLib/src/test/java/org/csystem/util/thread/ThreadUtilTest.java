package org.csystem.util.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadUtilTest {

    @Test
    public void testJoin()
    {
        AtomicBoolean completed = new AtomicBoolean(false);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
                completed.set(true);
            } catch (InterruptedException ignore) {
            }
        });

        thread.start();
        ThreadUtil.join(thread);

        assertTrue(completed.get(), "Thread should complete after join");
    }

    @Test
    public void testJoinWithTimeout()
    {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(500); // Longer than the timeout
            } catch (InterruptedException ignore) {
            }
        });

        thread.start();
        long start = System.currentTimeMillis();
        ThreadUtil.join(thread, 100); // Join for 100 ms
        long duration = System.currentTimeMillis() - start;

        assertTrue(duration >= 100, "Join should wait approximately 100 ms");
        assertTrue(thread.isAlive(), "Thread should still be alive after timeout");
    }

    @Test
    public void testSleep()
    {
        long start = System.currentTimeMillis();
        ThreadUtil.sleep(200);
        long duration = System.currentTimeMillis() - start;

        assertTrue(duration >= 200, "Sleep should last at least 200 ms");
    }

    @Test
    public void testNotifyAndWait()
    {
        Object lock = new Object();
        AtomicBoolean notified = new AtomicBoolean(false);

        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                try {
                    ThreadUtil.wait(lock);
                    notified.set(true);
                } catch (Exception ignore) {
                }
            }
        });

        waiter.start();

        ThreadUtil.sleep(100); // Give waiter time to start waiting

        synchronized (lock) {
            ThreadUtil.notify(lock);
        }

        ThreadUtil.join(waiter);
        assertTrue(notified.get(), "Thread should be notified");
    }

    @Test
    public void testSemaphoreAcquireRelease()
    {
        Semaphore semaphore = new Semaphore(0);

        Thread releaser = new Thread(() -> {
            ThreadUtil.sleep(100);
            ThreadUtil.release(semaphore);
        });

        releaser.start();
        long start = System.currentTimeMillis();
        ThreadUtil.acquire(semaphore);
        long duration = System.currentTimeMillis() - start;

        assertTrue(duration >= 100, "Acquire should block until release");
    }

    @Test
    public void testSemaphoreAcquireReleaseMultiplePermits()
    {
        Semaphore semaphore = new Semaphore(0);

        ThreadUtil.release(semaphore, 2);
        ThreadUtil.acquire(semaphore);
        ThreadUtil.acquire(semaphore);

        assertEquals(0, semaphore.availablePermits(), "All permits should be acquired");
    }

    @Test
    public void testSynchronize()
    {
        Object lock = new Object();
        AtomicBoolean executed = new AtomicBoolean(false);

        ThreadUtil.synchronize(() -> executed.set(true), lock);

        assertTrue(executed.get(), "Action should be executed inside synchronized block");
    }

    @Test
    public void testSynchronizeThrowsWrappedRuntimeException()
    {
        Object lock = new Object();

        Exception exception = assertThrows(RuntimeException.class, () ->
                ThreadUtil.synchronize(() -> {
                    throw new Exception("fail");
                }, lock));

        assertEquals("fail", exception.getMessage());
    }
}