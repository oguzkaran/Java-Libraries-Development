package com.karandev.util.net;

import com.karandev.util.net.exception.NetworkException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Utility class for TCP socket operations, including sending and receiving primitive type values, texts and files.
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public final class TcpUtil {
	private static final int DEFAULT_LINE_BLOCK_SIZE = 2048;

	/**
	 * <p>Receives a specified number of bytes from the input stream and stores them into the given byte array.</p>
	 * <p>This method is called by {@link #receive(Socket,byte[],int,int) receive(Socket,byte[],int,int)} and
	 * {@link #receive(Socket, byte[]) receive(Socket,byte[])} for handling byte-by-byte transfer from a socket.</p>
	 * <p>It is possible to handle data transfer when incoming bytes are not available in a single read operation.</p>
	 * @param dis the DataInputStream to read from
	 * @param data the byte array to store the read data from the DataInputStream
	 * @param offset the starting index offset of the byte array
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
	 * <p>Receives a specified number of bytes from the input stream and stores them into the given byte array.</p>
	 * <p>This method is called by {@link #receive(Socket, byte[], int, int) receive(Socket,byte[],int,int)} and
	 * {@link #receive(Socket, byte[]) receive(Socket,byte[])} for handling data transfer from a socket.</p> <p>It uses
	 * a fixed offset value of 0 and receives up to the length of byte array.</p>
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
	 * <p>Sends specified {@code data} to an output stream {@code dos}</p>
	 * <p>This method first determines initial stages of transmission by instantiating with given {@code offset} and {@code length}.
	 * Upon entering the loop a complete transmission attempt is made, followed by a flush. After the flush, size of successfully
	 * written data is measured via subtracting the initial written byte count from current written byte count. Based on number of
	 * successful writes, total bytes written, the number of bytes remaining and current offset is updated and method goes on
	 * looping until there are no more bytes remaining.</p>
	 * @param dos the DataOutputStream to write to
	 * @param data the byte array used for writing to the DataOutputStream
	 * @param offset the starting index offset of the byte array
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
	 * <p>Sends specified {@code data} to an output stream {@code dos}</p>
	 * <p>This method works the same way as {@link #send(DataOutputStream, byte[], int, int)}, but instead
	 * uses fixed starting offset of 0 and length parameter as big as {@code data}</p>
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
	 * <p>Returns an Optional SocketServer with the first available port number in the given
	 * {@code minPort}, {@code maxPort} (inclusive)
	 * range, having the specified {@code backlog} value for maximum number of pending connections on the socket.
	 * Other-wise returns an empty Optional.</p>
	 * @param backlog requested maximum length of the queue of incoming connections
	 * @param minPort minimum value for the port number
	 * @param maxPort maximum value for the port number
	 * @return an Optional SocketServer with given backlog value,
	 * or empty optional if all the ports are busy and cannot be assigned
	 * @throws IllegalArgumentException if the provided port numbers are outside the valid range
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
	 * <p>Returns an Optional SocketServer with the first available port number in the given
	 * {@code minPort}, {@code maxPort} (inclusive)
	 * range. Other-wise returns an empty Optional.</p>
	 * @param minPort minimum value for the port number
	 * @param maxPort maximum value for the port number
	 * @return an Optional SocketServer, or empty optional if all the ports are busy and cannot be assigned
	 * @throws IllegalArgumentException if the provided port numbers are outside the valid range
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
	 * <p>Returns an Optional SocketServer with the first available port number in the given vararg parameter {@code ports},
	 * and sets the maximum number of pending connections on the socket using the {@code backlog}.
	 * Other-wise returns an empty Optional.</p>
	 * @param backlog requested maximum length of the queue of incoming connections
	 * @param ports vararg parameter for the available ports to be connected
	 * @return an Optional SocketServer, or empty optional if all the {@code ports} are busy and cannot be assigned
	 * @throws IllegalArgumentException if the provided port numbers are outside the valid range
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
	 * <p>Returns an Optional SocketServer with the first available port number in the given vararg parameter {@code ports}.
	 * Other-wise returns an empty Optional.</p>
	 * @param ports vararg parameter for the available ports to be connected
	 * @return an Optional SocketServer, or empty optional if all the ports are busy and cannot be assigned
	 * @throws IllegalArgumentException if the provided port numbers are outside the valid range
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
	 * <p>Receives specified number of bytes from a socket and stores them into the specified byte array.</p>
	 * <p>This method wraps {@link #receive(DataInputStream, byte[], int, int)} for handling byte-by-byte transfer and
	 * is called by receiveXXX methods for retrieving data of different primitive types.</p>
	 * @param socket the socket to receive data from
	 * @param data the byte array to store the read data from the socket
	 * @param offset the starting index offset of the byte array
	 * @param length the number of the bytes to be read
	 * @return the number of bytes read, or -1 if the end of the stream is reached before any data is read,
	 * or 0 when no bytes are read
	 * @throws NetworkException if any problem occurs while receiving from the socket
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
	 * <p>Receives data from a socket and stores them into the specified byte array.</p>
	 * <p>Number of bytes to read is as long as the incoming data and starting offset is 0</p>
	 * <p>This method wraps {@link #receive(DataInputStream, byte[], int, int)} for handling byte-by-byte transfer and
	 * is called by receiveXXX methods for retrieving data of different primitive types.</p>
	 * @param socket the socket to receive data from
	 * @param data the byte array to store the read data from the socket
	 * @return the number of bytes read, or -1 if the end of the stream is reached before any data is read,
	 * or 0 when no bytes are read
	 * @throws NetworkException if any problem occurs while receiving from the socket
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
	 * <p>Sends specified number of bytes over a socket.</p>
	 * <p>This method wraps {@link #send(DataOutputStream, byte[], int, int)} for handling byte-by-byte transfer and
	 * is called by sendXXX methods for sending data of different primitive types.</p>
	 * @param socket the socket to send data to
	 * @param data the byte array used for sending the data through the socket
	 * @param offset the starting index offset of the byte array to be sent
	 * @param length the maximum length of data from the byte array to be sent
	 * @return the total number of bytes sent through the socket
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
	 * <p>Sends specified {@code data} over a socket.</p>
	 * <p>Number of bytes to read is as long as the incoming data and starting offset is 0.</p>
	 * <p>This method wraps {@link #send(DataOutputStream, byte[], int, int)} for handling byte-by-byte transfer and
	 * is called by sendXXX methods for sending data of different primitive types.</p>
	 * @param socket the socket to send data to
	 * @param data the byte array used for sending the data through the socket
	 * @return the total number of bytes sent through the socket
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
	 * <p>Receives a single byte from the {@code socket}</p>
	 * <p>This method allocates a buffer for a byte and calls {@link #receive(Socket, byte[])}.</p>
	 * <p>Received byte is returned.</p>
	 * @param socket any valid and open socket
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
	 * <p>Receives a short value from the {@code socket}</p>
	 * This method allocates a buffer for 2 bytes and calls {@link #receive(Socket, byte[])}.
	 * Byte array filled by incoming data is converted to short by {@link BitConverter#toShort(byte[])}
	 * and returned.
	 * @param socket any valid and open socket
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
	 * <p>Receives an integer value from the {@code socket}</p>
	 * This method allocates a buffer for 4 bytes and calls {@link #receive(Socket, byte[])}.
	 * Byte array filled by incoming data is converted to Int by {@link BitConverter#toInt(byte[])}
	 * and returned.
	 * @param socket any valid and open socket
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

	/**<p>Receives an integer value from the {@code socket}</p>
	 * This method allocates a buffer for 8 bytes and calls {@link #receive(Socket, byte[])}.
	 * Byte array filled by incoming data is converted to long by {@link BitConverter#toLong(byte[])}
	 * and returned.
	 * @param socket any valid and open socket
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

	/**<p>Receives a float value from the {@code socket}</p>
	 * This method allocates a buffer for 4 bytes and calls {@link #receive(Socket, byte[])}.
	 * Byte array filled by incoming data is converted to float by {@link BitConverter#toFloat(byte[])}
	 * and returned.
	 * @param socket any valid and open socket
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

	/**<p>Receives a double value from the {@code socket}</p>
	 * This method allocates a buffer for 8 bytes and calls {@link #receive(Socket, byte[])}.
	 * Byte array filled by incoming data is converted to double by {@link BitConverter#toDouble(byte[])}
	 * and returned.
	 * @param socket any valid and open socket
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

	/**<p>Receives a char value from the {@code socket}</p>
	 * This method allocates a buffer for 2 bytes and calls {@link #receive(Socket, byte[])}.
	 * Byte array filled by incoming data is converted to char by {@link BitConverter#toChar(byte[])}
	 * and returned.
	 * @param socket any valid and open socket
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
	 * <p>Consecutively receives a string's length and the string itself from the {@code socket}, using the default charset UTF-8.</p>
	 * <p>This method works the same way as {@link #receiveStringViaLength(Socket, Charset)}, other than using default charset (UTF-8) </p>
	 * @param socket any valid and open socket
	 * @return the received string
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String receiveStringViaLength(Socket socket)
	{
		return receiveStringViaLength(socket, StandardCharsets.UTF_8);
	}

	/**
	 * <p>Consecutively receives a string's length and the string itself from the {@code socket}, using the specified {@code charset}.</p>
	 * <p>This method is to be used with {@link #sendStringViaLength(Socket, String, Charset) sendStringViaLength} and expects to receive length of the
	 * incoming string before successfully decoding it. Length information is received via {@link #receiveInt(Socket) receiveInt}
	 * and byte array is allocated accordingly.</p>
	 * @param socket any valid and open socket
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
	 * Receives a string having {@code length} number of characters from the {@code socket}, using
	 * the default charset UTF-8.
	 * @param socket any valid and open socket
	 * @param length length the number of bytes to read
	 * @return the received string
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String receiveString(Socket socket, int length)
	{
		return receiveString(socket, length, StandardCharsets.UTF_8);
	}

	/**
	 * Receives a string having {@code length} number of characters from the {@code socket}, using
	 * the specified {@code charset}.
	 * @param socket any valid and open socket
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
	 * @param socket any valid and open socket
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
	 * @param socket any valid and open socket
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
	 * @param socket any valid and open socket
	 * @param blockSize block size of the internal buffer. If zero, no data is read
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
	 * @param socket any valid and open socket
	 * @param charset the charset of the text
	 * @param blockSize block size of the internal buffer. If zero, no data is read
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
	 * @param socket any valid and open socket
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
	 * @param socket any valid and open socket
	 * @param charset the charset of the text
	 * @return all received lines from the socket as a string array
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String [] receiveLines(Socket socket, Charset charset)
	{
		return receiveLines(socket, charset, DEFAULT_LINE_BLOCK_SIZE);
	}

	/**
	 * Receives text from the {@code socket}, using the specified {@code blockSize},
	 * then splits the text around matching line break.
	 * @param socket any valid and open socket
	 * @param blockSize block size of the internal buffer. If zero, no data is read
	 * @return all received lines from the socket as a string array
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static String [] receiveLines(Socket socket, int blockSize)
	{
		return receiveLines(socket, StandardCharsets.UTF_8, blockSize);
	}

	/**
	 * Reads a string and splits the text around matching line break,
	 * using the specified {@code charset} and {@code blockSize}.
	 * <p>This method will work properly if sender close socket after the send process</p>
	 * @param socket any valid and open socket
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
	 * <p>Receives a file from the socket and writes it to the path contained in {@code file} object</p>
	 * <p>This method functions the same way as {@link #receiveFile(Socket, String)})}, other than
	 * having path argument as {@link File}, instead of {@link String}.</p>
	 * @param socket any valid and open socket
	 * @param file the file that will be used for saving the received file
	 * @throws NetworkException if any problem occurs while receiving from the socket
	 */
	public static void receiveFile(Socket socket, File file)
	{
		receiveFile(socket, file.getAbsolutePath());
	}

	/**
	 * <p>Receives a file from the {@code socket} and writes it to specified {@code path}.</p>
	 * <p>This method instantiates a {@code fos} with specified {@code path}, allocates a buffer area
	 * of file size via calling {@link #receiveInt(Socket)}, then loops until no file chunks remain.
	 * Finally the resulting file data is written via calling {@link FileOutputStream}'s write method
	 * with the resulting byte array and with a starting offset of 0.</p>
	 * @param socket any valid and open socket
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

	/**<p>Sends a single byte value to the {@code socket}</p>
	 * <p>This method allocates a new byte buffer via {@link BitConverter#getBytes(byte)} and calls {@link #send(Socket, byte[])}
	 * with the return value. </p>
	 * @param socket any valid and open socket
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

	/**<p>Sends a single byte value to the {@code socket}</p>
	 * <p>This method allocates a new byte buffer via {@link BitConverter#getBytes(short)} and calls {@link #send(Socket, byte[])}
	 * with the return value. </p>
	 * @param socket any valid and open socket
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

	/**<p>Sends an integer value to the {@code socket}</p>
	 * <p>This method allocates a new byte buffer via {@link BitConverter#getBytes(int)} and then calls {@link #send(Socket, byte[])}
	 * with the return value. </p>
	 * @param socket any valid and open socket
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

	/**<p>Sends a long value to the {@code socket}</p>
	 * <p>This method allocates a new byte buffer via {@link BitConverter#getBytes(long)} and then calls {@link #send(Socket, byte[])}
	 * with the return value. </p>
	 * @param socket any valid and open socket
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

	/**<p>Sends a float value to the {@code socket}</p>
	 * <p>This method allocates a new byte buffer via {@link BitConverter#getBytes(float)} and then calls {@link #send(Socket, byte[])}
	 * with the return value. </p>
	 * @param socket any valid and open socket
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

	/**<p>Sends a double value to the {@code socket}</p>
	 * <p>This method allocates a new byte buffer via {@link BitConverter#getBytes(double)} and then calls {@link #send(Socket, byte[])}
	 * with the return value. </p>
	 * @param socket any valid and open socket
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

	/**<p>Sends a char to the {@code socket}</p>
	 * <p>This method allocates a new byte buffer via {@link BitConverter#getBytes(char)} and then calls {@link #send(Socket, byte[])}
	 * with the return value. </p>
	 * @param socket any valid and open socket
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
	 * <p>Consecutively sends specified {@code str}'s length and then the string itself using the specified {@code socket}.
	 * <p>This method is to be used with {@link #receiveStringViaLength(Socket, Charset)} method for properly decoding the strings.</p>
	 * <p>Uses standard charset (UTF-8)</p>
	 * @param socket any valid and open socket
	 * @param str the string to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendStringViaLength(Socket socket, String str)
	{
		sendStringViaLength(socket, str, StandardCharsets.UTF_8);
	}

	/**
	 * <p>Consecutively sends specified {@code str}'s length and then the string itself using the specified {@code socket}
	 * and {@code charset}.</p>
	 * <p>This method is to be used with {@link #receiveStringViaLength(Socket, Charset)} method for properly decoding the strings.</p>
 	 * @param socket any valid and open socket
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
	 * Sends the {@code str} to the {@code socket} using the default charset (UTF-8).
	 * @param socket any valid and open socket
	 * @param str the string to send
	 */
	public static void sendString(Socket socket, String str)
	{
		sendString(socket, str, StandardCharsets.UTF_8);
	}

	/**
	 * Sends the {@code str} to the {@code socket} using the specified {@code charset}.
	 * @param socket any valid and open socket
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
	 * Takes the text {@code str}, terminates it by line break and sends it to the {@code socket},
	 * using the default charset UTF-8.
	 * @param socket any valid and open socket
	 * @param str the text to send
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendLine(Socket socket, String str)
	{
		sendLine(socket, str, StandardCharsets.UTF_8);
	}

	/**
	 * Takes the text {@code str}, terminates it by line break and sends it to {@code socket},
	 * using the specified {@code charset}.
	 * @param socket any valid and open socket
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
	 * <p>Sends a {@code file} over the socket with specified block size.</p>
	 * <p>This method functions the same way as {@link #sendFile(Socket, String, int)}, other than
	 * requiting a {@link File} object.</p>
	 * @param socket any valid and open socket
	 * @param file the file to send
	 * @param blockSize block size of the internal buffer
	 * @throws NetworkException if any problem occurs while sending through the socket
	 */
	public static void sendFile(Socket socket, File file, int blockSize)
	{
		sendFile(socket, file.getAbsolutePath(), blockSize);
	}

	/**<p>Sends a file on given {@code path} over the socket with specified {@code blockSize}.</p>
	 * <p>This method allocates a byte array of {@code blockSize} and pushes the file in {@code path} into a
	 * {@link FileInputStream}. Then then loops by sending transferred byte amount and the actual data until EOF condition is met.</p>
	 * @param socket any valid and open socket
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
