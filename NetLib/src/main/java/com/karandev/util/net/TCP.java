package com.karandev.util.net;

import com.karandev.util.net.exception.NetworkException;

import java.io.File;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Immutable TCP class for TCP socket operations
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 */

public class TCP {
    private final Socket m_socket;

    /**
     *
     * @param socket
     */
    public TCP(Socket socket)
    {
        m_socket = socket;
    }

    /**
     *
     * @param socket
     * @param timeout
     * @throws NetworkException
     */
    public TCP(Socket socket, int timeout)
    {
        try {
            m_socket = socket;
            socket.setSoTimeout(timeout);
        }
        catch (Throwable ex) {
            throw new NetworkException("TCP(socket, timeout)", ex);
        }
    }

    /**
     *
     * @return
     */
    public boolean isOpen()
    {
        return !m_socket.isClosed();
    }

    /**
     *
     * @return
     */
    public Socket getSocket()
    {
        return m_socket;
    }

    /**
     *
     * @param data
     * @param offset
     * @param length
     * @return
     * @throws NetworkException
     */
    public int receive(byte [] data, int offset, int length)
    {
        return TcpUtil.receive(m_socket, data, offset, length);
    }

    /**
     *
     * @param data
     * @return
     * @throws NetworkException
     */
    public int receive(byte [] data)
    {
        return TcpUtil.receive(m_socket, data);
    }

    /**
     *
     * @param data
     * @param offset
     * @param length
     * @return
     * @throws NetworkException
     */
    public int send(byte [] data, int offset, int length)
    {
        return TcpUtil.send(m_socket, data, offset, length);
    }

    /**
     *
     * @param data
     * @return
     * @throws NetworkException
     */
    public int send(byte [] data)
    {
        return TcpUtil.send(m_socket, data);
    }

    /**
     *
     * @return
     * @throws NetworkException
     */
    public byte receiveByte()
    {
        return TcpUtil.receiveByte(m_socket);
    }

    /**
     *
     * @return
     * @throws NetworkException
     */
    public short receiveShort()
    {
        return TcpUtil.receiveShort(m_socket);
    }

    /**
     *
     * @return
     * @throws NetworkException
     */
    public int receiveInt()
    {
        return TcpUtil.receiveInt(m_socket);
    }

    /**
     *
     * @return
     * @throws NetworkException
     */
    public long receiveLong()
    {
        return TcpUtil.receiveLong(m_socket);
    }

    /**
     *
     * @return
     * @throws NetworkException
     */
    public float receiveFloat()
    {
        return TcpUtil.receiveFloat(m_socket);
    }

    /**
     *
     * @return
     * @throws NetworkException
     */
    public double receiveDouble()
    {
        return TcpUtil.receiveDouble(m_socket);
    }

    /**
     *
     * @return
     * @throws NetworkException
     */
    public char receiveChar()
    {
        return TcpUtil.receiveChar(m_socket);
    }

    /**
     *
     * @return
     * @throws NetworkException
     */
    public String receiveStringViaLength()
    {
        return TcpUtil.receiveStringViaLength(m_socket);
    }

    /**
     *
     * @param charset
     * @return
     * @throws NetworkException
     */
    public String receiveStringViaLength(Charset charset)
    {
        return TcpUtil.receiveStringViaLength(m_socket, charset);
    }

    /**
     *
     * @param length
     * @return
     * @throws NetworkException
     */
    public String receiveString(int length)
    {
        return TcpUtil.receiveString(m_socket, length);
    }

    /**
     *
     * @param length
     * @param charset
     * @return
     * @throws NetworkException
     */
    public String receiveString(int length, Charset charset)
    {
        return TcpUtil.receiveString(m_socket, length, charset);
    }

    /**
     *
     * @return
     * @throws NetworkException
     * @throws NullPointerException
     * @throws NegativeArraySizeException
     */
    public String receiveLine()
    {
        return TcpUtil.receiveLine(m_socket);
    }

    /**
     *
     * @param charset
     * @return
     * @throws NetworkException
     * @throws NullPointerException
     * @throws NegativeArraySizeException
     */
    public String receiveLine(Charset charset)
    {
        return TcpUtil.receiveLine(m_socket, charset);
    }

    /**
     *
     * @param blockSize
     * @return
     * @throws NetworkException
     * @throws NullPointerException
     * @throws NegativeArraySizeException
     */
    public String receiveLine(int blockSize)
    {
        return TcpUtil.receiveLine(m_socket, blockSize);
    }

    /**
     *
     * @param charset
     * @param blockSize
     * @return
     * @throws NetworkException
     * @throws NullPointerException
     * @throws NegativeArraySizeException
     */
    public String receiveLine(Charset charset, int blockSize)
    {
        return TcpUtil.receiveLine(m_socket, charset, blockSize);
    }

    /**
     *
     * @param file
     * @throws NetworkException
     */
    public void receiveFile(File file)
    {
        TcpUtil.receiveFile(m_socket, file);
    }

    /**
     *
     * @param path
     * @throws NetworkException
     */
    public void receiveFile(String path)
    {
        TcpUtil.receiveFile(m_socket, path);
    }

    /**
     *
     * @param val
     * @throws NetworkException
     */
    public void sendByte(byte val)
    {
        TcpUtil.sendByte(m_socket, val);
    }

    /**
     *
     * @param val
     * @throws NetworkException
     */
    public void sendShort(short val)
    {
        TcpUtil.sendShort(m_socket, val);
    }

    /**
     *
     * @param val
     * @throws NetworkException
     */
    public void sendInt(int val)
    {
        TcpUtil.sendInt(m_socket, val);
    }

    /**
     *
     * @param val
     * @throws NetworkException
     */
    public void sendLong(long val)
    {
        TcpUtil.sendLong(m_socket, val);
    }

    /**
     *
     * @param val
     * @throws NetworkException
     */
    public void sendFloat(float val)
    {
        TcpUtil.sendFloat(m_socket, val);
    }

    /**
     *
     * @param val
     * @throws NetworkException
     */
    public void sendDouble(double val)
    {
        TcpUtil.sendDouble(m_socket, val);
    }

    /**
     *
     * @param val
     * @throws NetworkException
     */
    public void sendChar(char val)
    {
        TcpUtil.sendChar(m_socket, val);
    }

    /**
     *
     * @param str
     * @throws NetworkException
     */
    public void sendStringViaLength(String str)
    {
        TcpUtil.sendStringViaLength(m_socket, str);
    }

    /**
     *
     * @param str
     * @param charset
     * @throws NetworkException
     */
    public void sendStringViaLength(String str, Charset charset)
    {
        TcpUtil.sendStringViaLength(m_socket, str, charset);
    }

    /**
     *
     * @param str
     * @throws NetworkException
     */
    public void sendString(String str)
    {
        TcpUtil.sendString(m_socket, str);
    }

    /**
     *
     * @param str
     * @param charset
     * @throws NetworkException
     */
    public void sendString(String str, Charset charset)
    {
        TcpUtil.sendString(m_socket, str, charset);
    }

    /**
     *
     * @param str
     * @throws NetworkException
     */
    public void sendLine(String str)
    {
        TcpUtil.sendLine(m_socket, str);
    }

    /**
     *
     * @param str
     * @param charset
     * @throws NetworkException
     */
    public void sendLine(String str, Charset charset)
    {
        TcpUtil.sendLine(m_socket, str, charset);
    }

    /**
     *
     * @param file
     * @param blockSize
     * @throws NetworkException
     */
    public void sendFile(File file, int blockSize)
    {
        TcpUtil.sendFile(m_socket, file, blockSize);
    }

    /**
     *
     * @param path
     * @param blockSize
     * @throws NetworkException
     */
    public void sendFile(String path, int blockSize)
    {
        TcpUtil.sendFile(m_socket, path, blockSize);
    }
}
