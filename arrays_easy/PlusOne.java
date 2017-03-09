// Source : https://oj.leetcode.com/problems/plus-one/
// Date   : 03/09/2017

/********************************************************************************** 
* 
* Given a non-negative number represented as an array of digits, plus one to the number.
* 
* The digits are stored such that the most significant digit is at the head of the list.
*               
**********************************************************************************/
package Leetcode_Java.arrays_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class PlusOne {
     public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0) {
            return new int[0];
        }
        
        int carry = 1;
        int n = digits.length;
        
        for(int i = n - 1; i >= 0 && carry == 1; i--) {
            int digit = digits[i];
            if(digit + carry == 10) {
                digits[i] = 0;
            } else {
                digits[i] += carry;
                return digits;
            }
        }
        
        if(carry == 1) {
            int[] res = new int[n + 1];
            res[0] = 1;
            return res;
        } else {
            return digits;
        }
    }
    public static void main(String [] args){
        int [] dig = {0};
         
        StdOut.println(Arrays.toString(new PlusOne().plusOne(dig)));
    }
}
