/*
Source: https://leetcode.com/problems/palindrome-partitioning/
Date: 01/02/2016
********************************************************************************
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
********************************************************************************

Time O(2^n) Space O(n)
 */
package Leetcode_Java.dynamic_programming_hard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList();
        dfsHelper(res, new ArrayList(), s, 0);
        return res;
    }
    private void dfsHelper(List<List<String>> res, List<String> solution, String s, int start) {
        if(start == s.length()) {
            res.add(new ArrayList(solution));
            return;
        }
        /*
        for example, if we partition string "leet"
        1.) start = 0, 
        part = "l" which is a palindrome, dfs("eet"), solution["l"]
        part = "le", not palindrome, stop
        part = "lee, not palindrome, stop
        part = "leet, not palindrome, stop
        2.) start = 1,
        part = "e", which is palindrom, dfs("et"), solution ["l","e"] (recurse first)
        part = "ee", whhich is palindrome, dfs("t"), solution["l", "ee"]
        part = "eet", not palindrome, stop
        3.) start = 2,
        part = "e", is palindrome, dfs("t"), solution["l","e","e"] (recurse first)
        part = "et", not palindrome, stop
        4.) start = 3,
        part = "t", is palindrome, dfs(""), solution["l","e","e","t"] add to result
        part = "t", is palindrome, dfs(""), solution["l", "ee", "t"] add to result
        
        */
        for(int i = start; i < s.length(); i++) {
            String part = s.substring(start, i + 1);
            if(isPalindrome(part)) {
                solution.add(part);
                dfsHelper(res, solution, s, i + 1);
                solution.remove(solution.size() - 1);
            }
        }
    }
    //traditional palindrome check method time O(n)
    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        
        while(start < end) {
            if(s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
