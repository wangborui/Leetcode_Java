// Source : https://oj.leetcode.com/problems/edit-distance/
// Date   : 041/06/2017

/********************************************************************************** 
* 
* Given two words word1 and word2, find the minimum number of steps required to 
* convert word1 to word2. (each operation is counted as 1 step.)
* 
* You have the following 3 operations permitted on a word:
* 
* a) Insert a character
* b) Delete a character
* c) Replace a character
* 
*               
**********************************************************************************/
package Leetcode_Java.dynamic_programming_hard;

/**
 *
 * @author Borui Wang
 */
public class EditDistance {
    /*
 *  Dynamic Programming
 *
 *    Definitaion
 *
 *        m[i][j] is minimal distance from word1[0..i] to word2[0..j]
 *
 *    So, 
 *
 *        1) if word1[i] == word2[j], then m[i][j] == m[i-1][j-1].
 *
 *        2) if word1[i] != word2[j], then we need to find which one below is minimal:
 *
 *             min( m[i-1][j-1], m[i-1][j],  m[i][j-1] )
 *            
 *             and +1 - current char need be changed.
 *
 *        Let's take a look  m[1][2] :  "a" => "ab" 
 *
 *               +---+  +---+                                         
 *        ''=> a | 1 |  | 2 | '' => ab                                
 *               +---+  +---+                                         
 *                                                                    
 *               +---+  +---+                                         
 *        a => a | 0 |  | 1 | a => ab                                 
 *               +---+  +---+                                         
 *                                                            
 *        To know the minimal distance `a => ab`, we can get it from one of the following cases:
 *
 *            1) delete the last char in word1,  minDistance( '' => ab ) + 1                                                             
 *            2) delete the last char in word2,  minDistance(  a => a ) + 1                                                             
 *            3) change the last char,           minDistance( '' => a ) + 1                                                             
 *                                                            
 *  
 *    For Example:
 *
 *        word1="abb", word2="abccb"
 *
 *        1) Initialize the DP matrix as below:
 *
 *               "" a b c c b
 *            "" 0  1 2 3 4 5
 *            a  1
 *            b  2
 *            b  3
 *
 *        2) Dynamic Programming
 *
 *               "" a b c c b
 *            ""  0 1 2 3 4 5
 *            a   1 0 1 2 3 4 
 *            b   2 1 0 1 2 3
 *            b   3 2 1 1 1 2
 *
 */
    public int minDistance(String word1, String word2) {
        
        //f[0][j] = j means when word1 = "", we need to change j times to get from word2 to word1
        //f[i][0] = i means when word2 = "", we need to change i times to get form word2 to word1
        int n = word1.length();
        int m = word2.length();
        int [][] f = new int[n + 1][m + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if(i == 0) {
                    f[i][j] = j;
                }
                else if(j == 0) {
                    f[i][j] = i;
                } else {
                    if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        f[i][j] = f[i - 1][j - 1];
                    } else {
                        int diag = f[i - 1][j - 1];
                        int left = f[i][j - 1];
                        int top = f[i - 1][j];
                        f[i][j] = Math.min(diag, Math.min(left, top)) + 1;
                    }
                }
            }
        }
        
        return f[n][m];
    }
}
