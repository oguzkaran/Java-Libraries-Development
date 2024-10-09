package org.csystem.spring.net.constant;

/**
 * A utility class that holds constant values those are used in the application.
 *
 * <p>This class cannot be instantiated and is used to store static final constants.</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */

public final class Constant {
    private Constant()
    {
    }

    /**
     * The name of the Spring bean for the concurrent server component.
     *
     * <p>This constant is used to reference the {@code ConcurrentServer} bean
     * within the Spring application context.</p>
     */
    public static final String CONCURRENT_SERVER_BEAN_NAME = "org.csystem.spring.net.concurrentServer";
}
