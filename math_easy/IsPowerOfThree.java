/*
Source : https://leetcode.com/problems/power-of-three/
Date   : 01/13/2017
********************************************************************************** 
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
********************************************************************************** 

 */
package Leetcode_Java.math_easy;

/**
 *
 * @author Borui Wang
 */
public class IsPowerOfThree {
//if a number is a power of three, we can write it as 3^x = n or take log on both side
//log_3 n = x, using properties of log, we have log_a n / log_a 3 = x
      static boolean isPowerOfThree(int n) {
        if(n <= 0) return false;
        int base3 = (int)(Math.log(n)/Math.log(3.0));
        return Math.pow(3,base3) == n; 
    }
        public static void main(String[] args){
        //System.out.println(214748364 );
         System.out.println(isPowerOfThree(243));
    }
}
