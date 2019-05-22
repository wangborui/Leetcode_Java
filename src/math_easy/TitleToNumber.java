// Source : https://oj.leetcode.com/problems/excel-sheet-column-number/
// Date   : 04/03/2017

/********************************************************************************** 
 * 
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * 
 * For example:
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28 
 * 
 * Credits:Special thanks to @ts for adding this problem and creating all test cases.
 *               
 **********************************************************************************/
package Leetcode_Java.math_easy;

/**
 *
 * @author Borui Wang
 */
public class TitleToNumber {
//从字符串的最后一位开始，把那一位的字母减去‘A'再加一，乘以26^0
//再计算倒数第二位，把那一位的字母减去’A'再加一，乘以26^1
//直到字符串的从左到右第一位
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        int pow = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            sum += (int) Math.pow(26, pow++) * (c - 'A' + 1);
        }
        return sum;
    }
}
