// Source : https://oj.leetcode.com/problems/excel-sheet-column-title/
// Date   : 04/04/2017

/********************************************************************************** 
 * 
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * 
 * For example:
 * 
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB 
 * 
 * Credits:Special thanks to @ifanchu for adding this problem and creating all test cases.
 *               
 **********************************************************************************/
package Leetcode_Java.math_easy;

/**
 *
 * @author Borui Wang
 */
public class ConvertToTitle {
    static String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            n--;
            char c = (char) ('A' + n % 26);
            sb.insert(0, "" + c);
            n /= 26;
        }
        return sb.toString();
    }
    public static void main(String[] args){
        //convertToTitle(1);
        System.out.println();
    }
}
