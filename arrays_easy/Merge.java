/*
Source: https://leetcode.com/problems/merge-sorted-array/

Problem:Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.

Solution:
Time O(m+n) Space O(1)
use 3 pointers i, j, k. 
i represents the end of the merged array and decrement each time we add one element to num1
j represents the end of nums1 array and decrement 1 when its value is added into merged array
k represents the end of nums2 array and decrement 1 when its value is added into merged array

Follow up:
1.merge two sorted array of size m and n into another array of size m+n
2.what if one array is very large and the other one is very small?
* In this case if we assume m is much larger than n, then we can use binary search to find the insersion position of each element in n into m.
this costs time O(n log m), then we copy those elements from array n into new array k, then copy everything from array m time O(m + n)
this methods saves time because reading and writing elements alternatives from m,n, and k costs more time than to go directly all from n to k, then m to k

 */
package Leetcode_Java.arrays_easy;


/**
 *
 * @author Borui Wang
 */
public class Merge {
//    找到第一个数组的最后一个数字和第二个数组的最后一个数字
//    建立一个变量来记录合并后的最后一个下标
//    每次遍历两个数组的最后一个数字，比较大的放在合并数组的最后一个下标位置
//    移动合并数组下标和数组一或者数组二的下标
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //j is the last element in nums1, k is the last element in nums2
        int j = m - 1, k = n - 1;
        //i = m + n - 1, this is the last element of the merged array, each time i decrease 1 
        for(int i = m + n - 1; i >= 0; i--) {
            //either j is less than 0 or k is less than 0, meaning either nums1 or nums2 are finished, but the other one is not
            //In this case, we need to use Integer.MIN_VALUE to represent the value from the array already depleted
            //If both j and k >= 0, we choose the value from its array
            int one = (j < 0) ? Integer.MIN_VALUE : nums1[j];
            int two = (k < 0) ? Integer.MIN_VALUE : nums2[k];
            if(one >= two) {
                nums1[i] = nums1[j--];
            } else {
                nums1[i] = nums2[k--];
            }
        }
    }
 /*Test cases
    *case 1: when nums1 and nums2 are both size 0
    *case 2: when one of them is size 0 the other one is not
    *case 3: when both are greater than size 0
    */
    public static void main(String[] args){
        int []a={2,4,6,8,0,0,0};
        int []b ={7,9,11};
        new Merge().merge(a, 4, b, 3);
    }
}
