//Source : 
//Date   :

/*******************************************************************************
* Given a string, find the length of the longest substring T that contains at most k distinct characters.
* 
* For example, Given s = “eceba” and k = 2,
* 
* T is "ece" which its length is 3.
*******************************************************************************/
package Leetcode_Java.two_pointers_hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class LengthOfLongestSubstringKDistinct {
//    idea: Time O(n), space O(n):
//    we need to iterate then entire array twice to be able to find long substring with at most k distinct characters, why?
//    
//    we need to start from i = 0 : n, j = i : n, we keep moving j until we find a substring with more than k distinct character, then we move i to right one, start scanning j = i : n again.
//    
//    because we need to keep track of k distinct characters, we can therefore use hash map to keep track of distinct characters we have so far
//            
//    We also notices that we did lots of repetitive work above, for example
//            Index: 0    1    2    3    4
//              s = [e    c    e    b    a],  k = 2
//         
//            1.) i = 0, j = 0, substring = "e",     distinct = 1,  maxLength = 1
//            2.) i = 0, j = 1, substring = "ec",    distinct = 2,  maxLength = 2
//            3.) i = 0, j = 2, substring = "ece",   distinct = 2,  maxLength = 3 
//            4.) i = 0, j = 3, substring = "eceb",  distinct = 3,  maxLength = 3, distinct > k, break
//            5.) i = 1, j = 1, substring = "c",     distinct = 1,  maxLength = 3, 
//            6.) i = 1, j = 2, substring = "ce",    distinct = 2,  maxLength = 3,
//            7.) i = 1, j = 3, substring = "ceb",   distinct = 3,  maxLength = 3, distinct > k, break
//            
//    Note that we do not need to check step 5, 6, and 7, because even if there are substrings with distinct character, their length could not exceed max length we found from step 1,2,3,4
//    After step 4, we just need to keep j where it is, and start looking i = 1, j = 3, and keep reducing i until we find a substring(i, j+1) that has k distinct character
    
    
//    Steps:
    
//    we need to use 2 pointers, and hashmap. initialize i and j, both starting at 0, and hashmap to store number of occurence of characters
//    
//    we keep moving pointer j until we have a substring with more than k distinct characters, calculate max size at this point j - 1
//    
//    we keep moving pointer i and delete ith character until we have a substring with k distinct characters.
//    
//    we then move pointer j again, repeat the process until we finish

    public LengthOfLongestSubstringKDistinct() {
    }
    static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k <= 0 || s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap();
        int j = 0;
        int max = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            while (j < n && map.size() <= k) {
                char cur = s.charAt(j);
                if (map.size() == k && !map.containsKey(cur)) {
                    break;
                }
                if (map.containsKey(cur)) {
                    map.put(cur, map.get(cur) + 1);
                } else {
                    map.put(cur, 1);
                }
                j++;
            }

            max = Math.max(max, j - i);
            char prev = s.charAt(i);
            if (map.get(prev) == 1) {
                map.remove(prev);
            } else {
                map.put(prev, map.get(prev) - 1);
            }
        }

        return max;
    }
    //solution without hashmap
     static int lengthOfLongestSubstringKDistinct2(String s, int k) {
        if(k <= 0 || s == null || s.length() == 0) {
            return 0;
        }
        int []counts = new int[256];
        int j = 0;
        int max = 0;
        int n = s.length();
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            while (j < n && count <= k) {
                char cur = s.charAt(j);
                if(count == k && counts[cur] == 0) {
                    break;
                }
                if(counts[cur]++ == 0) {
                    count++;
                }
                j++;
            }
            
            max = Math.max(max, j - i);
            char prev = s.charAt(i);
            if (--counts[prev] == 0) {
                count--;
            }
        }
        
        return max;
     }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct2("aac",2));
        System.out.println(lengthOfLongestSubstringKDistinct2("e",1));
        System.out.println(lengthOfLongestSubstringKDistinct2("eceba",2)); 
        System.out.println(lengthOfLongestSubstringKDistinct2("ababcbcbaaabbdef", 2));
    }
}
