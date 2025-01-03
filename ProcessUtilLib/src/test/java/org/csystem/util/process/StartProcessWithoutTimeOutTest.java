package org.csystem.util.process;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StartProcessWithoutTimeOutTest {
    @Test
    void testStartProcessExitAction() {
        ProcessUtil.startProcess(
                processBuilder -> {},
                process -> assertEquals(0, process.exitValue()),
                ex -> fail(),
                "java", "-version"
        );
    }

    @Test
    void testStartProcessExceptionActionUnsupportedOperationException()
    {
        ProcessUtil.startProcess(
                processBuilder -> {
                    throw new UnsupportedOperationException("test");
                },
                process -> fail(),
                ex -> assertInstanceOf(UnsupportedOperationException.class, ex),
                "java", "-version"
        );
    }

    @Test
    void testProcessExceptionActionIOException()
    {
        ProcessUtil.startProcess(
                processBuilder -> {},
                process -> fail(),
                ex -> assertInstanceOf(IOException.class, ex),
                "invalidCommand"
        );
    }
}