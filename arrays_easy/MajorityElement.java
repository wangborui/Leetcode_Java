/*
Source: https://leetcode.com/problems/majority-element/
********************************************************************************
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
**********************************************************************************
 */
package Leetcode_Java.arrays_easy;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class MajorityElement {
// Moore Voting Algorithm
// Refer to: 
// http://www.cs.utexas.edu/~moore/best-ideas/mjrty/index.html
    static int majorityElement(int[] nums) {
        int majorityElement = 0;
        int count = 0;
        
        for(int num : nums) {
            if(count == 0) {
                majorityElement = num;
                count++;
            } else if(num == majorityElement) {
                count++;
            } else {
                count--;
            }
        }
        return majorityElement;
    }
    
    public static void main(String[] args){
        int [] a = {2147483647};
        StdOut.println(Integer.MAX_VALUE);
       StdOut.println(majorityElement(a));
    }
}
