/*
Source: https://leetcode.com/problems/single-number-ii/
Date  : 01/09/2017
/********************************************************************************** 
* 
* Given an array of integers, every element appears three times except for one. Find that single one.
* 
* Note:
* Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
* 
*               
**********************************************************************************/

package Leetcode_Java.bit_manipulation_medium;

/**
 *
 * @author Borui Wang
 */
public class SingleNumber2 {
    /**
     * 
     * @param nums[]
     * @return res
     * we know that integers in Java has 32 bits, and we create an integer array to hold all 32 bits, they are all zero initially.
     * 
     * we also know that all numbers appears 3 times except for one
     * 
     * for ith-bit in bits[], we count how many times 1 appears in that bit for each number in nums
     * 
     * after counting ith-bit of all numbers in nums array, we mod that bit  value by 3, if result is 0, then this bit is not used in our answer.
     * 
     * if result is 1, we use this in our final answer
     * 
     * This solution can be easy to extend to "every element appears k times except for one."
     */
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int res = 0;
        int []bits = new int[32];
        
        for(int i = 0; i < 32; i++) {
            for(int j = 0; j< nums.length; j++) {
                bits[i] += ((nums[j] >> i) & 1);
            }
            bits[i] %= 3;
            res |= bits[i] << i;
        }
        return res;
    }
    
        /*
     *   The following solution is popular solution on Internet, but it looks it's not easy to understand.
     *
     *   Actually, it just optimizes the above soultion.
     *
     *   Let's see how it improve the above.
     *
     *   We use three bitmasks, 
     *    1) `ones`     as a bitmask which represents the i-th bit had appeared once.
     *    2) `twos`     as a bitmask which represents the i-th bit had appeared twice.
     *    3) `threes`   as a bit mask which represents the i-th bit had appeared three times.
     *
     *    When the i-th bit had appeared for the third time, clear the i-th bit of both `ones` and `twos` to 0.
     *    The final answer will be the value of `ones`
     *
     */
    static int singleNumberOptimized(int[] nums) {
          int ones = 0, twos = 0, threes = 0;
          int n = nums.length;
        for (int i = 0; i < n; i++) {
            // `ones & A[i]` the result is the bitmask which the bits appeared twice
            twos |= ones & nums[i]; 
            // XOR means remove the bit which appeared twice int `ones` 
            ones ^= nums[i];
            // count the `three`
            threes = ones & twos;
            // clear the `ones` and `twos` if the i-th bit had appeared three times.
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }
    public static void main(String[] args) {
        System.out.println(singleNumberOptimized(new int[]{12,2,12,9,2,12,2}));
    }
}
