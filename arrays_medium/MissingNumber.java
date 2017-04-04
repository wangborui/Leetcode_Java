// Source : https://leetcode.com/problems/missing-number/
// Date   : 04/03/2017

/*************************************************************************************** 
 *
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the 
 * one that is missing from the array.
 * 
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * 
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using 
 * only constant extra space complexity?
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and creating 
 * all test cases.
 *               
 ***************************************************************************************/
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class MissingNumber {
    //我们一共有0,1,2.。。n个数字
//    这些数字的总和便是0+1+2+...n
//    我们把一个变量sum初始化为n
//    这个数组nums里面一共有n - 1个数字，他们的下标分别是0,1,2，。。。n - 1
//    如果我们把这些下标加起来求总和再加上sum，我们就求得了0,1,2.。。n 数字的总和
//    我们再减去nums数组里面所有的数字，就求到了第一个missing number

    public MissingNumber() {
    }
    static int missingNumber(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += i - nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{0, 1, 3}));
    }
}
