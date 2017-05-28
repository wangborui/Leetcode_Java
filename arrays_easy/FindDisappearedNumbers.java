// Source : https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/#/description
// Date   : 05/27/2017
/**
 * ********************************************************************************
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 * Example:
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 *********************************************************************************
 */
package Leetcode_Java.arrays_easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class FindDisappearedNumbers {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
