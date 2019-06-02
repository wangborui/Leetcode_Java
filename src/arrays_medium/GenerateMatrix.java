package arrays_medium;

/**
 * 06/02/2019
 * https://leetcode.com/problems/spiral-matrix-ii/
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * Solution:
 * Define 4 boundaries, top, bottom, left, and right and iterate the matrix in this order:
 * top    -> left,     top++
 * left   -> bottom    left--
 * bottom -> right     bottom--
 * right  -> top       right++
 *
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if(n == 0) {
            return res;
        }


        int top = 0;
        int right = n - 1;
        int left = 0;
        int bottom = n - 1;
        int count = 1;

        while(true) {
            for(int j = left; j <= right; j++) {
                res[top][j] = count++;
            }
            top++;
            if(count > n * n) {
                break;
            }

            for(int i = top; i <= bottom; i++) {
                res[i][right] = count++;
            }
            right--;
            if(count > n * n) {
                break;
            }

            for(int j = right; j >= left; j--) {
                res[bottom][j] = count++;
            }
            bottom--;
            if(count > n * n) {
                break;
            }

            for(int i = bottom; i >= top; i--) {
                res[i][left] = count++;
            }
            left++;
            if(count > n * n) {
                break;
            }
        }

        return res;
    }
}
