/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class MySqrt {
    static int mySqrt(int x) {
        int start = 0;
        int end = x/2;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            long sqr = mid * mid;
            if(sqr >= x) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if(end * end == x) {
            return end;
        } 
        return start;
    }
    public static void main(String[] args){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(2147395599);
        System.out.println(mySqrt(2147395599));
    }
}
