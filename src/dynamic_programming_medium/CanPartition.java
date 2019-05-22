/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class CanPartition {

    static boolean canPartition(int[] nums) {
        int total = 0;
        for (int i : nums) {
            total += i; // compute the total sum of the input array
        }
        if (total % 2 != 0) {
            return false; // if the array sum is not even, we cannot partition it into 2 equal subsets 
        }
        int max = total / 2; // the maximum for a subset is total/2
        int[][] results = new int[nums.length][max]; // integer matrix to store the results, so we don't have to compute it more than one time
        return isPartitionable(max, 0, 0, nums, results);
    }

    static boolean isPartitionable(int max, int curr, int index, int[] nums, int[][] results) {
        if (curr > max || index > nums.length - 1) {
            return false; // if we passed the max, or we reached the end of the array, return false
        }
        if (curr == max) {
            return true; // if we reached the goal (total/2) we found a possible partition
        }
        if (results[index][curr] == 1) {
            return true; // if we already computed teh result for the index i with the sum current, we retrieve this result (1 for true)
        }
        if (results[index][curr] == 2) {
            return false; // if we already computed teh result for the index i with the sum current, we retrieve this result (2 for false)
        }
        boolean res = isPartitionable(max, curr + nums[index], index + 1, nums, results) || isPartitionable(max, curr, index + 1, nums, results); // else try to find the equal partiion, taking this element, or not taking it
        results[index][curr] = res ? 1 : 2; // store the result for this index and this current sum, to use it in dynamic programming
        return res;
    }
    public static void main(String[] args){
        //System.out.println(canPartition(new int[]{1, 5, 11, 5})) ;
        System.out.println((7&1)==0);
    }
}
