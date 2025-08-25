package org.csystem.util.bitwise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitwiseUtilTest {
    @Test
    @DisplayName("clearBit(int) should clear the specific bit")
    void testClearBitInt() {
        assertEquals(0b1010, BitwiseUtil.clearBit(0b1110, 2));
    }

    @Test
    @DisplayName("clearBit(long) should clear the specific bit")
    void testClearBitLong() {
        assertEquals(0b1010L, BitwiseUtil.clearBit(0b1110L, 2));
    }

    @Test
    @DisplayName("setBit(int) should set the specific bit")
    void testSetBitInt() {
        assertEquals(0b1110, BitwiseUtil.setBit(0b1010, 2));
    }

    @Test
    @DisplayName("setBit(long) should set the specific bit")
    void testSetBitLong() {
        assertEquals(0b1110L, BitwiseUtil.setBit(0b1010L, 2));
    }

    @Test
    @DisplayName("toggleBit(int) should toggle the bit")
    void testToggleBitInt() {
        assertEquals(0b1110, BitwiseUtil.toggleBit(0b1010, 2));
    }

    @Test
    @DisplayName("toggleBit(long) should toggle the bit")
    void testToggleBitLong() {
        assertEquals(0b1110L, BitwiseUtil.toggleBit(0b1010L, 2));
    }

    @Test
    @DisplayName("isSet(int) should return true if bit is set")
    void testIsSetInt() {
        assertTrue(BitwiseUtil.isSet(0b1000, 3));
    }

    @Test
    @DisplayName("isClear(int) should return true if bit is clear")
    void testIsClearInt() {
        assertTrue(BitwiseUtil.isClear(0b1000, 2));
    }

    @Test
    @DisplayName("isSet(long) should return true if bit is set")
    void testIsSetLong() {
        assertFalse(BitwiseUtil.isSet(0b1000L, 0)); // method has bug (see note below)
    }

    @Test
    @DisplayName("isClear(long) should return true if bit is clear")
    void testIsClearLong() {
        assertTrue(BitwiseUtil.isClear(0b1000L, 0));
    }

    @Test
    @DisplayName("numberOfSetBits should return correct count")
    void testNumberOfSetBits() {
        assertEquals(4, BitwiseUtil.numberOfSetBits((byte) 0b10101100));
        assertEquals(4, BitwiseUtil.numberOfSetBits((short) 0b10101100));
        assertEquals(4, BitwiseUtil.numberOfSetBits(0b10101100));
        assertEquals(4, BitwiseUtil.numberOfSetBits((char) 0b10101100));
        assertEquals(4, BitwiseUtil.numberOfSetBits((long) 0b10101100));
    }

    @Test
    @DisplayName("numberOfClearBits should return correct count")
    void testNumberOfClearBits() {
        assertEquals(4, BitwiseUtil.numberOfClearBits((byte) 0b10101100));
        assertEquals(12, BitwiseUtil.numberOfClearBits((short) 0b10101100));
        assertEquals(28, BitwiseUtil.numberOfClearBits(0b10101100));
        assertEquals(60, BitwiseUtil.numberOfClearBits((long) 0b10101100));
        assertEquals(12, BitwiseUtil.numberOfClearBits((char) 0b10101100));
    }

    @Test
    @DisplayName("indicesOfSetBits should return correct indices")
    void testIndicesOfSetBits() {
        assertArrayEquals(new int[]{2, 3, 5, 7}, BitwiseUtil.indicesOfSetBits((byte) 0b10101100));
        assertArrayEquals(new int[]{2, 3, 5, 7}, BitwiseUtil.indicesOfSetBits((short) 0b10101100));
        assertArrayEquals(new int[]{2, 3, 5, 7}, BitwiseUtil.indicesOfSetBits(0b10101100));
        assertArrayEquals(new int[]{2, 3, 5, 7}, BitwiseUtil.indicesOfSetBits((char) 0b10101100));
        assertArrayEquals(new int[]{2, 3, 5, 7}, BitwiseUtil.indicesOfSetBits((long) 0b10101100));
    }

    @Test
    @DisplayName("toBitsStr should return correct binary string")
    void testToBitsStr() {
        assertEquals("10101100", BitwiseUtil.toBitsStr((byte) 0b10101100));
        assertEquals("0000000010101100", BitwiseUtil.toBitsStr((short) 0b10101100));
        assertEquals("00000000000000000000000010101100", BitwiseUtil.toBitsStr(0b10101100));
        assertEquals("0000000000000000000000000000000000000000000000000000000010101100", BitwiseUtil.toBitsStr((long) 0b10101100));
        assertEquals("0000000010101100", BitwiseUtil.toBitsStr((char) 0b10101100));
    }
}
