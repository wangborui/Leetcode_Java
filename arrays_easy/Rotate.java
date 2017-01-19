// Source : https://leetcode.com/problems/rotate-array/
// Date   : 01/18/2017

/********************************************************************************** 
* 
* Rotate an array of n elements to the right by k steps.
* For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 
* 
* Note:
* Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
* 
* Hint:
* Could you do it in-place with O(1) extra space?
* 
* Related problem: Reverse Words in a String II
* 
* Credits:Special thanks to @Freezen for adding this problem and creating all test cases.
*               
**********************************************************************************/
package Leetcode_Java.arrays_easy;

/**
 *
 * @author Borui Wang
 */
public class Rotate {
    
/*
 * this solution is so-called three times rotate method
 * because (X^TY^T)^T = YX, so we can perform rotate operation three times to get the result
 * obviously, the algorithm consumes O(1) space and O(n) time
 *
 * How to change [0,1,2,3,4,5,6] to [4,5,6,0,1,2,3] by k = 3?
 *
 * We can change by following rules: 
 *
 *     [0]->[3], [3]->[6], [6]->[2],  [2]->[5], [5]->[1], [1]->[4]
 *    
 *
 */
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length < 2 || k == 0) {
            return;
        }
        
        //reverse 0 : split - 1, and split : n - 1
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
