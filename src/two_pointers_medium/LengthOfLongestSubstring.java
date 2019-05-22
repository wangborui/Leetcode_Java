//Source : https://leetcode.com/problems/longest-substring-without-repeating-characters/
//Date   : 02/15/2017
 

/********************************************************************************** 
* 
* Given a string, find the length of the longest substring without repeating characters. 
* For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
* which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
*               
**********************************************************************************/
package Leetcode_Java.two_pointers_medium;

/**
 *
 * @author Borui Wang
 */
public class LengthOfLongestSubstring {
/*
 * Idea:
 * 
 * Iterate the entire array to find the first repeating character, marked each visited character in an array as visited
 *
 * After finding the first repeating character, start from the begining of the string where index i is, mark each character as unvisited, until we mark the first instance of "repeating" character as unvisited
 *
 * Start incrementing pointer j again, until we find a "repeating" character
 */
    static int lengthOfLongestSubstring(String s) {
        boolean [] repeat = new boolean[256];
        int j = 0;
        int max = 0;
        int n = s.length();
        for(int i = 0; i < n; i++) {
            //find a repeating character
            while(j < n && !repeat[s.charAt(j)]) {
                repeat[s.charAt(j)] = true;
                max = Math.max(max, j - i + 1);
                j++;
            }
            //keep removing until jth char is not repeating
            repeat[s.charAt(i)] = false;
        }
        
        return max;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
