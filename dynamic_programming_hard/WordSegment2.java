/*

********************************************************************************
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class WordSegment2 {

    //regular DFS exceeds time limits
    //because many element are DFSed many times over
//    for example
//              s = "catsanddog"
//              d = {"cat", "cats", "and", "sand", "dog"}
//          words = {"cat sand dog", "cats and dog"}
//              Note the word "dog" is DFSed twice in this case, but if we use a hashmap to store substrings already visited
//              We can retrive all valid words formed by that substring and just return 
//     iterate each word in dictionary to find out if current string s starts with the dictionary word
//     if current word s starts with one dictionary word, use dfs to recursively find all other valid words starting at substring(dictWord.length), why?
//     because we can exclude this dictionary word and search the rest of the possible substrings
//     Additionally, like example above, we need to use hashmap to keep track of all visited substring and not DFS visit them again
    static List<String> wordBreak(String s, List<String> wordDict) {

        return dfsHelper(wordDict, s, new HashMap());
    }
    //String s means the substring we are currently DFSing
    // DFS function returns an array including all substrings derived from s.
    static List<String> dfsHelper(List<String> wordDict, String s, HashMap<String, List<String>> map) {
        if(map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList();
        //visited all substrings in origianl string s
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        
        for(String dict : wordDict) {
            if(s.startsWith(dict)) {
                //all valid lines formed by substring excluding "dict" word
                List<String> lines = dfsHelper(wordDict, s.substring(dict.length()), map);
                for(String line : lines) {
                    res.add(dict + (line.isEmpty() ? "" : " ") + line);
                }
            }
        }
        map.put(s, res);
        return res;
    }

 
 

    public static void main(String[] args) {
        String[] dict = {"cat", "cats", "and", "sand", "dog", "dogs","so","o"};
        System.out.println(wordBreak("catsanddogso", Arrays.asList(dict)));
    }
}
