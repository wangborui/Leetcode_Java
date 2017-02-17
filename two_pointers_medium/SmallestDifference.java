//Source : http://www.lintcode.com/en/problem/the-smallest-difference/
//Date   : 02/17/2017

/**
 * ******************************************************************************
 * Given two array of integers(the first array is array A,
 * the second array is array B), now we are going to find a element in array A which is A[i],
 * and another element in array B which is B[j], so that the difference between A[i] and B[j]
 * (|A[i] - B[j]|) is as small as possible, return their smallest difference.
 *
 * Example
 * For example, given array A = [3,6,7,4], B = [2,8,9,3], return 0
 *
 * Challenge
 * O(n log n) time
 ********************************************************************************
 */
package Leetcode_Java.two_pointers_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class SmallestDifference {

    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
//    Idea:
//    
//    Sort array A and B, and create 2 pointesr i and j starting at 0
//    
//    the goal is to choose 2 numbers whose values as close to each other as possible
//    
//    so we calculate the difference, then compare A[i] and B[j]
//    
//    if A[i] < B[j] we need to move i to right so that A[i] can have closer value to B[j]
//    
//    else move j++, we continue until either i or j is out of bound or their respective array
    public int smallestDifference(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }
        Arrays.sort(A);
        Arrays.sort(B);

        int diff = Integer.MAX_VALUE;
        int j = 0;
        int i = 0;
        int n = A.length;
        int m = B.length;

        while (i < n && j < m) {
            diff = Math.min(diff, Math.abs(A[i] - B[j]));
            if (A[i] < B[j]) {
                i++;
            } else {
                j++;
            }
        }

        return diff;
    }
}
