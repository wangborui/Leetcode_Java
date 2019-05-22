/*
Source: https://leetcode.com/problems/perfect-squares/#/description
Date  : 07/26/2017
********************************************************************************
Given a positive integer n, find the least number of perfect square numbers 
(for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; 
given n = 13, return 2 because 13 = 4 + 9.
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class PerfectSquare {
    /**
     * Initialization:
     * f[0] = 0, there is 0 perfect square numbers to add up to 0
     * 
     * Sub-optimal Function:
     * f[i] is the minimum number of perfect square number that adds up to i
     */
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                f[i] = Math.min(f[i], f[i - j * j] + 1);
            }
        }

        return f[n];
    }
}
