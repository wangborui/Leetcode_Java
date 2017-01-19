// Source : https://oj.leetcode.com/problems/reverse-words-in-a-string-ii/
// Date   : 01/18/2017

/********************************************************************************** 
 * 
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters. 
 * 
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space. 
 * 
 * For example,
 *  Given s = "the sky is blue",
 *  return "blue is sky the". 
 * 
 * Could you do it in-place without allocating extra space? 
 * 
 * 
 **********************************************************************************/
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class ReverseWords2 {

    public void reverseWords(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }
        int start = 0;
        //1. reverse the whole string
        reverse(s, 0, s.length - 1);

        //2. reverse each word in string
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }

        //3. reverse last word in string, also if there is only one word corner case, it resolves it
        reverse(s, start, s.length - 1);

    }

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
