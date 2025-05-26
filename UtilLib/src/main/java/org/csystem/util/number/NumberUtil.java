/**
 * {@code NumberUtil} is a utility class that provides a set of static methods for performing various numerical operations.
 * <p>
 * These operations include digit manipulation, prime number generation, factorials, Fibonacci numbers,
 * palindrome checking, Armstrong number validation, random number generation with unique digits, and more.
 * </p>
 * <p>
 * All methods are static, and the class is not intended to be instantiated.
 * </p>
 * <h2>Features</h2>
 * <ul>
 *     <li>Factorial calculations for {@code int} and {@code long} values</li>
 *     <li>Digit extraction and manipulation for numbers</li>
 *     <li>Fibonacci number generation (individual and sequences)</li>
 *     <li>Prime number checks and generators</li>
 *     <li>Palindrome detection in strings</li>
 *     <li>Random number generation with unique digits</li>
 *     <li>Armstrong number validation</li>
 *     <li>Even/odd number detection</li>
 * </ul>
 *
 * <h2>Usage</h2>
 * <pre>{@code
 * int[] digits = NumberUtil.getDigits(12345); // [1, 2, 3, 4, 5]
 * boolean isPrime = NumberUtil.isPrime(13);  // true
 * long factorial = NumberUtil.factorial(20); // 2432902008176640000
 * }</pre>
 *
 * @author CSD Java Group
 * @version 01.11.2021
 * @see java.math.BigInteger
 * @see java.util.Random
 */package org.csystem.util.number;

import org.csystem.util.string.StringUtil;

import java.math.BigInteger;
import java.util.Random;

import static java.lang.Math.*;

public final class NumberUtil {
    private static final String [] MS_ONES_TR;
    private static final String [] MS_TENS_TR;
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger FIVE = BigInteger.valueOf(5);
    private static final BigInteger SEVEN = BigInteger.valueOf(7);
    private static final BigInteger ELEVEN = BigInteger.valueOf(11);

    static {
        MS_ONES_TR = new String[] {"", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz"};
        MS_TENS_TR = new String[] {"", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan"};
    }

    private static int [] getDigits(int n, long val)
    {
        val = Math.abs(val);
        int len = val == 0 ? 1 : (int)Math.log10(val) / n + 1;
        int [] digits = new int[len];
        int pow = (int)Math.pow(10, n);

        for (int i = len - 1; i >= 0; digits[i] = (int)(val % pow), val /= pow, --i)
            ;

        return digits;
    }

    private static int getPowSum(int val, int n)
    {
        int sum = 0;

        while (val != 0) {
            sum += pow(val % 10, n);
            val /= 10;
        }

        return sum;
    }

    private static String numToStr3DigitsTR(int val)
    {
        if (val == 0)
            return "sıfır";

        String str = val < 0 ? "eksi" : "";
        val = Math.abs(val);

        int a = val / 100;
        int b = val / 10 % 10;
        int c = val % 10;

        if (a != 0) {
            if (a != 1)
                str += MS_ONES_TR[a];

            str += "yüz";
        }
        if (b != 0)
            str += MS_TENS_TR[b];
        if (c != 0)
            str += MS_ONES_TR[c];

        return str;
    }

    private NumberUtil() {}

    /**
     * Returns the factorial of the specified non-negative integer.
     *
     * @param n the number to calculate the factorial for
     * @return the factorial of {@code n}
     */
    public static int factorial(int n)
    {
        int result = 1;

        for (int i = 2; i <= n; ++i)
            result *= i;

        return result;
    }

    /**
     * Returns the factorial of the specified non-negative {@code long} value as a {@code BigInteger}.
     *
     * @param n the number to calculate the factorial of
     * @return the factorial of {@code n} as a {@code BigInteger}
     */
    public static BigInteger factorial(long n)
    {
        BigInteger result = BigInteger.ONE;

        for (long i = 2L; i <= n; ++i)
            result = result.multiply(BigInteger.valueOf(i));

        return result;
    }

    /**
     * Returns the digits of the specified {@code int} value as an array of integers.
     * The digits are ordered from most significant to least significant.
     *
     * @param val the integer value to extract digits from
     * @return an array containing the digits of {@code val}
     */
    public static int [] getDigits(int val)
    {
        return getDigits((long)val);
    }

   /**
    * Returns the digits of the specified {@code long} value as an array of integers.
    * The digits are ordered from most significant to least significant.
    *
    * @param val the long value to extract digits from
    * @return an array containing the digits of {@code val}
    */
   public static int [] getDigits(long val)
   {
       return getDigits(1, val);
   }

   /**
    * Returns the digits of the specified {@code long} value grouped in pairs as an array of integers.
    * The digits are ordered from most significant to least significant.
    * If the number of digits is odd, the most significant digit will be a single digit.
    *
    * @param val the long value to extract digits from, grouped in pairs
    * @return an array containing the digits of {@code val} grouped in pairs
    */
   public static int [] getDigitsInTwo(long val)
   {
       return getDigits(2, val);
   }

    /**
     * Returns the digits of the specified {@code long} value grouped in threes as an array of integers.
     * The digits are ordered from most significant to least significant.
     * If the number of digits is not a multiple of three, the most significant group will contain the remaining digits.
     *
     * @param val the long value to extract digits from, grouped in threes
     * @return an array containing the digits of {@code val} grouped in threes
     */
    public static int [] getDigitsInThree(long val)
    {
        return getDigits(3, val);
    }

    /**
     * Returns the number of digits in the specified {@code int} value.
     * Delegates to {@link #getDigitsCount(long)}.
     *
     * @param val the int value whose digits are to be counted
     * @return the number of digits in {@code val}
     */

    public static int getDigitsCount(int val)
    {
        return getDigitsCount((long)val);
    }

    /**
     * Returns the number of digits in the specified {@code long} value.
     * If the value is zero, the result is {@code 1}.
     *
     * @param val the long value whose digits are to be counted
     * @return the number of digits in {@code val}
     */

    public static int getDigitsCount(long val)
    {
        return val == 0 ? 1 : (int)Math.log10(Math.abs(val)) + 1;
    }

    /**
     * Returns the sum of the digits in the specified {@code int} value.
     * The sum is calculated by iterating through each digit of the number.
     * The result is always a non-negative value.
     *
     * @param val the integer value to sum digits of
     * @return the sum of the digits in {@code val}
     */
    public static int getDigitsSum(int val)
    {
        int sum = 0;

        while (val != 0) {
            sum += val % 10;
            val /= 10;
        }

        return abs(sum);
    }

   /**
    * Returns the n-th Fibonacci number.
    * The Fibonacci sequence is defined as follows:
    * F(1) = 0, F(2) = 1, and F(n) = F(n-1) + F(n-2) for n > 2.
    * If the input is less than or equal to 0, the method returns -1.
    *
    * @param n the position of the Fibonacci number to retrieve (1-based index)
    * @return the n-th Fibonacci number, or -1 if {@code n} is less than or equal to 0
    */
   public static int getFibonacciNumber(int n)
   {
       if (n <= 0)
           return -1;

       if (n <= 2)
           return  n - 1;

       int prev1 = 0, prev2 = 1, val = 0;

       for (int i = 2; i < n; ++i) {
           val = prev1 + prev2;
           prev1 = prev2;
           prev2 = val;
       }

       return val;
   }

   /**
    * Returns the first {@code n} Fibonacci numbers as an array.
    * The Fibonacci sequence is defined as follows:
    * F(1) = 0, F(2) = 1, and F(n) = F(n-1) + F(n-2) for n > 2.
    * If {@code n} is less than or equal to 0, an empty array is returned.
    *
    * @param n the number of Fibonacci numbers to generate
    * @return an array containing the first {@code n} Fibonacci numbers
    */
   public static int [] getFibonacciNumbers(int n)
   {
       int [] numbers = new int[n];

       if (n > 1)
           numbers[1] = 1;

       int prev1 = 0, prev2 = 1;

       for (int i = 2; i < n; ++i) {
           numbers[i] = prev1 + prev2;
           prev1 = prev2;
           prev2 = numbers[i];
       }

       return numbers;
   }

   /**
    * Returns the next Fibonacci number greater than the specified value.
    * The Fibonacci sequence is defined as follows:
    * F(1) = 0, F(2) = 1, and F(n) = F(n-1) + F(n-2) for n > 2.
    *
    * @param val the value to find the next Fibonacci number for
    * @return the next Fibonacci number greater than {@code val}
    */
   public static int getNextFibonacciNumber(int val)
   {
       int prev1 = 0, prev2 = 1, result = 0;

       for (;;) {
           result = prev1 + prev2;
           if (result > val)
               return result;

           prev1 = prev2;
           prev2 = result;
       }
   }

   /**
    * Returns the n-th prime number.
    * The method iterates through integers starting from 2 and checks for primality.
    * If {@code n} is less than or equal to 0, the method returns -1.
    *
    * @param n the position of the prime number to retrieve (1-based index)
    * @return the n-th prime number, or -1 if {@code n} is less than or equal to 0
    */
   public static int getPrime(int n)
   {
       if (n <= 0)
           return -1;

       int count = 0, val = 0;

       for (int i = 2; count < n; ++i)
           if (isPrime(i)) {
               ++count;
               val = i;
           }

       return val;
   }

    /**
     * Returns the first {@code n} prime numbers as an array.
     * The method iterates through integers starting from 2 and checks for primality.
     *
     * @param n the number of prime numbers to generate
     * @return an array containing the first {@code n} prime numbers
     */
    public static int[] getPrimes(int n)
    {
        int[] primes = new int[n];
        int count = 0;

        for (int i = 2; count < n; ++i)
            if (isPrime(i))
                primes[count++] = i;

        return primes;
    }
    /**
     * Generates a random number with unique digits of the specified length.
     * Delegates to {@link #getRandomNumberUniqueDigits(Random, int)}.
     *
     * @param n the number of unique digits in the generated number
     * @return a random number with unique digits of length {@code n}
     */
    public static long getRandomNumberUniqueDigits(int n)
    {
        return getRandomNumberUniqueDigits(new Random(), n);
    }
    /**
     * Generates a random number with unique digits of the specified length.
     * The number is generated by randomly selecting digits without repetition. The first digit will be between 1 and 9,
     * and the subsequent digits will be selected from 0 to 9 without repeating any of the previously selected digits.
     *
     * @param r the {@link Random} object used to generate random values
     * @param n the number of digits in the generated number. The digits will be unique.
     * @return a random number with unique digits of length {@code n}
     */
    public static long getRandomNumberUniqueDigits(Random r, int n)
    {
        //...
        boolean [] flags = new boolean[10];
        long result = 0;
        int val;

        val = r.nextInt(9) + 1;
        result +=  val * (long)Math.pow(10, n - 1);
        flags[val] = true;

        for (int i = 1; i < n; ++i) {
            for (;;) {
                val = r.nextInt(10);
                if (!flags[val])
                    break;
            }
            result += val * (long)Math.pow(10, n - 1 - i);
            flags[val] = true;
        }

        return result;
    }

   /**
    * Reverses the digits of the specified integer value.
    * For example, if the input is 123, the output will be 321.
    *
    * @param val the integer value to reverse
    * @return the reversed integer value
    */
   public static int getReverse(int val)
   {
       int rev = 0;

       while (val != 0) {
           rev = rev * 10 + val % 10;
           val /= 10;
       }

       return rev;
   }
    /**
     * Checks if the specified integer value is an Armstrong number.
     * An Armstrong number is a number that is equal to the sum of its own digits raised to the power of the number of digits.
     *
     * @param val the integer value to check
     * @return {@code true} if {@code val} is an Armstrong number, {@code false} otherwise
     */
    public static boolean isArmstrong(int val)
    {
        if (val < 0)
            return false;

        if (val < 10)
            return true;

        int n = getDigitsCount(val);

        return getPowSum(val, n) == val;
    }

   /**
    * Checks if the specified integer value is even.
    *
    * @param val the integer value to check
    * @return {@code true} if {@code val} is even, {@code false} otherwise
    */
   public static boolean isEven(int val)
   {
       return val % 2 == 0;
   }
    /**
     * Checks if the specified integer value is odd.
     *
     * @param val the integer value to check
     * @return {@code true} if {@code val} is odd, {@code false} otherwise
     */
    public static boolean isOdd(int val)
    {
        return !isEven(val);
    }

    public static boolean isPalindrome(String s)
    {
        String str = StringUtil.getLetters(s);

        if (str.isEmpty())
            return false;

        int first = 0;
        int last = str.length() - 1;

        while (first < last) {
            char chLeft = Character.toLowerCase(str.charAt(first++));
            char chRight = Character.toLowerCase(str.charAt(last--));

            if (chLeft != chRight)
                return false;
        }

        return true;

    }


    /**
     * Checks if the specified integer value is a prime number.
     * Delegates to {@link #isPrime(long)} for the actual implementation.
     *
     * @param val the integer value to check
     * @return {@code true} if {@code val} is a prime number, {@code false} otherwise
     */
    public static boolean isPrime(int val)
    {
        return isPrime((long)val);
    }

    /**
     * Checks if the specified {@code long} value is a prime number.
     * A prime number is a number greater than 1 that has no divisors other than 1 and itself.
     * The method uses trial division up to the square root of the number for efficiency.
     *
     * @param val the {@code long} value to check
     * @return {@code true} if {@code val} is a prime number, {@code false} otherwise
     */
    public static boolean isPrime(long val)
    {
        if (val <= 1L)
            return false;

        if (val % 2L == 0)
            return val == 2L;

        if (val % 3L == 0)
            return val == 3L;

        if (val % 5L == 0)
            return val == 5L;

        if (val % 7L == 0)
            return val == 7L;

        long sqrtVal = (long)sqrt(val);

        for (long i = 11L; i <= sqrtVal; i += 2L)
            if (val % i == 0)
                return false;

        return true;
    }

    /**
     * Checks if the specified {@link BigInteger} value is a prime number.
     * A prime number is a number greater than 1 that has no divisors other than 1 and itself.
     * The method uses trial division up to the square root of the number for efficiency.
     *
     * @param val the {@link BigInteger} value to check
     * @return {@code true} if {@code val} is a prime number, {@code false} otherwise
     */
    public static boolean isPrime(BigInteger val)
    {
        if (val.compareTo(BigInteger.ONE) <= 0)
            return false;

        if (val.remainder(BigInteger.TWO).equals(BigInteger.ZERO))
            return val.equals(BigInteger.TWO);

        if (val.remainder(THREE).equals(BigInteger.ZERO))
            return val.equals(THREE);

        if (val.remainder(FIVE).equals(BigInteger.ZERO))
            return val.equals(FIVE);

        if (val.remainder(SEVEN).equals(BigInteger.ZERO))
            return val.equals(SEVEN);

        BigInteger sqrt = val.sqrt();

        for (BigInteger i = ELEVEN; i.compareTo(sqrt) <= 0; i = i.add(BigInteger.TWO))
            if (val.remainder(i).equals(BigInteger.ZERO))
                return false;

        return true;
    }

    /**
     * Checks if the specified integer value is not a prime number.
     * A number is not prime if it is less than or equal to 1, or if it has divisors other than 1 and itself.
     *
     * @param val the integer value to check
     * @return {@code true} if {@code val} is not a prime number, {@code false} otherwise
     */
    public static boolean isNotPrime(int val)
    {
        return !isPrime(val);
    }

   /**
    * Checks if the specified {@code long} value is not a prime number.
    * A number is not prime if it is less than or equal to 1, or if it has divisors other than 1 and itself.
    *
    * @param val the {@code long} value to check
    * @return {@code true} if {@code val} is not a prime number, {@code false} otherwise
    */
   public static boolean isNotPrime(long val)
   {
       return !isPrime(val);
   }

    /**
     * Checks if the specified {@link BigInteger} value is not a prime number.
     * A number is not prime if it is less than or equal to 1, or if it has divisors other than 1 and itself.
     *
     * @param val the {@link BigInteger} value to check
     * @return {@code true} if {@code val} is not a prime number, {@code false} otherwise
     */
    public static boolean isNotPrime(BigInteger val)
    {
        return !isPrime(val);
    }
    /**
     * Returns the minimum of three integer values.
     *
     * @param a the first integer value
     * @param b the second integer value
     * @param c the third integer value
     * @return the minimum of {@code a}, {@code b}, and {@code c}
     */
    public static int min(int a, int b, int c)
    {
        return Math.min(Math.min(a, b), c);
    }

    /**
     * Returns the maximum of three integer values.
     *
     * @param a the first integer value
     * @param b the second integer value
     * @param c the third integer value
     * @return the maximum of {@code a}, {@code b}, and {@code c}
     */
    public static int max(int a, int b, int c)
    {
        return Math.max(Math.max(a, b), c);
    }

    /**
     * Converts a long value to a string representation in Turkish.
     * The conversion is done by calling {@link #numToStr3DigitsTR(int)}.
     *
     * @param val the long value to convert
     * @return the string representation of {@code val} in Turkish
     */
    public static String numToStr(long val)
    {
        //...
        return numToStr3DigitsTR((int)val);

    }
}


