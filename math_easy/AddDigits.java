// Source : https://leetcode.com/problems/add-digits/
// Date   : 04/05/2017
/**
 * ********************************************************************************
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *
 * For example:
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 *
 * Follow up:
 * 	Could you do it without any loop/recursion in O(1) runtime?
 *
 *********************************************************************************
 */
package Leetcode_Java.math_easy;

/**
 *
 * @author Borui Wang
 */
public class AddDigits {
    // Let's observe the pattern
    //    1    1
    //    2    2
    //    ... ...
    //    8    8    
    //    9    9    
    //    10    1
    //    11    2
    //    12    3    
    //    ... ...
    //    17    8
    //    18    9
    //    19    1
    //    20    2
    //    ...  ...
    // It looks most of number just simply %9 is the answer, 
    // but there are some edge cases.
    //    9%9=0 but we need 9. 
    //    18%9=0 but we need 9
    // so we can find the solution is:
    //    1) num <=9, return num
    //    2) num > 9, reutrn num%9 if num%9>0
    //                return 9 if num%9 ==0

    public int addDigits(int num) {
        return 1 + (num - 1) % 9;
    }
}
