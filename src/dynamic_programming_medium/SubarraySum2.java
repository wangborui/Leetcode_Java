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

import java.util.Arrays;

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
    
    static int subarraySumII2(int[] A, int start, int end) {
        int len = A.length;
        for(int i = 1; i < len; i++) {
            A[i] += A[i - 1];
        }
        Arrays.sort(A);
        
        int count = 0;
        for(int i  = 0; i < len; i++) {
            if(A[i] >= start && A[i] <= end) {
                count++;
            }
            int l = A[i] - end;
            int r = A[i] - start;
            count += find(A, len, r + 1) - find(A, len, l);
        }
        return count;
    }
    static int find(int[] A, int len, int value) {
        if(A[len - 1] < value) {
            return len;
        }
        
        int l = 0;
        int r = len - 1;
        int ans = 0;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(value <= A[mid]) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(subarraySumII2(new int[]{1,2,3,4},1,3));
    }
}
