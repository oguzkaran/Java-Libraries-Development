package org.csystem.util.process;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static java.util.concurrent.TimeUnit.*;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Written by Buğrahan KISA, Mehmet Doğan")
class StartProcessTimeoutTest {
    @Test
    void testStartProcessExitWithinTimeout()
    {
        ProcessUtil.startProcess(
                1000, MILLISECONDS,
                processBuilder -> {},
                process -> assertEquals(0, process.exitValue()),
                ex -> fail(),
                "java", "-version"
        );
    }

    @Test
    void testStartProcessExitWithNonZeroCode() {
        ProcessUtil.startProcess(
                1, NANOSECONDS,
                processBuilder -> {},
                process -> {
                    try {
                        assertFalse(process.waitFor(1, NANOSECONDS));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                },
                ex -> fail(),
                "java", "-version"
        );
    }

    @Test
    void testStartProcessTimeoutInterrupt()
    {
        var thread = Thread.currentThread();

        new Thread(
                () -> {
                    try {
                        Thread.sleep(500);
                        thread.interrupt();
                    }
                    catch (InterruptedException ignore) {

                    }
                }).start();

        ProcessUtil.startProcess(
                2000, MILLISECONDS,
                processBuilder -> {},
                process -> {},
                ex -> assertInstanceOf(InterruptedException.class, ex),
                "ping", "127.0.0.1", "-n", "50"
        );
    }
}