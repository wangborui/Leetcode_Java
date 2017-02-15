// Source : https://leetcode.com/problems/minimum-size-subarray-sum/
// Date   : 02/15/2017

/********************************************************************************** 
 * 
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray 
 * of which the sum ≥ s. If there isn't one, return 0 instead.
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * click to show more practice.
 * 
 * More practice:
 * 
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 * 
 * Credits:Special thanks to @Freezen for adding this problem and creating all test cases.
 *               
 **********************************************************************************/

package Leetcode_Java.two_pointers_medium;

/**
 *
 * @author Borui Wang
 */
public class MinSubArrayLen {     
    //the idea is:
      
//     1.)starts with 2 pointers i = 0, j = 1, sum = n[0],
//     2.)if current sum >= s, we move i to the right one.
//     3.)if current sum < s, we keep moving j until sum >= s
//       Time O(n)  Space O(1)
//     Pitfall, for every subarray size we calcuate, we need to make sure sum >= s, then calculate subarray size j - i
    static int minSubArrayLenTwoPointers(int s, int[] nums) {
        if(s <= 0 || nums == null || nums.length == 0) {
            return 0;
        }
        
        int j = 1;
        int sum = nums[0];
        int min = Integer.MAX_VALUE;
        
        for(int i = 0; i < nums.length; i++) {
            while(j < nums.length && sum < s) {
                sum += nums[j++];
            }
            //i goes from 0 to n, so we need to check if sum >= s when sum is < s for some i
            if(sum >= s) {
                min = Math.min(min, j - i);
            }
            sum -= nums[i];
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
     public static void main(String[] args){
         System.out.println(minSubArrayLenTwoPointers(7, new int[]{2,3,1,2,4,1,3}));
     }
}
