//Source : http://www.lintcode.com/en/problem/subarray-sum-closest/#
//Date   : 03/03/2017
/**
 * ******************************************************************************
 * Given an integer array, find a subarray with sum closest to zero.
 * Return the indexes of the first number and last number.
 *
 * Have you met this question in a real interview? Yes
 * Example
 * Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].
 *
 * Challenge
 * O(nlogn) time
 *******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class SubarraySumClosest {
//Pitfall: at index -1, prefix sum is 0, this is used to cover edge case
//idea is:
//1.) find prefix sum of the array
//2.) sort prefix sum based on sums, while keeping track of each element index
//3.) find the smallest value difference between each prefix sum
//4.) find the from and to index of the subarray
    static class Pair implements Comparable<Pair> {

        int index;
        int value;

        Pair(int i, int s) {
            index = i;
            value = s;
        }

        @Override
        public int compareTo(Pair that) {
            return this.value - that.value;
        }
    }
//    计算整个数组的prefix sum值，把prefix sum 0 放在下标为-1的地方
//    根据prefix sum值来排序，并且记录每个prefix sum的下标（用pair class 来记录）
//    找到两个差最接近0的prefix sum，输出他们的下标
    static int[] subarraySumClosest(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int len = nums.length;
        Pair[] sum = new Pair[len + 1];

        sum[0] = new Pair(-1, 0);
        int tmp = 0;
        for (int i = 1; i <= len; i++) {
            tmp += nums[i - 1];
            sum[i] = new Pair(i - 1, tmp);
        }

        Arrays.sort(sum);
        // printWrap(sum);
        int minSumAbs = Integer.MAX_VALUE;
        int from = 0, to = 0;
        //given prefix value array [X,X,X, i, X,X,X,j]
        //when |value[j] - value[i]| has smallest value, we then know that we found a range from i + 1 to j.
        //thus, we need to find from index =  min(i, j) + 1
        for (int i = 0; i < len; i++) {
            int diff = Math.abs(sum[i].value - sum[i + 1].value);
            if (diff < minSumAbs) {
                minSumAbs = diff;
                from = Math.min(sum[i].index, sum[i + 1].index) + 1;
                to = Math.max(sum[i].index, sum[i + 1].index);
                if (minSumAbs == 0) {
                    break;
                }
            }
        }

        return new int[]{from, to};
    }

    static void printWrap(Pair[] a) {
        for (Pair n : a) {
            System.out.print(n.value + " ");
        }
        System.out.println();
    }

    static void printArray(int[] a) {
        for (int n : a) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArray(subarraySumClosest(new int[]{-3, 1, 1, -3, 5}));
        printArray(subarraySumClosest(new int[]{1}));
    }
}
