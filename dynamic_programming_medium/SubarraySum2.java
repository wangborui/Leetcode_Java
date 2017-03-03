//Source : http://www.cnblogs.com/easonliu/p/4543647.html
//Date   : 03/03/2017

/**
 * ******************************************************************************
 * Given an integer array, find a subarray where the sum of numbers is between two given interval.
 * Your code should return the number of possible answer.
 *
 * Example
 * Given [1,2,3,4] and interval = [1,3], return 4. The possible answers are:
 *
 * [0, 0]
 * [0, 1]
 * [1, 1]
 * [3, 3]
 *******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class SubarraySum2 {
    //brute force O(n^2)
    static int subarraySumII(int[] A, int start, int end) {
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        int[] sums = new int[n + 1];
        //set 0th index as 0 for prefix
        sums[0] = 0;
        for(int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + A[i - 1];
        }
        
        int count = 0;
        
        for(int i = 0; i < n; i ++) {
            for(int j = i + 1; j <= n; j++) {
                int diff = sums[j] - sums[i];
                if(start <= diff && diff <= end) {
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println(subarraySumII(new int[]{1,2,3,4},1,3));
    }
}
