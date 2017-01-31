// Source : https://oj.leetcode.com/problems/distinct-subsequences/
// Date   : 01/31/2017
/**
 * ********************************************************************************
 *
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 *
 * A subsequence of a string is a new string which is formed from the original string
 * by deleting some (can be none) of the characters without disturbing the relative positions
 * of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 *
 * Return 3.
 *
 *
 *********************************************************************************
 */
package Leetcode_Java.dynamic_programming_hard;

/**
 *
 * @author Borui Wang
 */
public class DistinctSubsequence {

//=====================
// Dynamic Programming
//=====================
//
//  The idea as below:
//
//     we will build an array mem where m[i+1][j+1] means that S[0..j] contains T[0..i] that many times as distinct subsequences. Therefor the result will be mem[T.length()][S.length()].
//
//     A) Initialization for empty case:  m[0][j] = 1;
//
//     B) Calculation 
//
//        a) Target-len > Source-len cannot found any substring
//           i > j : m[i][j] = 0;   
//
//        b) if S[j] != T[i], take the value of T[i] => S[j-1] (e.g. ["ra" => "rabb"] =["ra" => "rab"] )
//           S[j] != T[i] :  m[i][j] = m[i][j-1]
//
//        c) if equal S[j] == T[i]. (e.g.  ["rab" => "rabb"] = ["rab" =>"rab"] + ["ra" => "rab"] ) 
//           S[j] == T[i] :  m[i][j] = m[i][j-1] + m[i-1][j-1]
//
//  1) Initialize a table as below   
//       ""  r  a  b  b  b  i  t
//    ""  1  1  1  1  1  1  1  1
//    r     
//    b   
//    t  
//
//  2) Calculation
//       ""  r  a  b  b  b  i  t
//    ""  1  1  1  1  1  1  1  1
//    r   0  1  1  1  1  1  1  1 
//    b   0  0  0  1  2  3  3  3
//    t   0  0  0  0  0  0  0  3
//
    public int numDistinct(String s, String t) {
        int[][] mem = new int[t.length() + 1][s.length() + 1];

        for (int j = 0; j <= s.length(); j++) {
            mem[0][j] = 1;
        }

        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (i > j) {
                    mem[i][j] = 0;
                    continue;
                }
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    mem[i][j] = mem[i][j - 1] + mem[i - 1][j - 1];
                } else {
                    mem[i][j] = mem[i][j - 1];
                }
            }
        }
        return mem[t.length()][s.length()];
    }
}
