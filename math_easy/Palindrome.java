/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.math_easy;

/**
 *
 * @author Borui Wang
 */
public class Palindrome {
     static boolean isPalindrome(int x) {
        if(x < 0) return false;
        int i,j,power;
        i = x;
        j = 0;
        while(i>0){
            j = j*10+i%10;
            i/=10;
        }
        return j==x;
    }
     public static void main(String []args){
         isPalindrome(121);
     }
}
