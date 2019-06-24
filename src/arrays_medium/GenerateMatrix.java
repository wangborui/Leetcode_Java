package arrays_medium;

/**
 * 06/02/2019
 * Update: 06/24/2019 update using switch statements
 * https://leetcode.com/problems/spiral-matrix-ii/
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * Solution:
 * Define 4 boundaries, top, bottom, left, and right and iterate the matrix in this order:
 * top    -> left,     top++
 * left   -> bottom    left--
 * bottom -> right     bottom--
 * right  -> top       right++
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        if (n <= 0)
            return new int[0][0];

        int left = 0, top = 0;
        int bot = n - 1, right = n - 1;
        int[][] res = new int[n][n];
        int num = 1;
        //left = 0, down = 1, left = 2, up = 3
        int direction = 0;
        while (num < n * n) {
            switch (direction) {
                case 0:
                    for (int i = left; i <= right; i++) {
                        res[top][i] = num++;
                    }
                    top++;
                    direction = 1;
                    break;
                case 1:
                    for (int i = top; i <= bot; i++) {
                        res[i][right] = num++;
                    }
                    right--;
                    direction = 2;
                    break;

                case 2:
                    for (int i = right; i >= left; i--) {
                        res[bot][i] = num++;
                        ;
                    }
                    bot--;
                    direction = 3;
                    break;

                case 3:
                    for (int i = bot; i >= top; i--) {
                        res[i][left] = num++;
                    }
                    left++;
                    direction = 3;
                    break;
            }
        }
        return res;
    }

    //Using Directions
    public int[][] generateMatrix2(int n) {
        if (n <= 0)
            return new int[0][0];

        int left = 0, top = 0;
        int bot = n - 1, right = n - 1;
        int[][] res = new int[n][n];
        int num = 1;
        //left = 0, down = 1, left = 2, up = 3
        int direction = 0;
        while (num <= n * n) {
            switch (direction) {
                case 0:
                    System.out.println(num);
                    for (int i = left; i <= right; i++) {
                        res[top][i] = num++;
                    }
                    top++;
                    direction = 1;
                    break;
                case 1:
                    for (int i = top; i <= bot; i++) {
                        res[i][right] = num++;
                    }
                    right--;
                    direction = 2;
                    break;

                case 2:
                    for (int i = right; i >= left; i--) {
                        res[bot][i] = num++;
                    }
                    bot--;
                    direction = 3;
                    break;

                case 3:
                    for (int i = bot; i >= top; i--) {
                        res[i][left] = num++;
                    }
                    left++;
                    direction = 0;
                    break;
            }
        }
        return res;
    }
}
