// Source : https://oj.leetcode.com/problems/minimum-window-substring/
// Date   : 02/16/2017
/**
 * ********************************************************************************
 *
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 *
 * Minimum window is "BANC".
 *
 * Note:
 *
 * > If there is no such window in S that covers all characters in T,
 *   return the emtpy string "".
 *
 * > If there are multiple such windows, you are guaranteed that there
 *   will always be only one unique minimum window in S.
 *
 *
 *********************************************************************************
 */
package Leetcode_Java.two_pointers_hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class MinWindow {
    /*
     * Declare two "hash map" for ASCII chars
     *   sMap: represents the char found in string S
     *   tMap: stores the chars in string T
     */    
    private static Map<Character, Integer> sMap = new HashMap();
    private static Map<Character, Integer> tMap = new HashMap();
    /* if sMap[i] is existed in tMap*/ 
    static boolean isValid() {
        for (char c : tMap.keySet()) {
            int count = tMap.get(c);
            if (!sMap.containsKey(c) || sMap.get(c) < count) {
                return false;
            }
        }
        return true;
    }

    static String minWindow(String s, String t) {
    /* 
     *  Go through the T, and inital the sMap and tMap 
     *  Notes: a same char can be appeared multiple times.
     */
        for (char c : t.toCharArray()) {
            if (tMap.containsKey(c)) {
                tMap.put(c, tMap.get(c) + 1);
            } else {
                tMap.put(c, 1);
            }
        }
        int j = 0;
        int n = s.length();
        int minSize = Integer.MAX_VALUE;
        String minW = "";
        //two pointers, j stop on the first char that contains valid T
        //i stops on the first char that invalidates T
        for (int i = 0; i < n; i++) {
                 /* 
                 * Find the beginning of the window
                 * 1)the char at the `begin` is not in T
                 * 2)a same char appeared more than excepted.
                 */
            while (j < n && !isValid()) {
                char c = s.charAt(j);
                if (sMap.containsKey(c)) {
                    sMap.put(c, sMap.get(c) + 1);
                } else {
                    sMap.put(c, 1);
                }
                j++;
            }
            /* Calculate the minimized window size */
            if (minSize > j - i && isValid()) {
                minW = s.substring(i, j);
                minSize = j - i;
            }
            char prev = s.charAt(i);
            if(sMap.get(prev) == 1) {
                sMap.remove(prev);
            } else {
                sMap.put(s.charAt(i), sMap.get(prev) - 1);
            }
        }

        return minW;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("a","a") );
        System.out.println(minWindow("ADOBECODEBANC","ABC"));
    }
}
