
// Source : https://leetcode.com/problems/single-number/
// Date   : 01/08/2017

/********************************************************************************** 
* 
* Given an array of integers, every element appears twice except for one. Find that single one.
* 
* Note:
* Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
* 
*               
**********************************************************************************/
//XOR key note
//1.) No carry addtion of binary numbers
//      1010
//      0110
//      ----
//      1100
//
//2.) Formulas 
//"a ^ b = c => a ^ c = b, a ^ b = c
//a ^ a  = 0
//a ^ 0 = a
//a ^ b ^ c = a ^ (b ^ c)"
// This is classical interview question
// As we know, the same number XOR together will be 0,
// So, XOR all of numbers, the result is the number which only appears once. 
package Leetcode_Java.bit_manipulation_easy;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
//把所有的数字亦或起来，最后剩下的就是答案了
public class SingleNumber {
    static int singleNumber(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            res ^= nums[i];
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] test = {1,2,3,3,2};
        singleNumber(test);
    }
}
