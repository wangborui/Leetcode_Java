// Source : https://leetcode.com/problems/reverse-string-ii/#/description
// Date   : 05/17/2017
/**
 * *************************************************************************************
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string.
 * If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
 * Example:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 * Restrictions:
 * The string consists of lower English letters only.
 * Length of the given string and k will in the range [1, 10000]
 **************************************************************************************
 */
package Leetcode_Java.string_easy;

/**
 *
 * @author Borui Wang
 */
public class ReverseStr2 {

    public String reverseStr(String s, int k) {
        if (s == null || s.length() < 2 || k <= 1) {
            return s;
        }

        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i += 2 * k) {
            reverse(c, i, Math.min(i + k - 1, c.length - 1));
        }
        return new String(c);
    }

    private void reverse(char[] c, int start, int end) {
        while (start < end) {
            char temp = c[start];
            c[start] = c[end];
            c[end] = temp;
            start++;
            end--;
        }
    }
}
