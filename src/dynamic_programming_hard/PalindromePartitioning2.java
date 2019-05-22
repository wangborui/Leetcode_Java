/*
Initialization:
mc[0] = 0, there are no cuts needed when 1 character presents

   For example, let s = "leet".
   
   f(0) = 0; // minimum cut of str[0:0]="l", which is a palindrome, so not cut is needed.
   f(1) = 1; // str[0:1]="le" How to get 1? 
                    f(1) might be:  (1)   f(0)+1=1, the minimum cut before plus the current char.
                                    (2)   0, if str[0:1] is a palindrome  (here "le" is not )
   f(2) = 1; // str[0:2] = "lee" How to get 2?
                   f(2) might be:   (1)  f(1) + 1=2
                                    (2)  0, if str[0:2] is a palindrome (here "lee" is not)
                                    (3)  f(0) + 1,  if str[1:2] is a palindrome, yes! 
   f(3) = 2; // str[0:3] = "leet" How to get 2?
                   f(3) might be:   (1)  f(2) + 1=2
                                    (2)  0, if str[0:3] is a palindrome (here "leet" is not)
                                    (3)  f(0) + 1,  if str[1:3] is a palindrome (here "eet" is not)
                                    (4)  f(1) + 1,  if str[2:e] is a palindrome (here "et" is not)
  OK, output f(3) =2 as the result.

Cut:
mc[i] means minimum number of cuts from 0 to i needed to partition palindromes, thus the result for us would be mc[s.length() - 1];
mc[i + 1] = 0               : 0 to i + 1 is palindrome
            min(mc[j] + 1)  : 0 <= j <= i + 1, 0 to j is palindrome and j + 1 to i + 1 are also palindromes
So, the optimal function is:
 
f(i) = min [  f(j)+1,  j=0..i-1   and str[j:i] is palindrome
                      0,   if str[0,i] is palindrome ]
            
Drawback:
this methods exceeds time limit on OJ when string length increases to a large length, why?
our isPalindrome() method takes O(n) time

To create palindrome table from string s
  e.g.   mp[i][j]=true if str[i:j] is palindrome.
         mp[i][i]=true; 
         mp[i][j] = true if str[i]==str[j] and (mp[i+1][j-1]==true or j-i<2 )  j-i<2 ensures the array boundary. 
*Note: if str[i] == str[i] and mp[i+1][j-1]==true means that str[i+1] to str[j - 1] is also a palindrome
         
for word "leet" we have mp as following, j - i < 2 ensures when j = 3, i = 3, we do not check [i+1][j-1], i + 1 would go out of bound
   l  e  e  t
--------------
l| T  F  F  F
e|    T  T  F
e|       T  F
t|          T
*/
package Leetcode_Java.dynamic_programming_hard;

/**
 *
 * @author Borui Wang
 */
public class PalindromePartitioning2 {
 
    static int minCut(String s) {
        if(s.length() == 0) {
            return 0;
        }
        int [] mc = new int[s.length()];
        mc[0] = 0;
        boolean [][] palTable = getPalindromes(s);
        
        for(int i = 1; i < s.length(); i++) {
            //initialization
            mc[i] = Integer.MAX_VALUE;
            //0 to i is palindrome
             if(palTable[0][i]) {
            //if(isPalindrome(s.substring(0,i + 1))) {
                    mc[i] = 0;
            } else {
                for(int j = 0; j < i; j++) {
                    if(palTable[j + 1][i]) {
                    //if(isPalindrome(s.substring(j + 1, i + 1))) {
                         mc[i] = Math.min(mc[j] + 1,mc[i]);
                    }
                }
            }
        }
        return mc[s.length() - 1];
    }
    //create palindrome table where table[i][j] shows if substring(i,j) is palindrome
    static boolean[][] getPalindromes(String s){
        boolean [][] res = new boolean[s.length()][s.length()];
        
        for(int i = s.length() - 1; i >= 0; i--) {
            for(int j = i; j < s.length(); j++){
                if(s.charAt(i) == s.charAt(j) && (j - i < 2 || res[i + 1][j - 1])) {
                    res[i][j] = true;
                } else {
                    res[i][j] = false;
                }
            }
        }
        return res;
    }
    //traditional palindrome finder
    static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        
        while(start < end) {
            if(s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
    static void printB(boolean[][] res) {
        for (boolean[] r : res) {
            for (boolean b : r) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
 
         minCut("aab");
    }
}
