package org.csystem.util.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link IOUtil} class.
 */
public class IOUtilTest {

    @Test
    void testCopy() throws IOException
    {
        byte[] inputData = "Hello, World!".getBytes();
        InputStream inputStream = new ByteArrayInputStream(inputData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        IOUtil.copy(inputStream, outputStream, 4);

        assertArrayEquals(inputData, outputStream.toByteArray());
    }

    @Test
    void testCopyWithFlush() throws IOException
    {
        byte[] inputData = "Flush Test".getBytes();
        InputStream inputStream = new ByteArrayInputStream(inputData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        IOUtil.copy(inputStream, outputStream, 4, true);

        assertArrayEquals(inputData, outputStream.toByteArray());
    }

    @Test
    void testSerializeAndDeserialize() throws IOException
    {
        String original = "Serialization Test";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        IOUtil.serialize(outputStream, original);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        String deserialized = IOUtil.deserialize(inputStream);

        assertEquals(original, deserialized);
    }

    @Test
    void testDeserializeWithInvalidData()
    {
        byte[] invalidData = {0, 1, 2, 3, 4};
        InputStream inputStream = new ByteArrayInputStream(invalidData);

        assertThrows(StreamException.class, () -> IOUtil.deserialize(inputStream));
    }
}
