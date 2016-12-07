/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.binary_search_easy;

/**
 *
 * @author Borui Wang
 */
public class ArrangeCoins {
    static int arrangeCoins(int n) {
        //needed long because needed variable could overflow
       long s = 1, e = n;
        
        while(s + 1 < e){
            long mid = (s + e) >>> 1;
            //needed could overflow
            long needed = mid * (mid + 1) / 2;
            if(needed >= n) { 
                e = mid;
            }
            else { 
                s = mid;   
            }
        }
        if(e * (e + 1) / 2 > n) {
            return (int)e - 1;
        }
        return (int)e;
    }
    public static void main(String[] args){
        System.out.println(arrangeCoins(1804289383));
    }
}
