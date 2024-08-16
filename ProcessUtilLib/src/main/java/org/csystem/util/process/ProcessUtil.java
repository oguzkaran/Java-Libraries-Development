/*----------------------------------------------------------------------
	FILE        : ProcessUtil.java
	AUTHOR      : JavaApp2-Jan-2024 Team
	LAST UPDATE : 3rd June 2024

	Utility class for process operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.process;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public final class ProcessUtil {
    private ProcessUtil()
    {
    }

    private static void startProcessAsyncWaitCallback(long timeout, TimeUnit timeUnit,
                                                      Process process, Consumer<Process> exitAction,
                                                      Consumer<Process> elapsedBeforeExitAction,
                                                      Consumer<InterruptedException> interruptAction,
                                                      Consumer<Throwable> exceptionAction)
    {
        try {
            if (process.waitFor(timeout, timeUnit))
                exitAction.accept(process);
            else
                elapsedBeforeExitAction.accept(process);
        }
        catch (InterruptedException ex)  {
            interruptAction.accept(ex);
        }
        catch (Throwable ex) {
            exceptionAction.accept(ex);
        }
    }

    /**
     * Starts a process with the specified timeout, allowing for customization and handling of various execution scenarios.
     *
     * <p>This method provides the ability to start a process with a set of commands and allows customization of the
     * process via the provided {@link ProcessBuilder} action. It also supports handling the process's exit status,
     * timeouts, interruptions, and exceptions.
     *
     * @apiNote
     * It is recommended to ensure that the {@link ProcessBuilder} action properly sets up the process environment
     * and handles any potential input/output redirections.
     *
     * @implNote
     * Internally, this method uses {@link ProcessBuilder#start()} to initiate the process and {@link Process#waitFor(long, TimeUnit)}
     * to handle the timeout mechanism.
     *
     * @param timeout The maximum time to wait for the process to exit before triggering the elapsed timeout action.
     * @param timeUnit The unit of the timeout parameter (e.g., {@link TimeUnit#SECONDS}).
     * @param builderAction A {@link Consumer} that customizes the {@link ProcessBuilder} before starting the process.
     * @param exitAction A {@link Consumer} that handles the process when it exits within the allowed timeout.
     * @param elapsedBeforeExitAction A {@link Consumer} that handles the scenario where the process does not exit within the specified timeout.
     * @param interruptAction A {@link Consumer} that handles the case where the thread is interrupted while waiting for the process to exit.
     * @param exceptionAction A {@link Consumer} that handles any {@link Throwable} that occurs during process execution.
     * @param commands A varargs array of {@link String} representing the program and its arguments to execute.
     *
     * @return void
     *
     * @throws NullPointerException if any of the consumer actions or commands are null.
     * @throws IllegalArgumentException if the commands array is empty.
     */

    public static void startProcess(long timeout, TimeUnit timeUnit,
                                    Consumer<ProcessBuilder> builderAction,
                                    Consumer<Process> exitAction,
                                    Consumer<Process> elapsedBeforeExitAction,
                                    Consumer<InterruptedException> interruptAction,
                                    Consumer<Throwable> exceptionAction,
                                    String...commands)
    {
        try {
            var processBuilder = new ProcessBuilder(commands);

            builderAction.accept(processBuilder);
            var process = processBuilder.start();

            if (process.waitFor(timeout, timeUnit))
                exitAction.accept(process);
            else
                elapsedBeforeExitAction.accept(process);
        }
        catch (InterruptedException ex) {
            interruptAction.accept(ex);
        }
        catch (Throwable ex) {
            exceptionAction.accept(ex);
        }
    }

    /**
     * Starts a process and waits for its completion. This method blocks until the process terminates.
     *
     * <p>The process is started using the specified commands, and it allows for customization via the
     * {@link ProcessBuilder} before the process begins. Once the process has started, this method waits
     * for it to complete and then invokes the provided action depending on whether the process finishes
     * successfully or an exception occurs during the execution.
     *
     * @param builderAction A {@link Consumer} that customizes the {@link ProcessBuilder} before starting the process.
     *                      This allows the caller to configure environment variables, working directory, etc.
     * @param exitAction A {@link Consumer} that handles the process after it exits. This action is invoked
     *                   when the process finishes successfully.
     * @param exceptionAction A {@link Consumer} that handles any {@link Throwable} that occurs during the process execution.
     *                        This action is invoked if an exception is thrown while starting the process or during its execution.
     * @param commands A varargs array of {@link String} representing the program and its arguments to execute.
     *                 The first element should be the program to execute, and the subsequent elements should be its arguments.
     *
     * @throws NullPointerException if any of the consumer actions (builderAction, exitAction, exceptionAction) or commands are null.
     * @throws IllegalArgumentException if the commands array is empty, meaning no program is specified to run.
     *
     * @implNote This method blocks the current thread until the process completes.
     * If asynchronous execution is required, consider using an async version of this method.
     *
     * @return void
     */

    public static void startProcess(Consumer<ProcessBuilder> builderAction,
                                    Consumer<Process> exitAction,
                                    Consumer<Throwable> exceptionAction,
                                    String...commands)
    {
        try {
            var processBuilder = new ProcessBuilder(commands);

            builderAction.accept(processBuilder);
            var process = processBuilder.start();

            process.waitFor();

            exitAction.accept(process);
        }
        catch (Throwable ex) {
            exceptionAction.accept(ex);
        }
    }

    /**
     * Starts a process with the specified timeout, allowing for customization and handling of the process completion.
     * This method blocks until the process terminates or the timeout is reached.
     *
     * @param timeout The maximum time to wait for the process to exit.
     * @param timeUnit The unit of the timeout parameter (e.g., {@link TimeUnit#SECONDS}).
     * @param builderAction A {@link Consumer} that customizes the {@link ProcessBuilder} before starting the process.
     * @param exitAction A {@link Consumer} that handles the process when it exits within the allowed timeout.
     * @param exceptionAction A {@link Consumer} that handles any {@link Throwable} that occurs during process execution.
     * @param commands A varargs array of {@link String} representing the program and its arguments to execute.
     *
     * @implNote This method blocks the current thread until the process completes.
     * If asynchronous execution is required, consider using an async version of this method.
     *
     * @return void
     *
     * @throws NullPointerException if any of the consumer actions or commands are null.
     * @throws IllegalArgumentException if the commands array is empty.
     */
    public static void startProcess(long timeout, TimeUnit timeUnit,
                                    Consumer<ProcessBuilder> builderAction,
                                    Consumer<Process> exitAction,
                                    Consumer<Throwable> exceptionAction,
                                    String...commands)
    {
        try {
            var processBuilder = new ProcessBuilder(commands);

            builderAction.accept(processBuilder);
            var process = processBuilder.start();

            process.waitFor(timeout, timeUnit);
            exitAction.accept(process);
        }
        catch (Throwable ex) {
            exceptionAction.accept(ex);
        }
    }

    /**
     * Starts a process asynchronously with the specified timeout, allowing for customization and handling of
     * various execution scenarios.
     *
     * <p>This method starts a process and returns immediately, while the process runs in a separate thread.
     * The provided actions are invoked based on the process's completion, timeout, or any exceptions that occur.
     *
     * @param timeout The maximum time to wait for the process to exit before triggering the elapsed timeout action.
     * @param timeUnit The unit of the timeout parameter (e.g., {@link TimeUnit#SECONDS}).
     * @param builderAction A {@link Consumer} that customizes the {@link ProcessBuilder} before starting the process.
     * @param exitAction A {@link Consumer} that handles the process when it exits within the allowed timeout.
     * @param elapsedBeforeExitAction A {@link Consumer} that handles the scenario where the process does not exit within the specified timeout.
     * @param interruptAction A {@link Consumer} that handles the case where the thread is interrupted while waiting for the process to exit.
     * @param exceptionAction A {@link Consumer} that handles any {@link Throwable} that occurs during process execution.
     * @param commands A varargs array of {@link String} representing the program and its arguments to execute.
     *
     * @return void
     *
     * @throws NullPointerException if any of the consumer actions or commands are null.
     * @throws IllegalArgumentException if the commands array is empty.
     */
    public static void startProcessAsync(long timeout, TimeUnit timeUnit,
                                         Consumer<ProcessBuilder> builderAction,
                                         Consumer<Process> exitAction,
                                         Consumer<Process> elapsedBeforeExitAction,
                                         Consumer<InterruptedException> interruptAction,
                                         Consumer<Throwable> exceptionAction,
                                         String...commands)
    {
        try {
            var processBuilder = new ProcessBuilder(commands);

            builderAction.accept(processBuilder);
            var process = processBuilder.start();

            new Thread(() -> startProcessAsyncWaitCallback(timeout, timeUnit, process, exitAction, elapsedBeforeExitAction, interruptAction, exceptionAction))
                    .start();
        }
        catch (IOException ex) {
            exceptionAction.accept(ex);
        }
    }

    //...
}