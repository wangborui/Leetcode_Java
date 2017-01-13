
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
        int count = 0;
        for(;n > 0; n >>= 1) {
            if((n & 1) == 1) {
                count++;
            }
        }
        return count == 1;
    }
}
