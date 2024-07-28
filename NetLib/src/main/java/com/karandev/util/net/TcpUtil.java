package com.karandev.util.net;

import com.karandev.util.net.exception.NetworkException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Utility class for TCP socket operations such as sending and receiving primitive type values, text and files.
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public final class TcpUtil {
	private static final int DEFAULT_LINE_BLOCK_SIZE = 2048;

	/**
	 * Receives data from a given {@code dis} and stores it in the given {@code data}. {@code offset} parameter can be used
	 * for offsetting the start index of the byte array.
	 * @param dis the DataInputStream to read from
	 * @param data the byte array to store the read data from the DataInputStream
	 * @param offset the starting index offset in the byte array
	 * @param length the number of the bytes to be read
	 * @return the number of bytes read, or -1 if the end of the stream is reached before any data is read, or
	 * 0 when no bytes are read
	 * @throws IOException if an I/O error occurs while reading from the stream
	 */
	private static int receive(DataInputStream dis, byte [] data, int offset, int length) throws IOException
	{
	    int result;
	    int left = length, index = offset;

	    while (left > 0) {
	        if ((result = dis.read(data, index, left)) == -1)
	            return -1;
	        
	        if (result == 0)
	            break;
	        
	        index += result;
	        left -= result;
	    }

	    return index;
	}

	/**
	 * Receives data from a given {@code dis} and stores it in the {@code data}. Default read length
	 * is determined by the length of the provided byte array.
	 * @param dis the DataInputStream to read from
	 * @param data the byte array to store the read data from the DataInputStream
	 * @return the number of bytes read, or -1 if the end of the stream is reached before any data is read, or
	 * 0 when no bytes are read
	 * @throws IOException if an I/O error occurs while reading from the input stream
	 */
	private static int receive(DataInputStream dis, byte [] data) throws IOException
	{
	    return receive(dis, data, 0, data.length);
	}

	/**
	 * Sending data to a given {@code dos} from the {@code data} byte array. {@code offset} parameter can be used
	 * for offsetting the start index of the byte array to be sent.
	 * @param dos the DataOutputStream to write to
	 * @param data the byte array used for writing to the DataOutputStream
	 * @param offset the starting index offset in the byte array
	 * @param length the maximum length of data from the byte array to be written
	 * @return the total number of bytes written to the DataOutputStream
	 * @throws IOException if an I/O error occurs while writing from the output stream
	 */
	private static int send(DataOutputStream dos, byte [] data, int offset, int length) throws IOException
	{						
		int curOffset = offset;
		int left = length;
		int total = 0;
		int written;
		int initWritten = dos.size();
		
		while (total < length) {
			dos.write(data, curOffset, left);
			dos.flush();
			written = dos.size() - initWritten;
			total += written;			
			left -= written;
			curOffset += written;
		}

		dos.flush();

		return total;
	}

	/**
	 * Sending data to a given {@code dos} from the {@code data} byte array. Default length to be sent
	 * is determined by the length of the provided byte array.
	 * @param dos the DataOutputStream to write to
	 * @param data the byte array used for writing to the DataOutputStream
	 * @return the total number of bytes written to the DataOutputStream
	 * @throws IOException if an I/O error occurs while writing from the output stream
	 */
	private static int send(DataOutputStream dos, byte [] data) throws IOException
	{
	    return send(dos, data, 0, data.length);
	}

	private TcpUtil() {}

	/**
	 * Returns an Optional SocketServer with the first available port number in the given
	 * {@code minPort}, {@code maxPort} (inclusive)
	 * range having the specified {@code backlog} value for maximum number of pending connections on the socket.
	 * Other-wise returns an empty Optional.
	 * @param backlog requested maximum length of the queue of incoming connections
	 * @param minPort minimum value for the port number
	 * @param maxPort maximum value for the port number
	 * @return an Optional SocketServer with given backlog value, or empty optional if all the ports are busy and can
	 * not be assigned
	 */
	public static Optional<ServerSocket> getFirstAvailableSocket(int backlog, int minPort, int maxPort)
	{
		Optional<ServerSocket> result = Optional.empty();

		for (int port = minPort; port <= maxPort; ++port)
			try {
				result = Optional.of(new ServerSocket(port, backlog));
			}
			catch (IOException ignore) {
			}

		return result;
	}

	/**
	 * Returns an Optional SocketServer with the first available port number in the given
	 * {@code minPort}, {@code maxPort} (inclusive)
	 * range. Other-wise returns an empty Optional.
	 * @param minPort minimum value for the port number
	 * @param maxPort maximum value for the port number
	 * @return an Optional SocketServer, or empty optional if all the ports are busy and can not be assigned
	 */
	public static Optional<ServerSocket> getFirstAvailablePort(int minPort, int maxPort)
	{
		Optional<ServerSocket> result = Optional.empty();

		for (int port = minPort; port <= maxPort; ++port)
			try {
				result = Optional.of(new ServerSocket(port));
			}
			catch (IOException ignore) {
			}

		return result;
	}

	/**
	 * Returns an Optional SocketServer with the first available port number in the given vararg parameter {@code ports}
	 * with the maximum number of pending connections on the socket set by the {@code backlog}.
	 * Other-wise returns an empty Optional.
	 * @param backlog requested maximum length of the queue of incoming connections
	 * @param ports vararg parameter for the available ports to be connected
	 * @return an Optional SocketServer, or empty optional if all the {@code ports} are busy and can not be assigned
	 */
	public static Optional<ServerSocket> getFirstAvailableSocket(int backlog, int...ports)
	{
		Optional<ServerSocket> result = Optional.empty();

		for (var port : ports)
			try {
				result = Optional.of(new ServerSocket(port, backlog));
			}
			catch (IOException ignore) {
			}

		return result;
	}

	/**
	 * Returns an Optional SocketServer with the first available port number in the given vararg parameter {@code ports}.
	 * Other-wise returns an empty Optional.
	 * @param ports vararg parameter for the available ports to be connected
	 * @return an Optional SocketServer, or empty optional if all the ports are busy and can not be assigned
	 */
	public static Optional<ServerSocket> getFirstAvailableSocket(int...ports)
	{
		Optional<ServerSocket> result = Optional.empty();

		for (var port : ports)
			try {
				result = Optional.of(new ServerSocket(port));
			}
			catch (IOException ignore) {
			}

		return result;
	}

	/**
	 * Receives data from a {@code socket} and stores it in the specified byte array({@code data}) starting from
	 * index number {@code offset} with the given {@code length}.
	 * @param socket the Socket to receive data from
	 * @param data the byte array to store the read data from the socket
	 * @param offset the starting index offset in the byte array
	 * @param length the number of the bytes to be read
	 * @return the number of bytes read, or -1 if the end of the stream is reached before any data is read,
	 * or 0 when no bytes are read
	 * @throws NetworkException if ay problem occurs while receiving from the socket
	 */
	public static int receive(Socket socket, byte [] data, int offset, int length)
	{
		try {
			return receive(new DataInputStream(socket.getInputStream()), data, offset, length);
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receive", ex);
		}
	}

	/**
	 * Receives data from a Socket and stores it in the specified byte array. Default read length
	 * is determined by the length of the provided byte array.
	 * @param socket the Socket to receive data from
	 * @param data the byte array to store the read data from the socket
	 * @return the number of bytes read, or -1 if the end of the stream is reached before any data is read,
	 * or 0 when no bytes are read
	 * @throws NetworkException if ay problem occurs while receiving from the socket
	 */
	public static int receive(Socket socket, byte [] data)
	{
		try {
			return receive(socket, data, 0, data.length);
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receive", ex.getCause());
		}
	}

	/**
	 * Sends data over the {@code socket} from the specified byte array({@code data}) starting from
	 * index number {@code offset} with the given {@code length}.
	 * @param socket the Socket to receive data to
	 * @param data the byte array used for sending the data through the socket
	 * @param offset the starting index offset of the byte array to be sent
	 * @param length the maximum length of data from the byte array to be sent
	 * @return the total number of bytes sent through the Socket
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static int send(Socket socket, byte [] data, int offset, int length)
	{
		try {
			return send(new DataOutputStream(socket.getOutputStream()), data, offset, length);
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.send", ex);
		}
	}

	/**
	 * Sends data over the {@code socket} from the specified byte array({@code data}).
	 * @param socket the Socket to send data to
	 * @param data the byte array used for sending the data through the socket
	 * @return the total number of bytes sent through the Socket
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static int send(Socket socket, byte [] data)
	{
		try {
			return send(socket, data, 0, data.length);
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.send", ex.getCause());
		}
	}

	/**
	 * Receives a byte value from the {@code socket}.
	 * @param socket the Socket to receive byte value from
	 * @return the received byte value
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static byte receiveByte(Socket socket)
	{
		try {
			byte [] data = new byte[Byte.BYTES];

			receive(socket, data);

			return data[0];
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveByte", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveByte", ex);
		}
	}

	/**
	 * Receives a short value from the {@code socket}.
	 * @param socket the Socket to receive short value from
	 * @return the received short value
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static short receiveShort(Socket socket)
	{
		try {
			byte[] data = new byte[Short.BYTES];

			receive(socket, data);

			return BitConverter.toShort(data);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveShort", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveShort", ex);
		}
	}

	/**
	 * Receives an int value from the {@code socket}.
	 * @param socket the Socket to receive int value from
	 * @return the received int value
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static int receiveInt(Socket socket)
	{
		try {
			byte[] data = new byte[Integer.BYTES];

			receive(socket, data);

			return BitConverter.toInt(data);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveInt", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveInt", ex);
		}
	}

	/**
	 * Receives a long value from the {@code socket}.
	 * @param socket the Socket to receive long value from
	 * @return the received long value
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static long receiveLong(Socket socket)
	{
		try {
			byte[] data = new byte[Long.BYTES];

			receive(socket, data);

			return BitConverter.toLong(data);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveLong", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveLong", ex);
		}
	}

	/**
	 * Receives a float value from the {@code socket}.
	 * @param socket the Socket to receive float value from
	 * @return the received float value
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static float receiveFloat(Socket socket)
	{
		try {
			byte[] data = new byte[Float.BYTES];

			receive(socket, data);

			return BitConverter.toFloat(data);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveFloat", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveFloat", ex);
		}
	}

	/**
	 * Receives a double value from the {@code socket}.
	 * @param socket the Socket to receive double value from
	 * @return the received double value
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static double receiveDouble(Socket socket)
	{
		try {
			byte[] data = new byte[Double.BYTES];

			receive(socket, data);

			return BitConverter.toDouble(data);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveDouble", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveDouble", ex);
		}
	}

	/**
	 * Receives a char value from the {@code socket}.
	 * @param socket the Socket to receive char value from
	 * @return the received char value
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static char receiveChar(Socket socket)
	{
		try {
			byte[] data = new byte[Character.BYTES];

			receive(socket, data);

			return BitConverter.toChar(data);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveChar", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveChar", ex);
		}
	}

	/**
	 * Receives a string from the {@code socket} using the default charset UTF-8.
	 * @param socket the Socket to receive string from
	 * @return the received string
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String receiveStringViaLength(Socket socket)
	{
		return receiveStringViaLength(socket, StandardCharsets.UTF_8);
	}

	/**
	 * Receives a string value from the {@code socket} using the specified {@code charset}.
	 * @param socket the Socket to receive string from
	 * @param charset the charset of the text
	 * @return the received string
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String receiveStringViaLength(Socket socket, Charset charset)
	{
		try {
			byte[] data = new byte[receiveInt(socket)];

			receive(socket, data);

			return BitConverter.toString(data, charset);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveStringViaLength", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveStringViaLength", ex);
		}
	}

	/**
	 * Receives a string value having {@code length} number of characters from the {@code socket} using
	 * the default charset UTF-8.
	 * @param socket the Socket to receive string from
	 * @param length length the number of bytes to read
	 * @return the received string
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String receiveString(Socket socket, int length)
	{
		return receiveString(socket, length, StandardCharsets.UTF_8);
	}

	/**
	 * Receives a string value having {@code length} number of characters from the {@code socket} using
	 * the specified {@code charset}.
	 * @param socket the Socket to receive string from
	 * @param length the number of the bytes to be read
	 * @param charset the charset of the text
	 * @return the received string
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String receiveString(Socket socket, int length, Charset charset)
	{
		try {
			byte[] data = new byte[length];

			if (receive(socket, data) == -1)
				return null;

			return BitConverter.toString(data, charset);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveString", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveString", ex);
		}
	}

	/**
	 * Receives text data until the last line feed character from the {@code socket}, using the default charset (UTF-8)
	 * and the default block size {@link TcpUtil#DEFAULT_LINE_BLOCK_SIZE}.
	 * <p>This method will work properly if the sender closes the socket after the send process.</p>
	 * @param socket the Socket to receive text from
	 * @return the received line
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 * @throws NullPointerException if socket is null or charset is null
	 * @throws NegativeArraySizeException blockSize is less than zero
	 */
	public static String receiveLine(Socket socket)
	{
		return receiveLine(socket, StandardCharsets.UTF_8);
	}

	/**
	 * Receives text data until the last line feed character from the {@code socket}, using the specified {@code charset}
	 * and the default block size {@link TcpUtil#DEFAULT_LINE_BLOCK_SIZE}.
	 * <p>This method will work properly if the sender closes the socket after the send process.</p>
	 * @param socket the Socket to receive text from
	 * @param charset the charset of the text
	 * @return the received line
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 * @throws NullPointerException if socket is null or charset is null
	 * @throws NegativeArraySizeException blockSize is less than zero
	 */
	public static String receiveLine(Socket socket, Charset charset)
	{
		return receiveLine(socket, charset, DEFAULT_LINE_BLOCK_SIZE);
	}

	/**
	 * Receives text data until the last line feed character from the {@code socket}, using the default charset (UTF-8)
	 * and the specified {@code blockSize}.
	 * <p>This method will work properly if the sender closes the socket after the send process.</p>
	 * @param socket the Socket to receive text from
	 * @param blockSize block size of the internal buffer. If zero no data is read
	 * @return the received line
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 * @throws NullPointerException if socket is null or charset is null
	 * @throws NegativeArraySizeException blockSize is less than zero
	 */
	public static String receiveLine(Socket socket, int blockSize)
	{
		return receiveLine(socket, StandardCharsets.UTF_8, blockSize);
	}

	/**
	 * Receives text data until the last line feed character from the {@code socket}, using the specified {@code charset}
	 * and {@code blockSize}.
	 * <p>This method will work properly if sender close socket after the send process.</p>
	 * @param socket a valid and open socket
	 * @param charset the charset of the text
	 * @param blockSize block size of the internal buffer. If zero no data is read
	 * @return the received line
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 * @throws NullPointerException if socket is null or charset is null
	 * @throws NegativeArraySizeException blockSize is less than zero
	 */
	public static String receiveLine(Socket socket, Charset charset, int blockSize)
	{
		var sb = new StringBuilder();
		var buf = new byte[blockSize];

		try (socket) {
			while (true) {
				receive(socket, buf);
				var str = BitConverter.toString(buf, charset);
				var index = str.lastIndexOf('\n');

				if (index != -1) {
					sb.append(str, 0, index);
					break;
				}

				sb.append(str);
			}
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveLine", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveLine", ex);
		}

		return sb.toString();
	}

	/**
	 * Receives text using the default block size {@link TcpUtil#DEFAULT_LINE_BLOCK_SIZE} and the
	 * default charset UTF-8, then splits the text around matching line break.
	 * @param socket the Socket to receive an array of texts from
	 * @return all received lines from the socket as a string array
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String [] receiveLines(Socket socket)
	{
		return receiveLines(socket, StandardCharsets.UTF_8);
	}

	/**
	 * Receives text using the default block size {@link TcpUtil#DEFAULT_LINE_BLOCK_SIZE} and
	 * specified {@code charset}, then splits the text around matching line break.
	 * @param socket the Socket to receive an array of texts from
	 * @param charset the charset of the text
	 * @return all received lines from the socket as a string array
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String [] receiveLines(Socket socket, Charset charset)
	{
		return receiveLines(socket, charset, DEFAULT_LINE_BLOCK_SIZE);
	}

	/**
	 * Receives text from the {@code socket} using the specified {@code blockSize},
	 * then splits the text around matching line break.
	 * @param socket the Socket to receive an array of texts from
	 * @param blockSize block size of the internal buffer. If zero no data is read
	 * @return all received lines from the socket as a string array
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String [] receiveLines(Socket socket, int blockSize)
	{
		return receiveLines(socket, StandardCharsets.UTF_8, blockSize);
	}

	/**
	 * Receives a string split by end of line character
	 * This method reads a String and split the lines, using the specified {@code charset} and {@code blockSize}.
	 * <p>This method will work properly if sender close socket after the send process</p>
	 * @param socket a valid and open socket
	 * @param charset the charset of the text
	 * @param blockSize block size of the internal buffer
	 * @return all received lines from the socket
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String [] receiveLines(Socket socket, Charset charset, int blockSize)
	{
		try {
			var lines = receiveLine(socket, charset, blockSize);

			return lines.split("[\r\n]+");
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveLines", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveLines", ex);
		}
	}

	/**
	 * Receives a file from the {@code socket} and write it to {@code file}.
	 * @param socket the Socket to receive a File from
	 * @param file the file where the received file to be saved
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static void receiveFile(Socket socket, File file)
	{
		receiveFile(socket, file.getAbsolutePath());
	}

	/**
	 * Receives a file from the {@code socket} and writes it to {@code file}.
	 * @param socket the Socket to receive a File from
	 * @param path the saving path for the received file
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static void receiveFile(Socket socket, String path)
	{
		try (FileOutputStream fos = new FileOutputStream(path)) {
			int result;

			for (;;) {
				var size = receiveInt(socket);

				if (size <= 0)
					break;

				var data = new byte[size];

				result = receive(socket, data);
				fos.write(data, 0, result);
			}
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.receiveFile", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.receiveFile", ex);
		}
	}

	/**
	 * Sends a byte value to the {@code socket}.
	 * @param socket the Socket to send a byte value to
	 * @param val the byte value to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendByte(Socket socket, byte val)
	{
		try {
			send(socket, BitConverter.getBytes(val));
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendByte", ex);
		}
	}

	/**
	 * Sends a short value to the {@code socket}.
	 * @param socket the Socket to send a short value to
	 * @param val the short value to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendShort(Socket socket, short val)
	{
		try {
			send(socket, BitConverter.getBytes(val));
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendShort", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendShort", ex);
		}
	}

	/**
	 * Sends an int value to the {@code socket}.
	 * @param socket the Socket to send an int value to
	 * @param val the int value to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendInt(Socket socket, int val)
	{
		try {
			send(socket, BitConverter.getBytes(val));
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendInt", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendInt", ex);
		}
	}

	/**
	 * Sends a long value to the {@code socket}.
	 * @param socket the Socket to send a long value to
	 * @param val the long value to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendLong(Socket socket, long val)
	{
		try {
			send(socket, BitConverter.getBytes(val));
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendLong", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendLong", ex);
		}
	}

	/**
	 * Sends a float value to the {@code socket}.
	 * @param socket the Socket to send a float value to
	 * @param val the float value to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendFloat(Socket socket, float val)
	{
		try {
			send(socket, BitConverter.getBytes(val));
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendFloat", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendFloat", ex);
		}
	}

	/**
	 * Sends a double value to the {@code socket}.
	 * @param socket the Socket to send a double value to
	 * @param val the double value to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendDouble(Socket socket, double val)
	{
		try {
			send(socket, BitConverter.getBytes(val));
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendDouble", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendDouble", ex);
		}
	}

	/**
	 * Sends a char value to the {@code socket}.
	 * @param socket the Socket to send a char value to
	 * @param val the char value to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendChar(Socket socket, char val)
	{
		try {
			send(socket, BitConverter.getBytes(val));
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendChar", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendChar", ex);
		}
	}

	/**
	 * Consecutively sends specified {@code string} and string's length via specified
	 * {@code socket}.
	 * @param socket the Socket to send a string to
	 * @param str the string to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendStringViaLength(Socket socket, String str)
	{
		sendStringViaLength(socket, str, StandardCharsets.UTF_8);
	}

	/**
	 * Consecutively sends specified {@code str} and string's length via specified
	 * {@code socket} and {@code charset}.
	 * @param socket the Socket to send a string to
	 * @param str the string to send
	 * @param charset the charset of the text
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendStringViaLength(Socket socket, String str, Charset charset)
	{
		try {
			byte[] data = BitConverter.getBytes(str, charset);
			byte[] dataLen = BitConverter.getBytes(data.length);

			send(socket, dataLen);
			send(socket, data);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendStringViaLength", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendStringViaLength", ex);
		}
	}

	/**
	 * Sends a string value to the {@code socket} using the default charset (UTF-8).
	 * @param socket the Socket to send a string to
	 * @param str the string to send
	 */
	public static void sendString(Socket socket, String str)
	{
		sendString(socket, str, StandardCharsets.UTF_8);
	}

	/**
	 * Sends a string value to the {@code socket} using the specified {@code charset}.
	 * @param socket the Socket to send a string to
	 * @param str the string to send
	 * @param charset the charset of the text
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendString(Socket socket, String str, Charset charset)
	{
		try {
			byte[] data = BitConverter.getBytes(str, charset);

			send(socket, data);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendString", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendString", ex);
		}
	}

	/**
	 * Takes a text {@code str}, terminates it by line break and sends it to {@code socket},
	 * using the default charset UTF-8.
	 * @param socket the Socket to send a text to
	 * @param str the text to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendLine(Socket socket, String str)
	{
		sendLine(socket, str, StandardCharsets.UTF_8);
	}

	/**
	 * Takes a text {@code str}, terminates it by line break and sends it to {@code socket},
	 * using the specified {@code charset}.
	 * @param socket the Socket to send a text to
	 * @param str the text to send
	 * @param charset the charset of the text
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendLine(Socket socket, String str, Charset charset)
	{
		try {
			sendString(socket, String.format("%s\r\n", str), charset);
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendLine", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendLine", ex);
		}
	}

	/**
	 * Sends a {@code file} to the specified {@code socket} using the specified {@code blockSize}.
	 * @param socket the Socket to send a File to
	 * @param file the file to send
	 * @param blockSize block size of the internal buffer
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendFile(Socket socket, File file, int blockSize)
	{
		sendFile(socket, file.getAbsolutePath(), blockSize);
	}

	/**
	 * Creates a fileInputStream using the specified {@code path} and the {@code blockSize},
	 * then sends to specified {@code socket}.
	 * @param socket the Socket to send a File to
	 * @param path the path to the file to send
	 * @param blockSize block size of the internal buffer
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendFile(Socket socket, String path, int blockSize)
	{
		byte [] data = new byte[blockSize];

		try (FileInputStream fis = new FileInputStream(path)) {
			int result;

			for (;;) {
				result = fis.read(data);
				sendInt(socket, result);
				if (result <= 0)
					break;
				send(socket, data, 0, result);
			}
		}
		catch (NetworkException ex) {
			throw new NetworkException("TcpUtil.sendFile", ex.getCause());
		}
		catch (Throwable ex) {
			throw new NetworkException("TcpUtil.sendFile", ex);
		}
	}
}
