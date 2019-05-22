/*
Source : https://leetcode.com/problems/product-of-array-except-self/
Date   : 01/11/2017
********************************************************************************
Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
********************************************************************************

 */
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
//Given numbers [2, 3, 4, 5], regarding the third number 4, the product of array except 4 is 2*3*5 
//which consists of two parts: left 2*3 and right 5. The product is left*right. We can get lefts and rights:
//
//Numbers:     2    3    4     5
//Lefts:            2   2*3  2*3*4
//Rights:   3*4*5  4*5   5      
//Let’s fill the empty with 1:
//
//Numbers:     2    3    4     5
//Lefts:       1    2   2*3  2*3*4
//Rights:   3*4*5  4*5   5     1
//We can calculate lefts and rights in 2 loops. The time complexity is O(n).
//
//We store lefts in result array. If we allocate a new array for rights. The space complexity is O(n). To make it O(1), 

//先从左往右扫描一遍，计算出每个数除了自己以外所有左边数的乘积，注意第一个数左边数的乘积是1
//从右往左扫面一遍，计算出每个数除了自己以外所有右边数的乘积，注意最后一个数右边数乘积是1
//在扫描一遍数组，把每个数左边的乘积乘以其右边数的乘积，就得到了我们的答案了
public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int [] res = new int[nums.length];
        //first store all left products except i in res[i]
        int rightP = 1;
        //calculate all left products
        for(int i = 0; i < nums.length;i++){
            res[i] = (i==0)?1: res[i-1]*nums[i-1];
        }
        //find the right products
        for(int i = nums.length -1; i >= 0; i--){
            rightP = (i==nums.length-1)?1:rightP * nums[i+1];
            res[i] = res[i]*rightP;
        }
        return res;
    }
}
