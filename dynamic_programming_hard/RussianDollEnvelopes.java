/*
Source: https://leetcode.com/problems/russian-doll-envelopes/#/description
Date  : 07/25/2017
********************************************************************************
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique dp are there?
 *    start                                                  
 *    +---------+----+----+----+----+----+                   
 *    |----|    |    |    |    |    |    |                   
 *    |----|    |    |    |    |    |    |                   
 *    +----------------------------------+                   
 *    |    |    |    |    |    |    |    |                   
 *    |    |    |    |    |    |    |    |                   
 *    +----------------------------------+                   
 *    |    |    |    |    |    |    |----|                   
 *    |    |    |    |    |    |    |----|                   
 *    +----+----+----+----+----+---------+                   
 *                                   finish 

Above is a 3 x 7 grid. How many possible unique dp are there?

Note: m and n will be at most 100.
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_hard;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Borui Wang
 */
public class RussianDollEnvelopes {
    //O(n^2) Dynamic Programming
    /**
     * Sort all envelopes either by height, and if two envelopes have the same height, sorted them by any order
     * 
     * Sub-optimal function:
     * f[i] means how many envelops can the ith envelope enclose
     * 
     */
    public int maxEnvelopes(int[][] e) {
        if(e == null || e.length == 0 || e[0].length == 0) {
            return 0; 
        }
        
        Arrays.sort(e, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });
        
        int n = e.length;
        int[] dp = new int[n];
        int max = 1;
        
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(e[j][0] < e[i][0] && e[j][1] < e[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(dp[i], max);
                }
            }
        }
        
        return max;
    }
    //O(n log n), same as "Longest Increasing Subsequence"
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });

        int n = envelopes.length;
        int[] sorted = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            int idx = Arrays.binarySearch(sorted, 0, max, envelopes[i][1]);
            if (idx < 0) {
                idx = -idx - 1;
            }

            if (idx == max) {
                sorted[max++] = envelopes[i][1];
            } else {
                sorted[idx] = envelopes[i][1];
            }
        }

        return max;
    }
}
