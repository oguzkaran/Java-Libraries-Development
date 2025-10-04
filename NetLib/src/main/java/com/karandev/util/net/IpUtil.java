package com.karandev.util.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.OptionalInt;

/**
 * Utility class for IP-related operations, such as checking port availability and finding available ports.
 * <p>Provides static methods to assist with common IP networking tasks.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public final class IpUtil {
    /**
     * Private constructor to prevent instantiation.
     */
    private IpUtil() {}

    /**
     * Checks if a specific port is available for use on the local machine.
     *
     * @param port the port number to check.
     * @return true if the port is available, false otherwise.
     */
    public static boolean isPortAvailable(int port)
    {
        var result = false;

        try (var socket = new ServerSocket(port)) {
            result = true;
        }
        catch (IOException ignore) {

        }

        return result;
    }

    /**
     * Finds the first available port within a specified range.
     *
     * @param minPort the minimum port number (inclusive).
     * @param maxPort the maximum port number (inclusive).
     * @return an {@link OptionalInt} containing the first available port, or empty if none are available.
     */
    public static OptionalInt getFirstAvailablePort(int minPort, int maxPort)
    {
        for (var port = minPort; port <= maxPort; ++port)
            if (isPortAvailable(port))
                return OptionalInt.of(port);

        return OptionalInt.empty();
    }

    /**
     * Finds the first available port from a list of specified ports.
     *
     * @param ports the ports to check.
     * @return an {@link OptionalInt} containing the first available port, or empty if none are available.
     */
    public static OptionalInt getFirstAvailablePort(int...ports)
    {
        for (var port : ports)
            if (isPortAvailable(port))
                return OptionalInt.of(port);

        return OptionalInt.empty();
    }
}
