/*
Source : http://www.lintcode.com/en/problem/partition-array/
Date   : 01/13/2017
********************************************************************************
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

 Notice

You should do really partition in array nums instead of just counting the numbers of integers smaller than k.

If all elements in nums are smaller than k, then return nums.length
********************************************************************************
 */
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class PartitionArray {
    	/** 
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     * 
     * we use pointer i to represent the begining of the array, i meaning nums[i] < k
     * pointer j to represent the end of the array n - 1, j means nums[j] >= k
     * 
     * since we are moving i and j one step at a time, we can end the loop when i == j
     * 
     * loop invariant[<k,<k,<k,<k,i..unknown..j,>=k,>=k,>=k,>=k]
     * 
     * there are three possible conditions:
     * k is less than all numbers in array,          [i...xxx...j] - > [ij...xxx...]
     * k is greater than all numbers in array        [i...xxx...j] - > [...xxx...ij]
     * k is in the middle of array                   [i...xxx...j] - > [...xx,ij,x...]
     * 
     * Note: If all elements in nums are smaller than k, then return nums.length
     * we check if nums[j] >= k, if not, we return j + 1
     */
    static int partitionArray(int[] nums, int k) {
	    //write your code here
	    if(nums == null || nums.length == 0) {
	        return 0;
	    }
	    //i points to values <  k
	    int i = 0;
	    //j points to values >= k
	    int j = nums.length - 1;
	    //loop terminates when i == j
	    while(i != j) {
	        while(i != j && nums[j] >= k) {
	            j--;
	        }
	        while(i != j && nums[i] < k) {
	            i++;
	        }
	       exch(nums, i, j);
	    }
	    
	    return nums[j] >= k ? j : j + 1;
    }
    static void exch(int [] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String[] args) {
        System.out.println(partitionArray(new int[]{3,2,3}, 3));
    }
}
