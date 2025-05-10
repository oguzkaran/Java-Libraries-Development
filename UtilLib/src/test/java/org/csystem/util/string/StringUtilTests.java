package org.csystem.util.string;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.csystem.util.string.StringUtil.*;

public class StringUtilTests {

    @Test
    public void givenStringAndInt_whenSubstractCharacters_thenCheckEquals()
    {
        var testString = "test";
        var substractionCount = 32;
        var expected = "TEST";

        assertEquals(expected, subtractAllCharsWith(testString, substractionCount));
    }

    @Test
    public void givenStringAndInt_whenAddCharacters_thenCheckEquals()
    {
        var testString = "TEST";
        var addCount = 32;
        var expected = "test";

        assertEquals(expected, addAllCharsWith(testString, addCount));
    }

    @Test
    public void givenTwoStrings_whenTheyAreAnagram_thenAssertTrue()
    {
        var testStringFirst = "anastas";
        var testStringSecond = "satsana";

        assertTrue(areAnagram(testStringFirst, testStringSecond));
    }

    @Test
    public void givenTwoStrings_whenTheyAreNotAnagram_thenAssertFalse()
    {
        var testStringFirst = "testStringFirst";
        var testStringSecond = "testStringSecond";

        assertFalse(areAnagram(testStringFirst, testStringSecond));
    }

    @Test
    public void givenString_whenItsCharactersAreDistinct_thenAssertTrue()
    {
        var testStringFirst = "String";
        assertTrue(areCharactersDistinct(testStringFirst));
    }

    @Test
    public void givenString_whenItsCharactersAreNotDistinct_thenAssertTrue()
    {
        var testStringFirst = "TestString";
        assertFalse(areCharactersDistinct(testStringFirst));
    }

    @Test
    public void givenString_thenCapitalizeAndCheckEquals()
    {
        var testString = "test";
        var expected = "Test";

        assertEquals(expected, capitalize(testString));
    }

    @Test
    public void givenString_thenChangeCaseOfTheCharactersAndCheckEquals()
    {
        var testString = "TeSTStrInG";
        var expected = "tEstsTRiNg";

        assertEquals(expected, changeCase(testString));
    }

    @Test
    public void givenTwoStrings_thenCalculateTheAppearanceOfTheSecond_AndCheckEquals()
    {
        var firstString = "testtesttesttes";
        var appearancesString = "test";
        var expectedCount = 3;

        assertEquals(expectedCount, getCount(firstString, appearancesString));
    }

    @Test
    public void givenString_whenContainsNotLetterCharacter_ThenReturnsViaOnlyCharactersAndCheckEquals()
    {
        var testString = "test12^+&!t1es4t";
        var expected = "testtest";

        assertEquals(expected, getLetters(testString));
    }

    @Test
    public void givenENString_whenItsIsogram_ThenAssertTrue()
    {
        var testString = "abcdefghijklmnopqrstuvwxyz";
        assertTrue(isIsogramEN(testString));
    }

    @Test
    public void givenTRString_whenItsIsogram_ThenAssertTrue()
    {
        var testString = "abcçdefgğhıijklmnoöprsştuüvyz";
        assertTrue(isIsogramTR(testString));
    }

    @Test
    public void givenENString_whenItsNotIsogram_ThenAssertFalse()
    {
        var testString = "test";
        assertFalse(isIsogramEN(testString));
    }

    @Test
    public void givenTRString_whenItsNotIsogram_ThenAssertFalse()
    {
        var testString = "test";
        assertFalse(isIsogramTR(testString));
    }

    @Test
    public void givenString_whenItsPalindrome_thenAssertTrue()
    {
        var testString = "eye";
        assertTrue(isPalindrome(testString));
    }

    @Test
    public void givenString_whenItsNotPalindrome_thenAssertFalse()
    {
        var testString = "test";
        assertFalse(isPalindrome(testString));
    }

    @Test
    public void givenENString_whenItsPangram_ThenAssertTrue()
    {
        var testString = "abcdefghijklmnopqrstuvwxyz";
        assertTrue(isPangramEN(testString));
    }

    @Test
    public void givenTRString_whenItsPangram_ThenAssertTrue()
    {
        var testString = "abcçdefgğhıijklmnoöprsştuüvyz";
        assertTrue(isPangramTR(testString));
    }

    @Test
    public void givenENString_whenItsNotPangram_ThenAssertFalse()
    {
        var testString = "test";
        assertFalse(isPangramEN(testString));
    }

    @Test
    public void givenTRString_whenItsNotPangram_ThenAssertFalse()
    {
        var testString = "test";
        assertFalse(isPangramTR(testString));
    }

    @Test
    public void givenString_ThenAddPaddingFromStart_AndCheckEquals()
    {
        var testString = "test";
        var newStringLength = 8;
        var expected = "    test";

        assertEquals(expected, padLeft(testString, newStringLength));
    }

    @Test
    public void givenString_ThenAddPaddingFromStartViaGivenCharacter_AndCheckEquals()
    {
        var testString = "test";
        var newStringLength = 8;
        var character = 'a';
        var expected = "aaaatest";

        assertEquals(expected, padLeft(testString, newStringLength, character));
    }

    @Test
    public void givenString_ThenAddPaddingFromEnd_AndCheckEquals()
    {
        var testString = "test";
        var newStringLength = 8;
        var expected = "test    ";

        assertEquals(expected, padRight(testString, newStringLength));
    }

    @Test
    public void givenString_ThenAddPaddingFromEndViaGivenCharacter_AndCheckEquals()
    {
        var testString = "test";
        var newStringLength = 8;
        var character = 'a';
        var expected = "testaaaa";

        assertEquals(expected, padRight(testString, newStringLength, character));
    }

    @Test
    public void givenString_ThenReverseTheCharacters_AndCheckEquals()
    {
        var testString = "test";
        var expected = "tset";
        assertEquals(expected, reverse(testString));
    }

    @Test
    public void givenTwoStrings_thenDetermineDistrictCharacters_AndCheckEquals()
    {
        var firstString = "firstString";
        var secondString = "secondString";
        var expected = "f";

        assertEquals(expected, squeeze(firstString, secondString));
    }

    @Test
    public void givenString_thenSplitItAccordingToGivenDelimiter_AndCheckEquals()
    {
        var testString = "f;i;r;s;t;S;t;r;i;n;g;";
        var delimiter = ";";
        var expectedArray = new String[] {"f", "i", "r", "s", "t", "S", "t", "r", "i", "n", "g"};

        assertArrayEquals(expectedArray, split(testString, delimiter));
    }
}
