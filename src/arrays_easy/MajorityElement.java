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
//    建立两个变量，majority element和count = 0，表示我们当前没有找到majority element
//    遍历整个数组， 如果当前访问的数字等于majority element， count + 1
//    如果count = 0， 把当前的访问的元素设置为majority element，count + 1
//    不然我们就把count - 1
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
