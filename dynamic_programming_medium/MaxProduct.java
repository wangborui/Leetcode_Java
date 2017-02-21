// Source : https://oj.leetcode.com/problems/maximum-product-subarray/
// Date   : 02/21/2017

/********************************************************************************** 
* 
* Find the contiguous subarray within an array (containing at least one number) 
* which has the largest product.
* 
* For example, given the array [2,3,-2,4],
* the contiguous subarray [2,3] has the largest product = 6.
* 
* More examples:
*   
*   Input: arr[] = {6, -3, -10, 0, 2}
*   Output:   180  // The subarray is {6, -3, -10}
*   
*   Input: arr[] = {-1, -3, -10, 0, 60}
*   Output:   60  // The subarray is {60}
*   
*   Input: arr[] = {-2, -3, 0, -2, -40}
*   Output:   80  // The subarray is {-2, -40}
*               
**********************************************************************************/
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class MaxProduct {
// The idea is similar with "Find the subarray wich has the largest sum"
// (See: http://en.wikipedia.org/wiki/Maximum_subarray_problem)
// 
// The only thing to note here is, maximum product can also be obtained by minimum (negative) product 
// ending with the previous element multiplied by this element. For example, in array {12, 2, -3, -5, -6, -2}, 
// when we are at element -2, the maximum product is multiplication of, minimum product ending with -6 and -2.
//
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int localMin = nums[0];
        int localMax = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int lastMin = localMin;
            localMin = Math.min(nums[i], Math.min(nums[i] * localMin, nums[i] * localMax));
            //max(current, max(current * last min, current * last max)
            localMax = Math.max(nums[i], Math.max(nums[i] * lastMin, nums[i] * localMax));
            globalMax = Math.max(globalMax, localMax);
        }
        return globalMax;
    }
}
