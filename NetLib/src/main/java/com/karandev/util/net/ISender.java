package com.karandev.util.net;

import java.io.IOException;

/**
 * Interface for sending data of a generic type over a network or communication channel.
 * <p>Defines a method for sending data, to be implemented by protocol-specific sender classes.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public interface ISender<T> {
    /**
     * Sends the specified data.
     *
     * @param t the data to send.
     * @throws IOException if an I/O error occurs during sending.
     */
    void send(T t) throws IOException;
}
