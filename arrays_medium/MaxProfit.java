/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class MaxProfit {
    static int maxProfit(int[] prices) {
        if(prices.length < 2) return 0;
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int max = 0;
        while(i < prices.length){
            while(i<prices.length -1 && prices[i] >= prices[i+1]) i++;
            valley = prices[i];
            while(i<prices.length-1&&prices[i]<= prices[i+1])i++;
            peak = prices[i];
            max += peak-valley;
        }
        return max;
    }
    public static void main(String[] args){
         System.out.println(maxProfit(new int[]{2,5,2,3,9}));
    }
}
