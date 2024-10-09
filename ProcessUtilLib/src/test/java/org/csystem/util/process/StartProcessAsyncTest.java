package org.csystem.util.process;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

class StartProcessAsyncTest {
    @Test
    void testStartProcessAsyncExitActionWithLatch() throws InterruptedException
    {
         CountDownLatch latch = new CountDownLatch(1);

        ProcessUtil.startProcessAsync(1000, MILLISECONDS, processBuilder -> {}, process -> {
            assertEquals(0, process.exitValue());
            latch.countDown();
        }, process -> fail(), ex -> fail(), ex -> fail(), "java", "-version");

        assertTrue(latch.await(100, MILLISECONDS));
    }
}