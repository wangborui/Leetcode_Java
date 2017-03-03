//Source : http://www.lintcode.com/en/problem/continuous-subarray-sum/
//Date   : 03/03/2017
/**
 * ******************************************************************************
 * Given an integer array, find a continuous subarray where the sum of numbers is the biggest.
 * Your code should return the index of the first number and the index of the last number.
 * (If their are duplicate answer, return anyone)
 *
 * Have you met this question in a real interview? Yes
 * Example
 * Give [-3, 1, 3, -3, 4], return [1,4].
 *******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.ArrayList;

/**
 *
 * @author Borui Wang
 */
public class ContinuousSubarraySum {

    /**
     * @param A an integer array
     * @return A list of integers includes the index of the first number and the
     * index of the last number
     */
//    Idea is:
//    similar to maximum subarray, however, we are asked to find the index of max subarray instead of its max value
//            
//    Use localmax to indicate the sum from previous elements
//            
//    if localMax is negative, we can then start recalcuating localMax, set start end to current index
//            
//    else, we add localmax to current element, and update end to i, we then update global max and result
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        ArrayList<Integer> res = new ArrayList();
        if (A == null || A.length == 0) {
            return res;
        }

        int start = 0;
        int end = 0;
        int localMax = 0;
        int globalMax = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            if (localMax < 0) {
                localMax = A[i];
                start = end = i;
            } else {
                localMax += A[i];
                end = i;
            }

            if (localMax > globalMax) {
                globalMax = localMax;
                res.clear();
                res.add(start);
                res.add(end);
            }
        }
        return res;

    }
}
