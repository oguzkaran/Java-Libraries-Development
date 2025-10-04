package com.karandev.util.net.ip.protocol.standard.text;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 * Abstract super class for clients of text-based standard IP protocols.
 * <p>This class provides a base for implementing clients that communicate over standard IP protocols using text-based communication.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public abstract class Client implements Closeable {
    /**
     * The underlying socket used for network communication.
     */
    protected Socket socket;

    /**
     * Closes the client socket and releases any associated resources.
     *
     * @throws IOException if an I/O error occurs when closing the socket.
     */
    @Override
    public void close() throws IOException
    {
        socket.close();
    }
}