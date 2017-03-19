// Source : https://leetcode.com/problems/wiggle-sort/
// Date   : 03/19/2017
/**
 * *************************************************************************************
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1]
 * >= nums[2] <= nums[3]....
 *
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6,
 * 2, 5, 3, 4].
 **************************************************************************************
 */
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class WiggleSort {
//    这个题我们需要维护一个不变条件：
//        当一个数的下标是奇数的时候，这个数必须大于等于之前的数
//        当一个数的下标是偶数的时候，这个数必须小于等于之前的数
//    遍历一边数组，检查上方的条件是否都符合了
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1 && nums[i] < nums[i - 1]) {
                swap(nums, i, i - 1);
            } else if (i % 2 == 0 && nums[i] > nums[i - 1]) {
                swap(nums, i, i - 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
