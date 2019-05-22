/*
Source : https://leetcode.com/problems/single-number-iii/
Date   : 01/09/2017

**********************************************************************************
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
**********************************************************************************/
package Leetcode_Java.bit_manipulation_medium;

/**
 *
 * @author Borui Wang
 */
/**
 * We have many numbers that appear 2 times and only 2 numbers(a and b) that
 * appear once in the array, so if we XOR all the numbers together we would get
 * something like so 10101100 ^ ^ ^^
 *
 * All the 1's in the XOR result indicates a difference between a and b in their
 * relative bit position
 *
 * Any number in array AND LastOneBit can either be 0 or some other value
 *
 * We group all number AND LastOneBit = 0 to group 1 and XOR them(Similar idea
 * to single number 1)
 *
 * We group all number AND LastOneBit != 0 to group 2 and XOR them(similar idea
 * to single number 1)
 *
 * Thus, we have 2 groups of numbers with each group containing many numbers
 * that appear twice except one
 *
 * We return the number that appears once in both
 */
//________________________________________________________________________________
//        for example:
//            we have numbers in binary format 
//            2 * 1001
//            2 * 1111
//            a = 1110
//            b = 0110
//            after XOR all number, we get 1000, we can see that 1 indicates first bit where a and b differ
//                                         ^
//            then, we 1000 & all numbers in array
//                group[0] = {1001,1001,1111,1111,1110}
//                group[1] = {0110}
//            
//            then we XOR all numbers in each group
//                group[0] = {1110}
//                group[1] = {0110}
//________________________________________________________________________________
public class SingleNumber3 {
//    把所有数字亦或起来，这样的话所有出现两次的数字就会被抵消掉变成0
//    亦或起来的结果就是两个不同的数字的亦或，因为两个数字不同，所有他们的32位bit当中，必定有一位不一样，一个数的第i位为1，另一个数的第i位为0
//    我们可以通过亦或结果xor = xor & -xor 来找到两个不同的数字32位中从右到左（最低位到最高位）第一位不一样的（xxx00000[1]000000xxxx)，called diffBit，出现在第i位
//    找到这个之后我们可以把原数组的数字再分成两部分，一部分是num & diffBit = 0（数字的第i位不为1）, 另外一部分是num & diffBit ！= 0（数字的第i位为1）
//    把分好的两组数字再亦或起来就得到了我们想找的两个数字
    static int[] singleNumber(int[] nums) {
        //find XOR of all numbers in nums
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        //find the last bit(least significant bit) in xor with value 1
        int lastOneBit = xor & -xor;

        int[] res = new int[2];
        for (int num : nums) {

            if ((lastOneBit & num) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1, 2, 1, 3, 2, 5}));
    }
}
