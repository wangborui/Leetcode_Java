/*
Source: http://www.lintcode.com/en/problem/interleaving-positive-and-negative-numbers/#_=_

********************************************************************************
Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

 Notice

You are not necessary to keep the original order of positive integers or negative integers.

Have you met this question in a real interview? Yes
Example
Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.
********************************************************************************
 */
package Leetcode_Java.two_pointers_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class Rerange {

    //brute force, sort the array and use O(n log n) time
    static void rerange(int[] A) {
        // write your code here

        //sort those numbers
        //start with first number which must be negative
        //number after start num is the last number in array
        int[] temp = new int[A.length];
        Arrays.sort(A);

        int i = 0;
        int j = 0;
        for (int k = 0; k < A.length; k++) {
            if (A[k] > 0) {
                j = k;
                break;
            }
        }

        int negCount = j - i;
        int posCount = A.length - j;

        if (negCount == posCount) {
            for (int k = 0; k < A.length; k += 2) {
                temp[k] = A[i++];
                temp[k + 1] = A[j++];
            }
        } else if (negCount < posCount) {
            for (int k = 0; k < A.length - 1; k += 2) {
                temp[k] = A[j++];
                temp[k + 1] = A[i++];
            }

            temp[A.length - 1] = A[j];
        } else {
            for (int k = 0; k < A.length - 1; k += 2) {
                temp[k] = A[i++];
                temp[k + 1] = A[j++];
            }

            temp[A.length - 1] = A[i];
        }

        //copy temp to A 
        for (int k = 0; k < A.length; k++) {
            A[k] = temp[k];
        }
    }
    //using two pointers
//    先扫描一般数组统计一下正数个数和负数个数，较大个数的数作为结果数组开始元素
//    在用两个指针分别从做到右找正数和负数
//    用quick sort 的方法交换位置错误的正数和负数
    
    public void rerangeTwoPointers(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        int n = A.length;
        int pos = 0;
        int neg = 0;
        //find total counts of positive and negative numbers
        for (int num : A) {
            if (num > 0) {
                pos++;
            } else {
                neg++;
            }
        }
        //start with positive number
        if (pos >= neg) {
            pos = 0;
            neg = 1;
        } else {
            pos = 1;
            neg = 0;
        }
        while (pos < n || neg < n) {
            //pos pointer will stop on negative number
            while (pos < n && A[pos] > 0) {
                pos += 2;
            }
            //negative pointer will stop on positive number
            while (neg < n && A[neg] < 0) {
                neg += 2;
            }

            if (pos < n && neg < n) {
                //swap
                int temp = A[pos];
                A[pos] = A[neg];
                A[neg] = temp;
            }
        }
    }

    private void exch(int[] A, int i, int j) {
        A[i] ^= A[j];
        A[j] ^= A[i];
        A[i] ^= A[j];
    }

    public static void main(String[] args) {
        //-33 -19 -9 21 26 30
        int[] A = {-33, -19, 30, 26, 21, -9};
        rerange(A);
        for (int n : A) {
            System.out.print(n + " ");
        }
    }
}
