/*----------------------------------------------------------------------
    FILE        : UdpUtil.java
    AUTHOR      : Oğuz Karan
    LAST UPDATE : 21.04.2023

    Utility class for UDP socket operations

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package com.karandev.util.net;

import com.karandev.util.net.exception.NetworkException;

import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class UdpUtil {
    /**
     *
     * @param data
     * @param host
     * @param port
     * @return
     * @throws UnknownHostException
     */
    private static DatagramPacket createDatagramPacket(byte [] data, String host, int port) throws UnknownHostException
    {
        return new DatagramPacket(data, 0, data.length, InetAddress.getByName(host), port);
    }

    /**
     *
     * @param length
     * @return
     */
    private static DatagramPacket createDatagramPacket(int length)
    {
        return new DatagramPacket(new byte[length], length);
    }

    private UdpUtil()
    {
    }

    /**
     *
     * @param host
     * @param port
     * @param val
     */
    public static void sendByte(String host, int port, byte val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            sendByte(datagramSocket, host, port, val);
        }
        catch (SocketException ex) {
            throw new NetworkException("UdpUtil.sendByte", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param val
     */
    public static void sendByte(DatagramSocket datagramSocket, String host, int port, byte val)
    {
        try {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendByte", ex);
        }
    }

    /**
     *
     * @param host
     * @param port
     * @param val
     */
    public static void sendShort(String host, int port, short val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            sendShort(datagramSocket, host, port, val);
        }
        catch (SocketException ex) {
            throw new NetworkException("UdpUtil.sendShort", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param val
     */
    public static void sendShort(DatagramSocket datagramSocket, String host, int port, short val)
    {
        try {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendShort", ex);
        }
    }

    /**
     *
     * @param host
     * @param port
     * @param val
     */
    public static void sendInt(String host, int port, int val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            sendInt(datagramSocket, host, port, val);
        }
        catch (SocketException ex) {
            throw new NetworkException("UdpUtil.sendInt", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param val
     */
    public static void sendInt(DatagramSocket datagramSocket, String host, int port, int val)
    {
        try {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendInt", ex);
        }
    }

    /**
     *
     * @param host
     * @param port
     * @param val
     */
    public static void sendLong(String host, int port, long val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            sendLong(datagramSocket, host, port, val);
        }
        catch (SocketException ex) {
            throw new NetworkException("UdpUtil.sendLong", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param val
     */
    public static void sendLong(DatagramSocket datagramSocket, String host, int port, long val)
    {
        try {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendLong", ex);
        }
    }

    /**
     *
     * @param host
     * @param port
     * @param val
     */
    public static void sendFloat(String host, int port, float val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            sendFloat(datagramSocket, host, port, val);
        }
        catch (SocketException ex) {
            throw new NetworkException("UdpUtil.sendFloat", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param val
     */
    public static void sendFloat(DatagramSocket datagramSocket, String host, int port, float val)
    {
        try {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendFloat", ex);
        }
    }

    /**
     *
     * @param host
     * @param port
     * @param val
     */
    public static void sendDouble(String host, int port, double val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            sendDouble(datagramSocket, host, port, val);
        }
        catch (SocketException ex) {
            throw new NetworkException("UdpUtil.sendDouble", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param val
     */
    public static void sendDouble(DatagramSocket datagramSocket, String host, int port, double val)
    {
        try {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendDouble", ex);
        }
    }

    /**
     *
     * @param host
     * @param port
     * @param ch
     */
    public static void sendChar(String host, int port, char ch)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            sendChar(datagramSocket, host, port, ch);
        }
        catch (SocketException ex) {
            throw new NetworkException("UdpUtil.sendChar", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param ch
     */
    public static void sendChar(DatagramSocket datagramSocket, String host, int port, char ch)
    {
        try {
            byte [] data = BitConverter.getBytes(ch);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendChar", ex);
        }
    }

    /**
     *
     * @param host
     * @param port
     * @param val
     */
    public static void sendBoolean(String host, int port, boolean val)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            sendBoolean(datagramSocket, host, port, val);
        }
        catch (SocketException ex) {
            throw new NetworkException("UdpUtil.sendBoolean", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param val
     */
    public static void sendBoolean(DatagramSocket datagramSocket, String host, int port, boolean val)
    {
        try {
            byte [] data = BitConverter.getBytes(val);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendBoolean", ex);
        }
    }

    /**
     *
     * @param host
     * @param port
     * @param str
     */
    public static void sendString(String host, int port, String str)
    {
        sendString(host, port, str, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param str
     */
    public static void sendString(DatagramSocket datagramSocket, String host, int port, String str)
    {
        sendString(datagramSocket, host, port, str, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param host
     * @param port
     * @param str
     * @param charset
     */
    public static void sendString(String host, int port, String str, Charset charset)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            sendString(datagramSocket, host, port, str, charset);
        }
        catch (SocketException ex) {
            throw new NetworkException("UdpUtil.sendString", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param str
     * @param charset
     */
    public static void sendString(DatagramSocket datagramSocket, String host, int port, String str, Charset charset)
    {
        try {
            byte [] data = BitConverter.getBytes(str, charset);

            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendString", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param host
     * @param port
     * @param ints
     */
    ////////////////////
    public static void sendIntArray(DatagramSocket datagramSocket, String host, int port, int...ints)
    {
        try {
            var data = BitConverter.getBytes(ints);
            datagramSocket.send(createDatagramPacket(data, host, port));
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.sendIntArray", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @return
     */
    public static byte receiveByte(DatagramSocket datagramSocket)
    {
        try {
            DatagramPacket datagramPacket = createDatagramPacket(Byte.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Byte.BYTES)
                throw new NetworkException("Invalid data length");

            return datagramPacket.getData()[0];
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveByte", ex);
        }
    }

    /**
     *
     * @param port
     * @return
     */
    public static byte receiveByte(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return receiveByte(datagramSocket);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveByte", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @return
     */
    public static short receiveShort(DatagramSocket datagramSocket)
    {
        try {
            DatagramPacket datagramPacket = createDatagramPacket(Short.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Short.BYTES)
                throw new NetworkException("Invalid data length");

            return BitConverter.toShort(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveShort", ex);
        }
    }

    /**
     *
     * @param port
     * @return
     */
    public static short receiveShort(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return receiveShort(datagramSocket);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveShort", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @return
     */
    public static int receiveInt(DatagramSocket datagramSocket)
    {
        try {
            DatagramPacket datagramPacket = createDatagramPacket(Integer.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Integer.BYTES)
                throw new NetworkException("Invalid data length");

            return BitConverter.toInt(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveInt", ex);
        }
    }

    /**
     *
     * @param port
     * @return
     */
    public static int receiveInt(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return receiveInt(datagramSocket);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveInt", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @return
     */
    public static float receiveFloat(DatagramSocket datagramSocket)
    {
        try {
            DatagramPacket datagramPacket = createDatagramPacket(Float.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Float.BYTES)
                throw new NetworkException("Invalid data length");

            return BitConverter.toFloat(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveFloat", ex);
        }
    }

    /**
     *
     * @param port
     * @return
     */
    public static float receiveFloat(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return receiveFloat(datagramSocket);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveFloat", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @return
     */
    public static double receiveDouble(DatagramSocket datagramSocket)
    {
        try {
            DatagramPacket datagramPacket = createDatagramPacket(Double.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Double.BYTES)
                throw new NetworkException("Invalid data length");

            return BitConverter.toDouble(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveDouble", ex);
        }
    }

    /**
     *
     * @param port
     * @return
     */
    public static double receiveDouble(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return receiveDouble(datagramSocket);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveDouble", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @return
     */
    public static char receiveChar(DatagramSocket datagramSocket)
    {
        try {
            DatagramPacket datagramPacket = createDatagramPacket(Character.BYTES);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != Character.BYTES)
                throw new NetworkException("Invalid data length");

            return BitConverter.toChar(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveDouble", ex);
        }
    }

    /**
     *
     * @param port
     * @return
     */
    public static char receiveChar(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return receiveChar(datagramSocket);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveDouble", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @return
     */
    public static boolean receiveBoolean(DatagramSocket datagramSocket)
    {
        try {
            DatagramPacket datagramPacket = createDatagramPacket(1);

            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            if (length != 1)
                throw new NetworkException("Invalid data length");

            return BitConverter.toBoolean(datagramPacket.getData());
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveBoolean", ex);
        }
    }

    /**
     *
     * @param port
     * @return
     */
    public static boolean receiveBoolean(int port)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return receiveBoolean(datagramSocket);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveBoolean", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param maxLength
     * @return
     */
    public static String receiveString(DatagramSocket datagramSocket, int maxLength)
    {
        return receiveString(datagramSocket, maxLength, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param port
     * @param maxLength
     * @return
     */
    public static String receiveString(int port, int maxLength)
    {
        return receiveString(port, maxLength, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param datagramSocket
     * @param maxLength
     * @param charset
     * @return
     */
    public static String receiveString(DatagramSocket datagramSocket, int maxLength, Charset charset)
    {
        try {
            DatagramPacket datagramPacket = receiveDatagramPacket(datagramSocket, maxLength, charset);

            return BitConverter.toString(datagramPacket.getData(), 0, datagramPacket.getLength(), charset);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveString", ex);
        }
    }

    /**
     *
     * @param port
     * @param maxLength
     * @param charset
     * @return
     */
    public static String receiveString(int port, int maxLength, Charset charset)
    {
        try {
            DatagramPacket datagramPacket = receiveDatagramPacket(port, maxLength, charset);

            return BitConverter.toString(datagramPacket.getData(), 0, datagramPacket.getLength(), charset);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveString", ex);
        }
    }

    /**
     *
     * @param datagramSocket
     * @param maxLength
     * @return
     */
    public static DatagramPacket receiveDatagramPacket(DatagramSocket datagramSocket, int maxLength)
    {
        return receiveDatagramPacket(datagramSocket, maxLength, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param port
     * @param maxLength
     * @return
     */
    public static DatagramPacket receiveDatagramPacket(int port, int maxLength)
    {
        return receiveDatagramPacket(port, maxLength, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param datagramSocket
     * @param maxLength
     * @param charset
     * @return
     */
    public static DatagramPacket receiveDatagramPacket(DatagramSocket datagramSocket, int maxLength, Charset charset)
    {
        try {
            DatagramPacket datagramPacket = createDatagramPacket(maxLength);

            datagramSocket.receive(datagramPacket);

            return datagramPacket;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveStringPacket", ex);
        }
    }

    /**
     *
     * @param port
     * @param maxLength
     * @param charset
     * @return
     */
    public static DatagramPacket receiveDatagramPacket(int port, int maxLength, Charset charset)
    {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return receiveDatagramPacket(datagramSocket, maxLength, charset);
        }
        catch (NetworkException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new NetworkException("UdpUtil.receiveStringPacket", ex);
        }
    }
}
