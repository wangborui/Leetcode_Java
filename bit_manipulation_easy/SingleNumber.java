
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

/**
 *
 * @author Borui Wang
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            res ^= nums[i];
        }
        return res;
    }
}
