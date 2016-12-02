/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two_pointer_easy;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class ReverseVowels {
    static String reverseVowels(String s) {
        int i,j;
        i = 0;
        j = s.length()%2 == 1?s.length()/2+1:s.length()/2;
        char [] sc = s.toCharArray();
        while(j<s.length()){
            if(isVowel(sc[i]) && isVowel(sc[j])) {
                char temp = sc[i];
                sc[i] = sc[j];
                sc[j] = temp;
            }
            i++;
            j++;
        }
        
        return new String(sc);
    }
    static boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    public static void main(String[] args){
//        reverseVowels("leetcode");
        StdOut.println("".length());
        
    }
}
