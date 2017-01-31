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

public class FindKthLargest {
    /**
     * O(n log n) sorting algorithm
     * @param nums
     * @param k
     * @return kth largest element
     * Analysis:
     * 
     * This problem is asking us to find the kth largest element in the array if the array is sorted.
     * this is the same as finding the element with index n - k, why?
     * 
     *      if k = 1, meaning find the largest number, which is the last number after sorting array
     *      we can also look at k = length - k smallest number, if k = 1, length = 6, then length - 1 = 5, which is the last number in the sorted array
     *      choose a random pivot number, partition the array with p, <= p to the left, > p to the right
     *
     * Because there are duplicates in the array, we choose a 3 way partition method with this invariant, based on the following methodology
     * A number is less than pivot, 
     * A number is equal to pivot,
     * A number is greater than pivot
     *      
     *      During partition:
     *      <,<,<,=,=,=,{..unknown..},>,>,>
     *             i      j            k
     *  
     *      After partition:
     *      <,<,<,=,=,=,=,=,=,>,>,>,>,>,>,
     *             i           k j
     * 
     * Pitfalls:
     * 
     *      1.) pointer k will always point to the pivot element 
     *      2.) we need to use random generator to choose pivot number randomly every time
     */
    public int findKthLargestSort(int[] nums, int k) {
        Arrays.sort(nums);
        int N = nums.length;
        return nums[N - k];
    }

    //randomize pivot with partition O(n)

    //invariant <<<< | ====== | unknown | >>>>>>
    //                 i       j       k
    private static final Random rand = new Random();
    static int findKthLargest(int[] nums, int k) {
        //this case will not happen
        if(nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        int n = nums.length;
        k = n - k;
        return partition(nums, 0, n - 1, k);
    }
    static int partition(int[] nums, int start, int end, int target) {
        int i = start; 
        int j = start; 
        int k = end;
        int bound = start;
        if(end - start != 0) {
            bound = start + rand.nextInt(end - start);
        }  
        int pivot = nums[bound];
        
        while(j <= k) {
            if(nums[j] < pivot) {
                swap(nums, i++, j++);
            } else if(nums[j] == pivot) {
                j++;
            } else {
                swap(nums, j, k--);
            } 
        }
        
        if(k == target) {
            return nums[k];
        } else if(k < target) {
            return partition(nums, k + 1, end, target);
        } else {
            return partition(nums, start, k - 1, target);
        }
    }
    static void swap(int[]nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        findKthLargest(new int[]{1}, 1);
        //findKthLargest(new int[]{3,2,1,5,6,4},2);
    }
}
