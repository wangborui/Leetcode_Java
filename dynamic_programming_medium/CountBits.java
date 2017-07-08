/*
Source: https://leetcode.com/problems/counting-bits/#/description
Date  : 07/08/2017
********************************************************************************
Given a non negative integer number num. 
For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class CountBits {
    /**
     * 
     * @param num
     * @return 
     * Given a number n, the number of 1 bit n has depends on how many 1 bits n / 2 has plus the last bit of n
     * For example, 
     * 
     *            n = 5 | 101
     *        n / 2 = 2 | 10
     *Last bit of 5 = 1
     * Therefore, 5 has 3 1 bits
     * 
     * Init:
     *      0 has 0 "1" bit
     * 
     * Sub-optimal function:
     *
     *     f[i] means the number of 1 bits number "i" has
     *     f[i] = f[i / 2] + i % 2 
     */
    static int[] countBits(int num) {
        int [] result = new int [num+1];
        for(int i = 1; i<= num;i++){
            result[i] = result[i>>1] + (i&1);
            }
            return result;
    }
    public static void main(String[] args) {
        for(int i :countBits(4) )
        System.out.println(i);
    }
}
