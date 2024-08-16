package com.karandev.util.net;

import com.karandev.util.net.exception.NetworkException;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Immutable TCP class for TCP socket operations.
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @see TcpUtil
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public class TCPClient implements Closeable {
    private final Socket m_socket;

    /**
     * Resolves the specified {@code host} to an {@link InetAddress}.
     *
     * @param host the hostname to resolve
     * @return the resolved {@code InetAddress}
     * @throws NetworkException if the hostname cannot be resolved
     */
    private static InetAddress getInetAddress(String host)
    {
        try {
            return InetAddress.getByName(host);
        }
        catch (Throwable ex) {
            throw new NetworkException("InetAddress:", ex);
        }
    }

    /**
     * Creates a new TCP client and connects to the specified {@code host} on {@code port}.
     *
     * @param host the hostname of the server
     * @param port the port number of the server
     * @throws NetworkException if a connection cannot be established
     */
    public TCPClient(String host, int port)
    {
        this(getInetAddress(host), port);
    }

    /**
     * Creates a new TCP client and connects to the specified {@code inetAddress} on {@code port}.
     *
     * @param inetAddress the {@code InetAddress} of the server
     * @param port        the port number of the server
     * @throws NetworkException if a connection cannot be established
     */
    public TCPClient(InetAddress inetAddress, int port)
    {
        try {
            m_socket = new Socket(inetAddress, port);
        }
        catch (IOException ex) {
            throw new NetworkException("IO Problem", ex);
        }
        catch (Throwable ex) {
            throw new NetworkException("Internal Problem", ex);
        }
    }

    /**
     * Checks whether the TCP connection is open.
     *
     * @return true if the socket is open, false otherwise
     */
    public boolean isOpen()
    {
        return !m_socket.isClosed();
    }

    /**
     * Returns the underlying {@link Socket} instance.
     *
     * @return the underlying {@code Socket}
     */
    public Socket getSocket()
    {
        return m_socket;
    }

    /**
     * Receives data with specified {@code length} from the server and stores it into the specified byte array.
     * {@code offset} parameter can be used for offsetting the start index of the byte array.
     * @param data   the byte array to store the received data
     * @param offset the start offset in the array at which the data is written
     * @param length number of bytes to read
     * @return the number of bytes read, or -1 if the end of the stream is reached before any data is read, or
     * 0 when no bytes are read
     * @throws IOException if an I/O error occurs while reading from the stream
     */
    public int receive(byte [] data, int offset, int length)
    {
        return TcpUtil.receive(m_socket, data, offset, length);
    }

    /**
     * Receives data from the server and stores it into the specified byte array. This method uses default index offset
     * as 0 and default length to receive is as big as incoming data.
     *
     * @param data the byte array to store the received data
     * @return the number of bytes read
     */
    public int receive(byte [] data)
    {
        return TcpUtil.receive(m_socket, data);
    }

    /**
     * Sends the specified {@code byte} to the server.
     *
     * @param data   the byte array containing the data to send
     * @param offset the start offset in the array from which the data is sent
     * @param length the number of bytes to send
     * @return the number of bytes sent
     */
    public int send(byte [] data, int offset, int length)
    {
        return TcpUtil.send(m_socket, data, offset, length);
    }

    /**
     * Sends the specified byte array data to the server.
     *
     * @param data the byte array containing the data to send
     * @return the number of bytes sent
     */
    public int send(byte [] data)
    {
        return TcpUtil.send(m_socket, data);
    }

    /**
     * Receives a single byte from the server.
     *
     * @return the received byte
     */
    public byte receiveByte()
    {
        return TcpUtil.receiveByte(m_socket);
    }

    /**
     * Receives a short value from the server.
     *
     * @return the received short value
     */
    public short receiveShort()
    {
        return TcpUtil.receiveShort(m_socket);
    }

    /**
     * Receives an integer value from the server.
     *
     * @return the received integer value
     */
    public int receiveInt()
    {
        return TcpUtil.receiveInt(m_socket);
    }

    /**
     * Receives a long value from the server.
     *
     * @return the received long value
     */
    public long receiveLong()
    {
        return TcpUtil.receiveLong(m_socket);
    }

    /**
     * Receives a float value from the server.
     *
     * @return the received float value
     */
    public float receiveFloat()
    {
        return TcpUtil.receiveFloat(m_socket);
    }

    /**
     * Receives a double value from the server.
     *
     * @return the received double value
     */
    public double receiveDouble()
    {
        return TcpUtil.receiveDouble(m_socket);
    }

    /**
     * Receives a character from the server.
     *
     * @return the received character
     */
    public char receiveChar()
    {
        return TcpUtil.receiveChar(m_socket);
    }

    /**
     * Receives a string from the {@code socket} using default charset (UTF_8)
     *
     * @return the received string
     */
    public String receiveStringViaLength()
    {
        return TcpUtil.receiveStringViaLength(m_socket);
    }

    /**
     * Receives a string from the {@code socket}, using the specified {@code charset}.
     *
     * @param charset the charset to use for decoding the string
     * @return the received string
     */
    public String receiveStringViaLength(Charset charset)
    {
        return TcpUtil.receiveStringViaLength(m_socket, charset);
    }

    /**
     * Receives a string of the specified length from the server using default charset (UTF_8).
     *
     * @param length the number of bytes to read as a string
     * @return the received string
     */
    public String receiveString(int length)
    {
        return TcpUtil.receiveString(m_socket, length);
    }

    /**
     * Receives a string of the specified length from the server using the specified charset.
     *
     * @param length  the number of bytes to read as a string
     * @param charset the charset to use for decoding the string
     * @return the received string
     */
    public String receiveString(int length, Charset charset)
    {
        return TcpUtil.receiveString(m_socket, length, charset);
    }

    /**
     * Receives a line of text from the server, using the default charset (UTF-8).
     *
     * @return the received line of text
     */
    public String receiveLine()
    {
        return TcpUtil.receiveLine(m_socket);
    }

    /**
     * Receives a line of text from the server using the specified charset.
     *
     * @param charset the charset to use for decoding the line
     * @return the received line of text
     */
    public String receiveLine(Charset charset)
    {
        return TcpUtil.receiveLine(m_socket, charset);
    }

    /**
     * Receives a line of text from the server with the specified block size.
     *
     * @param blockSize the block size to use for reading the line
     * @return the received line of text
     */
    public String receiveLine(int blockSize)
    {
        return TcpUtil.receiveLine(m_socket, blockSize);
    }

    /**
     * Receives a line of text from the server using the specified charset and block size.
     *
     * @param charset   the charset to use for decoding the line
     * @param blockSize the block size to use for reading the line
     * @return the received line of text
     */
    public String receiveLine(Charset charset, int blockSize)
    {
        return TcpUtil.receiveLine(m_socket, charset, blockSize);
    }

    /**
     * Receives a file from the server and saves it to the absolute path obtained from {@code file} object.
     *
     * @param file reference to file object
     */
    public void receiveFile(File file)
    {
        TcpUtil.receiveFile(m_socket, file);
    }

    /**
     * Receives a file from the server and saves it to the specified file system path.
     *
     * @param path the path to save the received data to
     */
    public void receiveFile(String path)
    {
        TcpUtil.receiveFile(m_socket, path);
    }

    /**
     * Sends a single byte to the server.
     *
     * @param val the byte to send
     */
    public void sendByte(byte val)
    {
        TcpUtil.sendByte(m_socket, val);
    }

    /**
     * Sends a short value to the server.
     *
     * @param val the short value to send
     */
    public void sendShort(short val)
    {
        TcpUtil.sendShort(m_socket, val);
    }

    /**
     * Sends an integer value to the server.
     *
     * @param val the integer value to send
     */
    public void sendInt(int val)
    {
        TcpUtil.sendInt(m_socket, val);
    }

    /**
     * Sends a long value to the server.
     *
     * @param val the long value to send
     */
    public void sendLong(long val)
    {
        TcpUtil.sendLong(m_socket, val);
    }

    /**
     * Sends a float value to the server.
     *
     * @param val the float value to send
     */
    public void sendFloat(float val)
    {
        TcpUtil.sendFloat(m_socket, val);
    }

    /**
     * Sends a double value to the server.
     *
     * @param val the double value to send
     */
    public void sendDouble(double val)
    {
        TcpUtil.sendDouble(m_socket, val);
    }

    /**
     * Sends a character to the server.
     *
     * @param val the character to send
     */
    public void sendChar(char val)
    {
        TcpUtil.sendChar(m_socket, val);
    }

    /**
     * Consecutively sends specified {@code str}'s length and {@code str} data using the default
     * charset (UTF_8)
     * @param str     the string to send
     * @throws NetworkException if any problem occurs while sending through the socket
     */
    public void sendStringViaLength(String str)
    {
        TcpUtil.sendStringViaLength(m_socket, str);
    }

    /**
     * Consecutively sends specified {@code str}'s length and {@code str} data using the specified
     * and {@code charset}.
     * @param str     the string to send
     * @param charset the charset to use for encoding the string
     * @throws NetworkException if any problem occurs while sending through the socket
     */
    public void sendStringViaLength(String str, Charset charset)
    {
        TcpUtil.sendStringViaLength(m_socket, str, charset);
    }

    /**
     * Sends the specified {@code str} to the server using the default charset.
     *
     * @param str the string to send
     */
    public void sendString(String str)
    {
        TcpUtil.sendString(m_socket, str);
    }

    /**
     * Sends the specified {@code str} to the server using the specified charset.
     *
     * @param str     the string to send
     * @param charset the charset to use for encoding the string
     */
    public void sendString(String str, Charset charset)
    {
        TcpUtil.sendString(m_socket, str, charset);
    }

    /**
     * Sends a line of text {@code str} to the server using default charset.
     *
     * @param str the line of text to send
     */
    public void sendLine(String str)
    {
        TcpUtil.sendLine(m_socket, str);
    }

    /**
     * Sends a line of text {@code str} to the server using the specified charset.
     *
     * @param str     the line of text to send
     * @param charset the charset to use for encoding the text
     */
    public void sendLine(String str, Charset charset)
    {
        TcpUtil.sendLine(m_socket, str, charset);
    }

    /**
     * Sends a file specified as {@code file} object to the server via specified {@code blockSize}.
     *
     * @param file      reference to file object
     * @param blockSize the block size to use for sending the file
     */
    public void sendFile(File file, int blockSize)
    {
        TcpUtil.sendFile(m_socket, file, blockSize);
    }

    /**
     * Sends a file at file system {@code path} to the server via specified {@code blockSize}.
     *
     * @param path      the path of the file to send
     * @param blockSize the block size to use for sending the file
     */
    public void sendFile(String path, int blockSize)
    {
        TcpUtil.sendFile(m_socket, path, blockSize);
    }

    @Override
    public void close()
    {
        try {
            m_socket.close();
        }
        catch (IOException ex) {
            throw new NetworkException("close", ex);
        }
    }
}
