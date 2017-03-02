// Source : http://massivealgorithms.blogspot.com/2015/06/lintcode-find-peak-element-ii-eason-liu.html
// Date   : 03/01/2017
/**
 * ********************************************************************************  *
 * There is an integer matrix which has the following features:
 *
 * The numbers in adjacent positions are different.
 * The matrix has n rows and m columns.
 * For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
 * For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1]. 
 * We define a position P is a
 * peek if:
 *
 * A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i]
 * > A[j][i-1] Find a peak element in this matrix. Return the index of the peak.
 *
 * Notice
 *
 * The matrix may contains multiple peeks, find any of them.        
********************************************************************************
 */
package Leetcode_Java.binary_search_hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class FindPeakElement2 {

    static List<Integer> findPeakII(int[][] A) {
        if(A == null || A.length == 0 || A[0].length == 0) {
            return new ArrayList();
        }
        int m = A.length;
        int n = A[0].length;
        return helper(1, m - 2, 1, n - 2, A, true);
    }
    static List<Integer> helper(int xStart, int xEnd, int yStart, int yEnd, int[][] A, boolean isVertical) {
        if(isVertical) {
            int mid = xStart + (xEnd - xStart) / 2;
            int maxIdx = yStart;
            
            for(int i = yStart; i <= yEnd; i++) {
                if(A[mid][i] > A[mid][maxIdx]) {
                    maxIdx = i;
                }
            }
            //check top
            if(A[mid][maxIdx] < A[mid - 1][maxIdx]) {
                return helper(xStart, mid - 1, yStart, yEnd, A, !isVertical);
            //check bottom
            } else if(A[mid][maxIdx] < A[mid + 1][maxIdx]){
                return helper(mid + 1, xEnd, yStart, yEnd, A, !isVertical);
            } else {
                return new ArrayList(Arrays.asList(mid, maxIdx));
            }
        } else {
            int mid = yStart + (yEnd - yStart) / 2;
            int maxIdx = xStart;
            
            for(int i = xStart; i <= xEnd; i++) {
                if(A[i][mid] > A[maxIdx][mid]) {
                    maxIdx = i;
                }
            }
            //check left
            if(A[maxIdx][mid] < A[maxIdx][mid - 1]) {
                return helper(xStart, xEnd, yStart, mid - 1, A, !isVertical);
            //check right
            } else if(A[maxIdx][mid] < A[maxIdx][mid + 1]) {
                return helper(xStart, xEnd, mid + 1, yEnd, A, !isVertical);
            } else {
                return new ArrayList(Arrays.asList(maxIdx, mid));
            }
        }
    }
    public static void main(String[] args) {
        int[][] in = {{1,2,3,6,5},
                      {16,41,23,22,6},
                      {15,17,22,21,7},
                      {14,18,19,20,10},
                      {13,14,11,10,9}};
        System.out.println(findPeakII(in));
    }
}
