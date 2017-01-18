// Source : https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
// Date   : 01/18/2017
/**
 * ********************************************************************************
 *
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 *
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 *
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 *
 *
 *********************************************************************************
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class RemoveDuplicates2 {

    /**
     * initialize I = 0, j = 1, count = 1, to record how many times element
     * appears invariant: 0 : ith -1 are all valid elements, and ith : j are all
     * removed duplicates, j + 1 : n are unknown areas. Note: ith = 0 because
     * the first element could always be In the final result
     *
     * @param nums
     * @return Integer
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 1;
        int n = nums.length;
        int count = 1;

        while (j < n) {
            if (j < n && nums[j] == nums[j - 1]) {
                count++;
                if (count < 3) {
                    nums[++i] = nums[j];
                }
                j++;
            } else {
                count = 1;
                nums[++i] = nums[j++];
            }
        }
        return i + 1;
    }
}
