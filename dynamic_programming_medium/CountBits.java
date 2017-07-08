/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class CountBits {
    static int[] countBits(int num) {
        int [] result = new int [num+1];
        for(int i = 1; i<= num;i++){
            result[i] = result[i>>1] + (i&1);
            }
            return result;
    }
    public static void main(String[] args) {
        for(int i :countBits(4) )
        System.out.println(i);
    }
}
