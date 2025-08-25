/*----------------------------------------------------------------------
	FILE        : StringUtil.java
	AUTHOR      : CSD Java group
	LAST UPDATE : 29.11.2021

	Utility class for string operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.*;

/**
 * Utility class for common string operations including text manipulation, validation, and generation.
 * Provides methods for working with both English and Turkish alphabets.
 */
public class StringUtil	{
	private static final String ms_alphabetTR;
	private static final String ms_alphabetEN;
	private static final String ms_alphabetCapitalsTR;
	private static final String ms_alphabetCapitalsEN;
	private static final String ms_alphabetAllTR;
	private static final String ms_alphabetAllEN;


	static {
		ms_alphabetTR = "abcçdefgğhıijklmnoöprsştuüvyz";
		ms_alphabetEN = "abcdefghijklmnopqrstuwxvyz";
		ms_alphabetCapitalsTR = "ABCÇDEFGĞJHIİJKLMNOÖPRSŞTUÜVYZ";
		ms_alphabetCapitalsEN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		ms_alphabetAllTR = ms_alphabetTR + ms_alphabetCapitalsTR;
		ms_alphabetAllEN = ms_alphabetEN + ms_alphabetCapitalsEN;
	}

	private static void incrementCounts(HashMap<Character, Integer> charMap, String s)
	{
		int length = s.length();

		for (int i = 0; i < length; ++i) {
			char c = s.charAt(i);
			int count = 1;

			if (charMap.containsKey(c))
				count = charMap.get(c) + 1;

			charMap.put(c, count);
		}
	}

	private static boolean decrementCounts(HashMap<Character, Integer> charMap, String s)
	{
		int length = s.length();

		for (int i = 0; i < length; ++i) {
			char c = s.charAt(i);

			if (!charMap.containsKey(c))
				return false;

			int count = charMap.get(c) - 1;

			if (count < 0)
				return false;

			charMap.put(c, count);
		}

		return true;
	}

	private StringUtil() {}

	/**
	 * Adds a specified value to each character's ASCII code in the string
	 * @param s the input string
	 * @param n the value to add to each character
	 * @return new string with modified characters
	 */
	public static String addAllCharsWith(String s, int n)
	{
		StringBuilder sb = new StringBuilder(s);
		int length = sb.length();

		for (int i = 0; i < length; ++i) {
			char ch = sb.charAt(i);
			sb.setCharAt(i, (char)(ch + n));
		}

		return sb.toString();
	}

	/**
	 * Checks if two strings are anagrams (contain same characters in any order)
	 * @param s1 first string to compare
	 * @param s2 second string to compare
	 * @return true if strings are anagrams, false otherwise
	 */
	public static boolean areAnagram(String s1, String s2)
	{
		if (s1.length() != s2.length())
			return false;

		HashMap<Character, Integer> charMap = new HashMap<>();

		incrementCounts(charMap, s1);

		if (!decrementCounts(charMap, s2))
			return false;

		for (int count : charMap.values())
			if (count != 0)
				return false;

		return true;
	}

	/**
	 * Checks if all characters in a string are distinct
	 * @param str the string to check
	 * @return true if all characters are unique, false otherwise
	 */
	public static boolean areCharactersDistinct(String str)
	{
		HashSet<Character> hashset = new HashSet<>();
		int length =  str.length();

		for (int i = 0; i < length; ++i)
			if (!hashset.add(str.charAt(i)))
				return false;

		return true;
	}

	/**
	 * Capitalizes the first character of a string and makes the rest lowercase
	 * @param s the string to capitalize
	 * @return capitalized string
	 */
	public static String capitalize(String s)
	{
		if (s.isEmpty())
			return s;
		
		return toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
	}

	/**
	 * Changes the case of each letter in the string (upper to lower and vice versa)
	 * @param s the string to process
	 * @return string with inverted cases
	 */
	public static String changeCase(String s)
	{
		StringBuilder sb = new StringBuilder(s);
		int length = sb.length();

		for (int i = 0; i < length; ++i) {
			char ch = sb.charAt(i);

			if (!Character.isLetter(ch))
				continue;

			sb.setCharAt(i, Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch));
		}

		return sb.toString();
	}

	/**
	 * Counts occurrences of a substring within another string
	 * @param s1 the string to search in
	 * @param s2 the substring to search for
	 * @return number of occurrences
	 */
	public static int getCount(String s1, String s2)
	{
		int count = 0;
		
		for (int index = -1; (index = s1.indexOf(s2, index + 1)) != -1; ++count)
			;		
		
		return count;
	}

	/**
	 * Extracts only letter characters from a string
	 * @param s the input string
	 * @return string containing only letters
	 */
	public static String getLetters(String s)
	{
		int len = s.length();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < len; ++i) {
			char c = s.charAt(i);
			if (isLetter(c))
				sb.append(c);
		}
		
		return sb.toString();
	}

	/**
	 * Generates random text using characters from a source string
	 * @param r Random instance for number generation
	 * @param n length of the generated text
	 * @param sourceText characters to choose from
	 * @return randomly generated string
	 */
	public static String getRandomText(Random r, int n, String sourceText)
	{
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; ++i)
			sb.append(sourceText.charAt(r.nextInt(sourceText.length())));

		return sb.toString();
	}

	/**
	 * Generates random text using Turkish alphabet characters
	 * @param n length of the generated text
	 * @return random Turkish text
	 */
	public static String getRandomTextTR(int n)
	{
		Random r = new Random();
		
		return getRandomTextTR(r, n);
	}

	/**
	 * Generates random text using Turkish alphabet characters with specified Random
	 * @param r Random instance for number generation
	 * @param n length of the generated text
	 * @return random Turkish text
	 */
	public static String getRandomTextTR(Random r, int n)
	{
		return getRandomText(r, n, ms_alphabetAllTR);
	}

	/**
	 * Generates multiple random Turkish texts with lengths between min and max
	 * @param r Random instance for number generation
	 * @param count number of strings to generate
	 * @param min minimum length of generated strings
	 * @param max maximum length of generated strings
	 * @return array of random Turkish strings
	 */
	public static String [] getRandomTextsTR(Random r, int count, int min, int max)
	{
		return Stream.generate(() -> getRandomTextTR(r, r.nextInt(max - min + 1) + min))
				.limit(count)
				.collect(Collectors.toList())
				.toArray(new String[count]);
	}

	/**
	 * Generates random text using English alphabet characters with specified Random
	 * @param r Random instance for number generation
	 * @param n length of the generated text
	 * @return random English text
	 */
	public static String getRandomTextEN(Random r, int n)
	{
		return getRandomText(r, n, ms_alphabetAllEN);
	}

	/**
	 * Generates random text using English alphabet characters
	 * @param n length of the generated text
	 * @return random English text
	 */
	public static String getRandomTextEN(int n)
	{
		Random r = new Random();

		return getRandomTextEN(r, n);
	}

	/**
	 * Generates multiple random English texts with lengths between min and max
	 * @param r Random instance for number generation
	 * @param count number of strings to generate
	 * @param min minimum length of generated strings
	 * @param max maximum length of generated strings
	 * @return array of random English strings
	 */
	public static String [] getRandomTextsEN(Random r, int count, int min, int max)
	{
		return Stream.generate(() -> getRandomTextEN(r, r.nextInt(max - min + 1) + min))
				.limit(count)
				.collect(Collectors.toList())
				.toArray(new String[count]);
	}

	/**
	 * Checks if a string is an isogram (each letter in alphabet appears exactly once)
	 * @param str the string to check
	 * @param alphabet reference alphabet to check against
	 * @return true if isogram, false otherwise
	 */
	public static boolean isIsogram(String str, String alphabet)
	{
		int [] counts = new int[alphabet.length()];
		int len = str.length();

		for (int i = 0; i < len; ++i) {
			char ch = Character.toLowerCase(str.charAt(i));

			int index = alphabet.indexOf(ch);

			if (index == -1)
				continue;

			++counts[index];

			if (counts[index] > 1)
				return false;
		}

		for (int val : counts)
			if (val == 0)
				return false;

		return true;
	}

	/**
	 * Checks if a string is an English isogram
	 * @param str the string to check
	 * @return true if English isogram, false otherwise
	 */
	public static boolean isIsogramEN(String str)
	{
		return isIsogram(str, ms_alphabetEN);
	}

	/**
	 * Checks if a string is a Turkish isogram
	 * @param str the string to check
	 * @return true if Turkish isogram, false otherwise
	 */
	public static boolean isIsogramTR(String str)
	{
		return isIsogram(str, ms_alphabetTR);
	}

	/**
	 * Checks if a string is a palindrome (reads same forwards and backwards)
	 * @param s the string to check
	 * @return true if palindrome, false otherwise
	 */
	public static boolean isPalindrome(String s)
	{
		String str = getLetters(s);
		
		if (str.isEmpty())
			return false;
		
		int first = 0;
		int last = str.length() - 1;
		
		while (first < last) {
			char chLeft = toLowerCase(str.charAt(first++));
			char chRight = toLowerCase(str.charAt(last--));
			
			if (chLeft != chRight)
				return false;
		}			
		
		return true;		
	}

	/**
	 * Checks if a string is a pangram (contains all letters of an alphabet)
	 * @param str the string to check
	 * @param alphabet reference alphabet to check against
	 * @return true if pangram, false otherwise
	 */
	public static boolean isPangram(String str, String alphabet)
	{
		str = str.toLowerCase();

		int len = alphabet.length();

		for (int i = 0; i < len; ++i) {
			char c = alphabet.charAt(i);

			if (!str.contains(c + ""))
				return false;
		}

		return true;
	}

	/**
	 * Checks if a string is a Turkish pangram
	 * @param str the string to check
	 * @return true if Turkish pangram, false otherwise
	 */
	public static boolean isPangramTR(String str)
	{
		return isPangram(str, ms_alphabetTR);
	}

	/**
	 * Checks if a string is an English pangram
	 * @param str the string to check
	 * @return true if English pangram, false otherwise
	 */
	public static boolean isPangramEN(String str)
	{
		return isPangram(str, ms_alphabetEN);
	}

	/**
	 * Pads a string on the left with spaces to reach specified length
	 * @param s the string to pad
	 * @param len target length
	 * @return padded string
	 */
	public static String padLeft(String s, int len)
	{
		return padLeft(s, len, ' ');
	}

	/**
	 * Pads a string on the left with specified character to reach length
	 * @param s the string to pad
	 * @param len target length
	 * @param ch padding character
	 * @return padded string
	 */
	public static String padLeft(String s, int len, char ch)
	{
		len -= s.length();
		
		if (len <= 0)
			return s;
		
		return (ch + "").repeat(len) + s;
	}

	/**
	 * Pads a string on the right with spaces to reach specified length
	 * @param s the string to pad
	 * @param len target length
	 * @return padded string
	 */
	public static String padRight(String s, int len)
	{
		return padRight(s, len, ' ');
	}

	/**
	 * Pads a string on the right with specified character to reach length
	 * @param s the string to pad
	 * @param len target length
	 * @param ch padding character
	 * @return padded string
	 */
	public static String padRight(String s, int len, char ch)
	{
		len -= s.length();
		
		if (len <= 0)
			return s;
		
		return s + (ch + "").repeat(len);
	}

	/**
	 * Reverses the characters in a string
	 * @param s the string to reverse
	 * @return reversed string
	 */
	public static String reverse(String s)
	{
		return new StringBuilder(s).reverse().toString();
	}

	/**
	 * Removes all characters from first string that appear in second string
	 * @param s1 the source string
	 * @param s2 characters to remove
	 * @return filtered string
	 */
	public static String squeeze(String s1, String s2)
	{
		StringBuilder sb = new StringBuilder();
		int length = s1.length();

		for (int i = 0; i < length; ++i) {
			char ch = s1.charAt(i);

			if (s2.indexOf(ch) == -1)
				sb.append(ch);
		}

		return sb.toString();
	}

	/**
	 * Splits a string using specified delimiters
	 * @param str the string to split
	 * @param delim delimiter characters
	 * @return array of split strings
	 */
	public static String [] split(String str, String delim)
	{
		return split(str, delim, StringSplitOptions.NONE);
	}

	/**
	 * Splits a string with options for handling empty entries
	 * @param str the string to split
	 * @param delim delimiter characters
	 * @param options split options (REMOVE_EMPTY_ENTRIES or NONE)
	 * @return array of split strings
	 */
	public static String [] split(String str, String delim, StringSplitOptions options)
	{
		StringBuilder re = new StringBuilder("[");
		String metas = "[]";

		for (int i = 0; i < delim.length(); ++i) {
			char c = delim.charAt(i);

			re.append(metas.contains(c + "") ? "\\" : "").append(c);
		}

		re.append("]");

		if (options == StringSplitOptions.REMOVE_EMPTY_ENTRIES)
			re.append("+");

		return str.split(re.toString());
	}

	/**
	 * Subtracts a specified value from each character's ASCII code in the string
	 * @param s the input string
	 * @param n the value to subtract from each character
	 * @return new string with modified characters
	 */
	public static String subtractAllCharsWith(String s, int n)
	{
		return addAllCharsWith(s, -n);
	}
}
