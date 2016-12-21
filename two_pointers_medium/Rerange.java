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

    //brute force
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
    public void rerangeTwoPointers(int[] A) {
        // write your code here
        if(A == null || A.length < 2) {
            return;
        }
        int posCount = 0; 
        int negCount = 0;
        //assuming more positive numbers than negative numbers
        int posIdx = 0;
        int negIdx = 1;
        
        for(int i = 0; i < A.length; i++) {
            if(A[i] > 0) {
                posCount++;
            } else {
                negCount++;
            }
        }
        
        if(negCount > posCount) {
            negIdx = 0;
            posIdx = 1;
        }
        
        while(posIdx < A.length && negIdx < A.length) {
            while(posIdx < A.length && A[posIdx] > 0) {
                posIdx += 2;
            }
            while(negIdx < A.length && A[negIdx] < 0) {
                negIdx += 2;
            }
            if(posIdx < A.length && negIdx < A.length) {
                exch(A, posIdx, negIdx);
            }
        }
   }
   private void exch(int []A, int i, int j) {
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
