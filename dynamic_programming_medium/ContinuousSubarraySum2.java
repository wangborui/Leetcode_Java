//Source : https://algorithm.yuanbin.me/zh-hans/problem_misc/continuous_subarray_sum_ii.html
//Date   : 03/03/2017
/**
 * ******************************************************************************
 * Given an integer array, find a continuous rotate subarray where the sum of numbers is the biggest.
 * Your code should return the index of the first number and the index of the last number.
 * (If their are duplicate answer, return anyone. The answer can be rorate array or non- rorate array)+
 *
 * Example
 *
 * Give [3, 1, -100, -3, 4], return [4,1].
 *******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.ArrayList;

/**
 *
 * @author Borui Wang
 */
public class ContinuousSubarraySum2 {

    static ArrayList<Integer> continuousSubarraySumII(int[] A) { // Write your code here 
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(0);
        result.add(0);
        int total = 0;
        int len = A.length;
        int start = 0, end = 0;
        int local = 0;
        int global = -0x7fffffff;
        for (int i = 0; i < len; ++i) {
            total += A[i];
            if (local < 0) {
                local = A[i];
                start = end = i;
            } else {
                local += A[i];
                end = i;
            }
            if (local >= global) {
                global = local;
                result.set(0, start);
                result.set(1, end);
            }
        }
        local = 0;
        start = 0;
        end = -1;
        for (int i = 0; i < len; ++i) {
            if (local > 0) {
                local = A[i];
                start = end = i;
            } else {
                local += A[i];
                end = i;
            }
            if (start == 0 && end == len - 1) {
                continue;
            }
            if (total - local >= global) {
                global = total - local;
                result.set(0, (end + 1) % len);
                result.set(1, (start - 1 + len) % len);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        //3, 1, -100, -3, 4
        System.out.println(continuousSubarraySumII(new int[]{3, 1, -100, -3, 4}));
    }
}
