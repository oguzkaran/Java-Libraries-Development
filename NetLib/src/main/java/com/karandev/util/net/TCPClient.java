package com.karandev.util.net;

import com.karandev.util.net.exception.NetworkException;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Immutable TCP class for TCP socket operations
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
*/

/**
 * The type Tcp client.
 */
public class TCPClient implements Closeable {
    private final Socket m_socket;

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
     * Instantiates a new Tcp client.
     *
     * @param host the host
     * @param port the port
     */
    public TCPClient(String host, int port)
    {
        this(getInetAddress(host), port);
    }

    /**
     * Instantiates a new Tcp client.
     *
     * @param inetAddress the inet address
     * @param port        the port
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
     * Is open boolean.
     *
     * @return the boolean
     */
    public boolean isOpen()
    {
        return !m_socket.isClosed();
    }

    /**
     * Gets socket.
     *
     * @return the socket
     */
    public Socket getSocket()
    {
        return m_socket;
    }

    /**
     * Receive int.
     *
     * @param data   the data
     * @param offset the offset
     * @param length the length
     * @return the int
     */
    public int receive(byte [] data, int offset, int length)
    {
        return TcpUtil.receive(m_socket, data, offset, length);
    }

    /**
     * Receive int.
     *
     * @param data the data
     * @return the int
     */
    public int receive(byte [] data)
    {
        return TcpUtil.receive(m_socket, data);
    }

    /**
     * Send int.
     *
     * @param data   the data
     * @param offset the offset
     * @param length the length
     * @return the int
     */
    public int send(byte [] data, int offset, int length)
    {
        return TcpUtil.send(m_socket, data, offset, length);
    }

    /**
     * Send int.
     *
     * @param data the data
     * @return the int
     */
    public int send(byte [] data)
    {
        return TcpUtil.send(m_socket, data);
    }

    /**
     * Receive byte byte.
     *
     * @return the byte
     */
    public byte receiveByte()
    {
        return TcpUtil.receiveByte(m_socket);
    }

    /**
     * Receive short short.
     *
     * @return the short
     */
    public short receiveShort()
    {
        return TcpUtil.receiveShort(m_socket);
    }

    /**
     * Receive int int.
     *
     * @return the int
     */
    public int receiveInt()
    {
        return TcpUtil.receiveInt(m_socket);
    }

    /**
     * Receive long long.
     *
     * @return the long
     */
    public long receiveLong()
    {
        return TcpUtil.receiveLong(m_socket);
    }

    /**
     * Receive float float.
     *
     * @return the float
     */
    public float receiveFloat()
    {
        return TcpUtil.receiveFloat(m_socket);
    }

    /**
     * Receive double double.
     *
     * @return the double
     */
    public double receiveDouble()
    {
        return TcpUtil.receiveDouble(m_socket);
    }

    /**
     * Receive char char.
     *
     * @return the char
     */
    public char receiveChar()
    {
        return TcpUtil.receiveChar(m_socket);
    }

    /**
     * Receive string via length string.
     *
     * @return the string
     */
    public String receiveStringViaLength()
    {
        return TcpUtil.receiveStringViaLength(m_socket);
    }

    /**
     * Receive string via length string.
     *
     * @param charset the charset
     * @return the string
     */
    public String receiveStringViaLength(Charset charset)
    {
        return TcpUtil.receiveStringViaLength(m_socket, charset);
    }

    /**
     * Receive string string.
     *
     * @param length the length
     * @return the string
     */
    public String receiveString(int length)
    {
        return TcpUtil.receiveString(m_socket, length);
    }

    /**
     * Receive string string.
     *
     * @param length  the length
     * @param charset the charset
     * @return the string
     */
    public String receiveString(int length, Charset charset)
    {
        return TcpUtil.receiveString(m_socket, length, charset);
    }

    /**
     * Receive line string.
     *
     * @return the string
     */
    public String receiveLine()
    {
        return TcpUtil.receiveLine(m_socket);
    }

    /**
     * Receive line string.
     *
     * @param charset the charset
     * @return the string
     */
    public String receiveLine(Charset charset)
    {
        return TcpUtil.receiveLine(m_socket, charset);
    }

    /**
     * Receive line string.
     *
     * @param blockSize the block size
     * @return the string
     */
    public String receiveLine(int blockSize)
    {
        return TcpUtil.receiveLine(m_socket, blockSize);
    }

    /**
     * Receive line string.
     *
     * @param charset   the charset
     * @param blockSize the block size
     * @return the string
     */
    public String receiveLine(Charset charset, int blockSize)
    {
        return TcpUtil.receiveLine(m_socket, charset, blockSize);
    }

    /**
     * Receive file.
     *
     * @param file the file
     */
    public void receiveFile(File file)
    {
        TcpUtil.receiveFile(m_socket, file);
    }

    /**
     * Receive file.
     *
     * @param path the path
     */
    public void receiveFile(String path)
    {
        TcpUtil.receiveFile(m_socket, path);
    }

    /**
     * Send byte.
     *
     * @param val the val
     */
    public void sendByte(byte val)
    {
        TcpUtil.sendByte(m_socket, val);
    }

    /**
     * Send short.
     *
     * @param val the val
     */
    public void sendShort(short val)
    {
        TcpUtil.sendShort(m_socket, val);
    }

    /**
     * Send int.
     *
     * @param val the val
     */
    public void sendInt(int val)
    {
        TcpUtil.sendInt(m_socket, val);
    }

    /**
     * Send long.
     *
     * @param val the val
     */
    public void sendLong(long val)
    {
        TcpUtil.sendLong(m_socket, val);
    }

    /**
     * Send float.
     *
     * @param val the val
     */
    public void sendFloat(float val)
    {
        TcpUtil.sendFloat(m_socket, val);
    }

    /**
     * Send double.
     *
     * @param val the val
     */
    public void sendDouble(double val)
    {
        TcpUtil.sendDouble(m_socket, val);
    }

    /**
     * Send char.
     *
     * @param val the val
     */
    public void sendChar(char val)
    {
        TcpUtil.sendChar(m_socket, val);
    }

    /**
     * Send string via length.
     *
     * @param str the str
     */
    public void sendStringViaLength(String str)
    {
        TcpUtil.sendStringViaLength(m_socket, str);
    }

    /**
     * Send string via length.
     *
     * @param str     the str
     * @param charset the charset
     */
    public void sendStringViaLength(String str, Charset charset)
    {
        TcpUtil.sendStringViaLength(m_socket, str, charset);
    }

    /**
     * Send string.
     *
     * @param str the str
     */
    public void sendString(String str)
    {
        TcpUtil.sendString(m_socket, str);
    }

    /**
     * Send string.
     *
     * @param str     the str
     * @param charset the charset
     */
    public void sendString(String str, Charset charset)
    {
        TcpUtil.sendString(m_socket, str, charset);
    }

    /**
     * Send line.
     *
     * @param str the str
     */
    public void sendLine(String str)
    {
        TcpUtil.sendLine(m_socket, str);
    }

    /**
     * Send line.
     *
     * @param str     the str
     * @param charset the charset
     */
    public void sendLine(String str, Charset charset)
    {
        TcpUtil.sendLine(m_socket, str, charset);
    }

    /**
     * Send file.
     *
     * @param file      the file
     * @param blockSize the block size
     */
    public void sendFile(File file, int blockSize)
    {
        TcpUtil.sendFile(m_socket, file, blockSize);
    }

    /**
     * Send file.
     *
     * @param path      the path
     * @param blockSize the block size
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
