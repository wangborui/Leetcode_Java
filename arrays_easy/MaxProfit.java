/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_easy;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class MaxProfit {
//    public int maxProfit(int[] prices) {
//        int n = prices.length;
//        if(n <=1) return 0;
//        int max = 0;
//        for(int i = 0; i < n; i++){
//            int dif = 0;
//            if(i+1<n){
//                for(int j = i+1; j < n; j++){
//                    dif = prices[j] - prices[i];
//                    if(dif == 6){
//                        int a = 0;
//                    }
//                    if(dif >max) max = dif;
//                }
//            }
//        }
//        return max;
//    }
    //dynamic programming solutions 
    public int maxProfit(int[]prices){
        int minValue = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for(int i = 0; i < prices.length; i ++){
            if(prices[i] - minValue > maxProfit) maxProfit =  prices[i] - minValue;
            else if(prices[i] < minValue) minValue = prices[i];
        }
        return maxProfit;
    }
    public static void main(String[] args){
        int [] test = {7,6};
        StdOut.println(new MaxProfit().maxProfit(test));
    }
}
