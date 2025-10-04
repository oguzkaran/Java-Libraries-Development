package com.karandev.util.net;

import java.io.IOException;
import java.util.List;

/**
 * Interface for receiving data of a generic type from a network or communication channel.
 * <p>Defines a method for receiving data, to be implemented by protocol-specific receiver classes.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public interface IReceiver<T> {
    /**
     * Receives a list of data items.
     *
     * @return a list of received data items.
     * @throws IOException if an I/O error occurs during receiving.
     */
    List<T> receive() throws IOException;
}
