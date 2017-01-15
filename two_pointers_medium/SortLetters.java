/*
Source : http://www.lintcode.com/en/problem/sort-letters-by-case/
Date   : 01/13/2017
********************************************************************************
Given a string which contains only letters. Sort it by lower case first and upper case second.

 Notice

It's NOT necessary to keep the original order of lower-case letters and upper case letters.

Have you met this question in a real interview? Yes
Example
For "abAcD", a reasonable answer is "acbAD"
********************************************************************************
 */
package Leetcode_Java.two_pointers_medium;

/**
 *
 * @author Borui Wang
 */
public class SortLetters {
    //same idea as partition array
    //loop invariant[lowercase,lowercase,lowercase,i..unknown..j,UPPERCASE,UPPERCASE,UPPERCASE,UPPERCASE,]
    static void sortLetters(char[] chars) {
        if(chars == null || chars.length == 0) {
            return;
        }
        
         //partition all lower case letters  
        int i = 0;
        //partition all capitalized letters  
        int j = chars.length - 1;
        
        while(i != j) {
            while (i != j && Character.isLowerCase(chars[i])) {
                i++;
            }
            while (i != j && Character.isUpperCase(chars[j])) {
                j--;
            }
            exch(chars, i, j);
        }
    }
    static void exch(char [] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    public static void main(String [] args) {
        //char [] a = "abAcD".toCharArray();
        char [] a = "wKJKJjkjKIQOK".toCharArray();
        sortLetters(a);
        for(char c : a) {
            System.out.print(c + " ");
        }
    }
}
