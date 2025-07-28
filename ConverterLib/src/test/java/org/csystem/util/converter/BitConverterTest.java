package org.csystem.util.converter;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

class BitConverterTest {
    @Test
    void testGetBytesAndToPrimitive_byte() {
        byte value = 42;
        byte[] bytes = BitConverter.getBytes(value);
        assertEquals(1, bytes.length);
        assertEquals(value, BitConverter.toByte(bytes));
    }

    @Test
    void testGetBytesAndToPrimitive_short() {
        short value = 12345;
        byte[] bytes = BitConverter.getBytes(value);
        assertEquals(2, bytes.length);
        assertEquals(value, BitConverter.toShort(bytes));
    }

    @Test
    void testGetBytesAndToPrimitive_int() {
        int value = 123456789;
        byte[] bytes = BitConverter.getBytes(value);
        assertEquals(4, bytes.length);
        assertEquals(value, BitConverter.toInt(bytes));
    }

    @Test
    void testGetBytesAndToPrimitive_long() {
        long value = 9876543210L;
        byte[] bytes = BitConverter.getBytes(value);
        assertEquals(8, bytes.length);
        assertEquals(value, BitConverter.toLong(bytes));
    }

    @Test
    void testGetBytesAndToPrimitive_char() {
        char value = 'A';
        byte[] bytes = BitConverter.getBytes(value);
        assertEquals(2, bytes.length);
        assertEquals(value, BitConverter.toChar(bytes));
    }

    @Test
    void testGetBytesAndToPrimitive_double() {
        double value = Math.PI;
        byte[] bytes = BitConverter.getBytes(value);
        assertEquals(8, bytes.length);
        assertEquals(value, BitConverter.toDouble(bytes), 1e-10);
    }

    @Test
    void testGetBytesAndToPrimitive_float() {
        float value = 3.14f;
        byte[] bytes = BitConverter.getBytes(value);
        assertEquals(4, bytes.length);
        assertEquals(value, BitConverter.toFloat(bytes), 1e-6f);
    }

    @Test
    void testGetBytesAndToPrimitive_boolean() {
        assertArrayEquals(new byte[]{1}, BitConverter.getBytes(true));
        assertArrayEquals(new byte[]{0}, BitConverter.getBytes(false));
        assertTrue(BitConverter.toBoolean(new byte[]{1}));
        assertFalse(BitConverter.toBoolean(new byte[]{0}));
    }

    @Test
    void testGetBytesAndToPrimitive_String() {
        String str = "Hello, world!";
        byte[] bytes = BitConverter.getBytes(str);
        assertEquals(str, BitConverter.toString(bytes));
        byte[] bytesUtf16 = BitConverter.getBytes(str, StandardCharsets.UTF_16);
        assertEquals(str, BitConverter.toString(bytesUtf16, StandardCharsets.UTF_16));
    }

    @Test
    void testArrayConversions_int() {
        int[] arr = {1, 2, 3, 4};
        byte[] bytes = BitConverter.getBytes(arr);
        int[] result = BitConverter.toIntArray(bytes, arr.length);
        assertArrayEquals(arr, result);
    }

    @Test
    void testArrayConversions_double() {
        double[] arr = {1.1, 2.2, 3.3};
        byte[] bytes = BitConverter.getBytes(arr);
        double[] result = BitConverter.toDoubleArray(bytes, arr.length);
        assertArrayEquals(arr, result, 1e-10);
    }

    @Test
    void testArrayConversions_boolean() {
        boolean[] arr = {true, false, true};
        byte[] bytes = BitConverter.getBytes(arr);
        boolean[] result = BitConverter.toBooleanArray(bytes, arr.length);
        assertArrayEquals(arr, result);
    }

    @Test
    void testToLittleEndianAndToBigEndian_int() {
        int value = 0x12345678;
        int little = BitConverter.toLittleEndian(value);
        int big = BitConverter.toBigEndian(little);

        assertEquals(value, BitConverter.toBigEndian(BitConverter.toLittleEndian(big)));
    }

    @Test
    void testToByteArray() {
        byte[] arr = {10, 20, 30, 40};
        byte[] result = BitConverter.toByteArray(arr, 1, 2);
        assertArrayEquals(new byte[]{20, 30}, result);
    }
}

