package com.karandev.util.net.ip.protocol.standard.text;

import java.io.IOException;

/**
 * Interface for login and logout operations in network clients.
 * <p>Defines methods for logging in and out, typically used by protocol clients that require authentication.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public interface ILogin {
    /**
     * Logs in to the remote server or service.
     *
     * @return true if login is successful, false otherwise.
     * @throws IOException if an I/O error occurs during login.
     */
    boolean login() throws IOException;

    /**
     * Logs out from the remote server or service.
     *
     * @return true if logout is successful, false otherwise.
     * @throws IOException if an I/O error occurs during logout.
     */
    boolean logout() throws IOException;
}
