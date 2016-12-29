/*
Source: https://leetcode.com/problems/word-break/
********************************************************************************
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
********************************************************************************

Solutions: using dynamic programming Time O(n^2) Space O(n)

Initialization:
f[0] = true if word dictionary contains this letter
Assume f[i] represents str[0:i] can be segmented into one or more words
f[i + 1] = true : wordDict contains substring(0, i+1)
         = true : 0 <= j < i, f[j] = true and wordDict contains substring(j + 1, i + 1)
         = false: none of the above
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class WordSegment {
    static boolean wordBreak(String s, Set<String> wordDict) {
        //isSegmentable[i] means substring from 0 to i can be segmented into one or more words
        //0 <= j < i if isSegmentable[j] = true && wordDict.contains(substring(j + 1, i + 1))  
        boolean [] isSegmentable = new boolean[s.length()];
        isSegmentable[0] = wordDict.contains(""+s.charAt(0));
        for(int i = 1; i < s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(wordDict.contains(s.substring(0, i + 1)) || 
                        isSegmentable[j] && wordDict.contains(""+s.substring(j + 1, i + 1))) {
                    isSegmentable[i] = true;
                    break;
                }
            }
        }
        return isSegmentable[s.length() - 1];
    }
    public static void main(String [] args){
        Set<String> wordDict = new HashSet();
//        wordDict.add("a");
//        wordBreak("a",wordDict);
        wordDict.add("leet");
        wordDict.add("code");
        System.out.println(wordBreak("leetcode",wordDict));
    }
}
