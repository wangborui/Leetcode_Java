// Source : https://leetcode.com/problems/first-unique-character-in-a-string/
// Date   : 04/04/2017

/*************************************************************************************** 
 *
 * Given a string, find the first non-repeating character in it and return it's index. 
 * If it doesn't exist, return -1.
 * 
 * Examples:
 * 
 * s = "leetcode"
 * return 0.
 * 
 * s = "loveleetcode",
 * return 2.
 * 
 * Note: You may assume the string contain only lowercase letters.
 ***************************************************************************************/
package Leetcode_Java.string_easy;

/**
 *
 * @author Borui Wang
 */
public class FirstUniqChar {
//    遍历一次字符串，把每个字母在字符串里出现的次数数一遍
//    再遍历一次字符串，看看当前字母是否只出现过一次，如果是的话返回当前下标
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
