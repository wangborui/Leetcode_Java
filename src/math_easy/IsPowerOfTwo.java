
// Source : https://leetcode.com/problems/power-of-two/
// Date   : 01/13/2017

/********************************************************************************** 
 * 
 * Given an integer, write a function to determine if it is a power of two.
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and creating 
 * all test cases.
 *               
 *               
 *               
 **********************************************************************************/
package Leetcode_Java.math_easy;

/**
 *
 * @author Borui Wang
 */
public class IsPowerOfTwo {
    //确定数字n大于等于0，并且这个数字里面的二进制代码只有一位是1
    //if n is power of two, then in binary form it must be 00000010000000..., with only one 1
    //we notice: n - 1 in binary always be 1111111...
    //so, (n) & (n-1) always be zero 000000...
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
    // count the number fo bits 1, if it only has one, then return true
    public boolean isPowerOfTwoCount(int n) {
        if(n <= 0) {
            return false;
        }
        int oneBits = 0;
        
        while(n != 0) {
            int lastBit = n & 1;
            oneBits += lastBit;
            n >>= 1;
        }
        return oneBits == 1;
    }
    public static void main(String[] args) {
 
    }
}
