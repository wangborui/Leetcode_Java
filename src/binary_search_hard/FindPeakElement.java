/*

// Source : https://oj.leetcode.com/problems/find-peak-element/
// Date   : 01/17/2017

/********************************************************************************** 
* 
* A peak element is an element that is greater than its neighbors.
* 
* Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
* 
* You may imagine that num[-1] = num[n] = -∞.
* 
* For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
* 
* click to show spoilers.
* 
* Note:
* Your solution should be in logarithmic complexity.
* 
* Credits:Special thanks to @ts for adding this problem and creating all test cases.
*               
********************************************************************************* 

2 similar questions:
case 1:
 this question condition -infinity{0- - - - -numbers}-infinity
so we set start = 0, end = n - 1, and when mid go out of bound we set it to -inifity 

case 2: 
The numbers in adjacent positions are different.
A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
we need to set start  = 1, end = n - 2 to avoid overflow
time O(log n) space O(1)

Another clever time O(n)

public int findPeakElement(int[] nums) {
        for(int i = 1; i < nums.length;i++){
            if(nums[i] < nums[i-1])
                return i-1;
        }
        return nums.length - 1;
    }

 */
package Leetcode_Java.binary_search_hard;

/**
 *
 * @author Borui Wang
 */
public class FindPeakElement {
    /*
 *    Binary search is common idea here.
 *
 *    However, you need to think about two senarios:
 *
 *    1) Becasue we need check `num[mid-1]`, `num[mid]`, `num[mid+1]`, 
 *       So, we need make sure there hasn't out-of-boundary issue.
 *   
 *
 *
 *    2) There are multiple Peak elements.
 *
 *       For example: [1,2,1,2,1], or [ 1,2,3,1,2,1]
 *
 *       LeetCode doesn't tell you what the expected result is. I guess:
 *
 *       2.1) for [1,2,1,2,1] you can return either 1 or 3, because both them are peak elements
 *
 *       2.1) for [1,2,3,2,4,2,1] it should return 4, because num[4] is the real peak.  but Leetcode accept either 2 or 4
 *
 */
    static int findPeakElement(int[] nums) {
        int start = 0, end = nums.length - 1;
        while(start + 1 < end){
            int mid = (start + end) >>> 1;
            int prev = (mid - 1) >= 0 ? nums[mid-1] : Integer.MIN_VALUE;
            if(nums[mid] < prev) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if(nums[start] < nums[end]) {
            return end;
        }
        else {
            return start;
        }
    }
    public static void main(String [] args){
        System.out.println(findPeakElement(new int[]{1,2}));
    }
}
