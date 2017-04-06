// Source : https://leetcode.com/problems/number-of-1-bits/
// Date   : 04/05/2017

/********************************************************************************** 
* 
* Write a function that takes an unsigned integer and returns the number of ’1' bits it has 
* (also known as the Hamming weight).
* 
* For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, 
* so the function should return 3.
* 
* Credits:Special thanks to @ts for adding this problem and creating all test cases.
*               
**********************************************************************************/
package Leetcode_Java.bit_manipulation_easy;

/**
 *
 * @author Borui Wang
 */
public class HammingWeight {
//    从一个整数的最后一位开始，看看那一位是否是1
//    如果一个数的最后一位是1的话，就计数一次
//    然后把这个数所有位向右移动一位
    public int hammingWeight(int n) {
        int count = 0;
        for(int i = 0; i < 32; i++){
            count = (n & 1) == 1?count+1:count;
            n = n>>1;
        }
        return count;
    }
    public static void main(String[] args){
        System.out.println(9&1);
    }
}
