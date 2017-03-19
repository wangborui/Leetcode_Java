//Date : 03/18/2017
package Leetcode_Java.arrays_medium;

import java.util.Random;

/**
 *
 * @author Borui Wang
 */
public class QuickSort {

    static void sort(int[] A) {

        if (A == null || A.length <= 1) {
            return;
        }
        qsort(A, 0, A.length - 1);

    }

    static void qsort(int[] A, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int p = partition(A, lo, hi);
        qsort(A, lo, p - 1);
        qsort(A, p + 1, hi);
    }

    static int partition(int[] A, int lo, int hi) {
        int pivotIndex = lo + (hi - lo) / 2;
        int pivot = A[pivotIndex];
        swap(A, lo, pivotIndex);

        int i = lo + 1;
        int j = hi;

        while (i < j) {
            while (i < j && A[i] <= pivot) {
                i++;
            }
            while (i < j && A[j] > pivot) {
                j--;
            }

            if (i < j) {
                swap(A, i, j);
            }
        }
//        因为在while loop结束后我们不知道i和j所指向的数字的状态，我们必须检查A[i] 是否大于pivot
//        因为我们最后跟pivot交换的数字必须是不能大于pivot的
        if (A[i] > pivot) {
            i--;
        }
        swap(A, i, lo);
        return i;
    }

    static void swap(int[] chars, int i, int j) {
        int ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;
    }

    static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //int[] nums = {9, 2, 4, 7, 3, 7, 10};
        //int[] nums = {3,3,3,3,3};
        //int[] nums = {8,7,6,5,4,3,2,1};
        int[] nums = {1,2,3,4,5,6,7,8,9};
        sort(nums);
        printArray(nums);
    }
}
