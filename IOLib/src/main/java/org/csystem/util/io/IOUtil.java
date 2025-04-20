package org.csystem.util.io;

import java.io.*;

/**
 * Utility class providing input/output stream operations.
 * This class contains methods for copying streams and serializing/deserializing objects.
 */
public final class IOUtil {
	/**
	 * Private constructor to prevent instantiation.
	 */
	private IOUtil()
	{}

	/**
	 * Copies data from an {@link InputStream} to an {@link OutputStream} using the specified block size.
	 *
	 * @param src       The source input stream.
	 * @param dest      The destination output stream.
	 * @param blockSize The size of the buffer used for copying.
	 * @param flush     Whether to flush the output stream after copying.
	 * @throws IOException If an I/O error occurs.
	 */
	public static void copy(InputStream src, OutputStream dest, int blockSize, boolean flush) throws IOException
	{
		int read;
		byte[] buf = new byte[blockSize];

		while ((read = src.read(buf)) > 0)
			dest.write(buf, 0, read);

		if (flush)
			dest.flush();
	}

	/**
	 * Copies data from an {@link InputStream} to an {@link OutputStream} using the specified block size.
	 * The output stream is flushed by default.
	 *
	 * @param src       The source input stream.
	 * @param dest      The destination output stream.
	 * @param blockSize The size of the buffer used for copying.
	 * @throws IOException If an I/O error occurs.
	 */
	public static void copy(InputStream src, OutputStream dest, int blockSize) throws IOException
	{
		copy(src, dest, blockSize, true);
	}

	/**
	 * Deserializes an object from the given {@link InputStream}.
	 *
	 * @param is The input stream containing the serialized object.
	 * @param <T> The type of the deserialized object.
	 * @return The deserialized object.
	 * @throws StreamException If an error occurs during deserialization.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(InputStream is)
	{
		try {
			ObjectInputStream ois = new ObjectInputStream(is);
			return (T) ois.readObject();
		}
		catch (Throwable ex) {
			throw new StreamException("IOUtil.deserialize", ex);
		}
	}

	/**
	 * Serializes an object and writes it to the given {@link OutputStream}.
	 *
	 * @param os The output stream where the object will be written.
	 * @param t The object to serialize.
	 * @param <T> The type of the object to serialize.
	 * @throws StreamException If an error occurs during serialization.
	 */
	public static <T> void serialize(OutputStream os, T t)
	{
		try {
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(t);
		}
		catch (Throwable ex) {
			throw new StreamException("IOUtil.serialize", ex);
		}
	}
}