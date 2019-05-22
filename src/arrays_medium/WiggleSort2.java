// Source : https://leetcode.com/problems/wiggle-sort-ii/
// Date   : 03/19/2017

/*************************************************************************************** 
 *
 * Given an unsorted array nums, reorder it such that
 *     nums[0]  nums[2] .
 * 
 *     Example:
 *     (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
 *     (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * 
 *     Note:
 *     You may assume all input has valid answer.
 * 
 *     Follow Up:
 *     Can you do it in O(n) time and/or in-place with O(1) extra space?
 * 
 * Credits:Special thanks to @dietpepsi for adding this problem and creating all test 
 * cases.
 ***************************************************************************************/

package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class WiggleSort2 {
//    先用quick select的方法找出median，把原数组分成两拨，一边只有小于等于median的值，另一半只有大于等于median的值
//    这个题只能用3 way partition来把重复的元素都归类在一起
//    然后建立一个临时数组，用两个指针，一个指向median元素的下标，called small，另一个指向数组结尾，called large
//    在临时数组中先加入temp[i++] = nums[small--],然后temp[i++] = nums[large--];
    static void wiggleSort(int[] nums) {
         if(nums == null || nums.length == 0) {
             return;
         }
         int median = 0;
         if(nums.length % 2 == 0) {
            median = partition(nums, 0, nums.length - 1, nums.length / 2 - 1);
         } else {
            median = partition(nums, 0, nums.length - 1, nums.length / 2);
         }
         //small is the index of median value
         int small = (nums.length % 2 == 0) ? nums.length / 2 - 1 : nums.length / 2;
         int large = nums.length - 1;
         int[] temp = new int[nums.length];
         for(int i = 0; i < nums.length; i++) {
             if(i % 2 == 0) {
                 temp[i] = nums[small--];
             } else {
                 temp[i] = nums[large--];
             }
         }
         System.arraycopy(temp, 0, nums, 0, nums.length);
    }
    static int partition(int[] nums, int start, int end, int rank) {
        if(start >= end) {
            return nums[start];
        }
        int pivot = nums[start];
        int i = 0;
        int j = 0;
        int k = end;
        
        while(j <= k) {
            if(nums[j] == pivot) {
                j++;
            } else if(nums[j] < pivot) {
                swap(nums, i++, j++);
            } else {
                swap(nums, j, k--);
            }
        }
        if(k == rank) {
            return nums[k];
        } else if(k < rank) {
            return partition(nums, k + 1, end, rank);
        } else {
            return partition(nums, start, k - 1, rank);
        }
    }
 
    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static void printArray(int[] nums) {
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums1 = {1, 5, 1, 1, 6, 4};
        int[] nums2 = {1, 3, 2, 2, 3, 1};
         int[] nums3 = {4,6};
        System.out.println("median: " + partition(nums3, 0, nums3.length - 1, (nums3.length % 2 == 0) ? nums3.length / 2 - 1 : nums3.length / 2));
        //wiggleSort(nums4);
        printArray(nums3);
    }
}
