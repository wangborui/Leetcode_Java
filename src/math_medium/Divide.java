// Source : https://oj.leetcode.com/problems/divide-two-integers/
// Date   : 02/24/2017
/**
 * ********************************************************************************
 *
 * Divide two integers without using multiplication, division and mod operator.
 *
 * If it is overflow, return MAX_INT.
 *
 *********************************************************************************
 */
package Leetcode_Java.math_medium;

/**
 *
 * @author Borui Wang
 */
public class Divide {

    public int divide(int dividend, int divisor) {
        // Arrr...gh! Divide by zero
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        // Corner case: 
        // dividend = -2147483648 and divisor = -1, answer should be 2147483647, otherwise it will overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // Work only with positive representations for now, we'll put the sign at the end.
        // NOTE: 
        // Casting to long is necessary otherwise Math.abs(Integer.MIN_VALUE) 
        // returns Integer.MIN_VALUE since 32-bit signed integers overflow
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        /*
         * KEY INSIGHT:  
         * Every integer lDividend can be represented in terms of lDivisor and powers of 2 as follows,
         * lDividend = c + lDivisor * Math.pow(2, n) + lDivisor * Math.pow(2, m) + .... +
         *               + ... + lDivisor * Math.pow(2, 0)
         * where, 
         * c is the remainder and c < lDivisor, 
         * n is the highest power of 2 such that lDivisor * Math.pow(2,n) <= lDividend,
         * m is the second highest power of 2 such that lDivisor * Math.pow(2,m) <= lDividend and so on.
         * 
         * Since every integer has such a binary representation, we can successively reduce lDividend by amount 
         * equal to (lDivisor * Math.pow(2, highestPowerOfTwo)) until lDividend becomes c. 
         * 
         * This way our search for the quotient can finish in O(log N) time.
         * 
         * EXAMPLE 1:
         * lDividend = 29, lDivisor = 3
         * Iteration 1: 
         * Highest power of two whose product with 3 is less than 29 is 8. quotient = 8, lDividend = 29 - (8 * 3) = 5
         * Iteration 2:
         * Highest power of two whose product with 3 is less than 5 is 1. quotient = 8 + 1 = 9, lDividend = 5 - (1 * 3) = 2
         * Stop. (lDividend < lDivisor) i.e. (2 < 3)
         * Return 9.
         * 
         * EXAMPLE 2:
         * lDividend = 15, lDivisor = 7
         * Iteration 1:
         * Highest power of two whose product with 7 is less than 15 is 2. quotient = 2, lDividend = 15 - (2 * 7) = 1
         * Stop. (lDividend < lDivisor) i.e. (1 < 7)
         * Return 2.
         */
        int quotient = 0;
        int powerOfTwo = 0;
        // NOTES ON SOME BIT HACKS: 
        // 1. x << n is equivalent to x * Math.pow(2, n). (<< is Leftshift Operator)
        // 2. 1 << x is equivalent to Math.pow(2, x). (<< is Leftshift Operator)
        // 3. x and y have opposite signs if (x ^ y) < 0. (^ is Bitwise XOR)
        while (ldividend >= ldivisor) {
            powerOfTwo = 0;
            // Keep finding the highest number such that (lDivisor * Math.pow(2, highestPowerOfTwo)) <= lDividend
            while (ldividend >= (ldivisor << powerOfTwo)) {
                powerOfTwo++;
            }
            // Latest highestPowerOfTwo took lDivisor higher than lDividend, so contend with (highestPowerOfTwo - 1)
            powerOfTwo--;
            // Add the highestPowerOfTwo to our result.
            quotient += 1 << powerOfTwo;
            // Reduce lDividend by amount (lDivisor * Math.pow(2, highestPowerOfTwo))
            ldividend -= ldivisor << powerOfTwo;
        }
        // If dividend and divisor had opposite signs, then sign of the result is negative.
        // Otherwise, sign of the result is positive.
        return (dividend ^ divisor) < 0 ? -quotient : quotient;
    }
}
