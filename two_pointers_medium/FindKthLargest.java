// Source : https://leetcode.com/problems/kth-largest-element-in-an-array/
// Date   : 01/14/2017
/**
 * ********************************************************************************
 *
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * Credits:Special thanks to @mithmatt for adding this problem and creating all test cases.
 *
 *********************************************************************************
 */
package Leetcode_Java.two_pointers_medium;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Borui Wang
 */
//if k = 1, meaning find the largest number, which is the last number after sorting array
//we can also look at k = length - k smallest number, if k = 1, length = 6, then length - 1 = 5, which is the last number in the sorted array
//choose a random pivot number, partition the array with p, <= p to the left, > p to the right
//after partition, if index of p = length - index = k, return p
//if length - index > k, partition index 
public class FindKthLargest {
    //O(n log n) sorting algo
    public int findKthLargestSort(int[] nums, int k) {
        Arrays.sort(nums);
        int N = nums.length;
        return nums[N - k];
    }

    //randomize pivot with partition O(n)
    private static Random rand = new Random();

    static int findKthLargest(int[] nums, int k) {
        //this case is undefined!
        if (nums == null || nums.length == 0) {
            return -1;
        }
        k = nums.length - k;
        int res = partition(nums, k, 0, nums.length - 1);
        return res;
    }

    static int partition(int[] nums, int target, int start, int end) {

        int position = start;
        if (end - start > 0) {
            position = start + rand.nextInt(end - start);
        }
        int pivot = nums[position];
        int i = start;
        int k = start;
        int j = end;

        //uses three way partition
        while (i <= j) {
            if (nums[i] == pivot) {
                i++;
            } else if (nums[i] < pivot) {
                swap(nums, i++, k++);
            } else {
                swap(nums, i, j--);
            }
        }

        if (j == target) {
            return nums[j];
        } else if (j < target) {
            return partition(nums, target, j + 1, end);
        } else {
            return partition(nums, target, start, j - 1);
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        findKthLargest(new int[]{1}, 1);
        //findKthLargest(new int[]{3,2,1,5,6,4},2);
    }
}
