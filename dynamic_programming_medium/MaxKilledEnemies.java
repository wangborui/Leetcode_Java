/*
Source: https://leetcode.com/problems/bomb-enemy/#/description
Date  : 07/08/2017
********************************************************************************
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class MaxKilledEnemies {

    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid == null || m == 0 || n == 0) {
            return 0;
        }
        int max = 0, row = 0;
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W' || grid[i][j] == 'E') {
                    continue;
                }
                if (j == 0 || grid[i][j - 1] == 'W') {
                    row = calcRow(grid, i, j);
                }
                if (i == 0 || grid[i - 1][j] == 'W') {
                    col[j] = calcCol(grid, i, j);
                }
                if (grid[i][j] == '0') {
                    max = Math.max(row + col[j], max);
                }
            }
        }
        return max;
        /*
        {{'0','E','0','0',},
         {'E','0','W','E',},
         {'0','E','0','0',},}
         */
    }

    private int calcRow(char[][] grid, int i, int j) {
        int killed = 0;
        for (; j < grid[0].length && grid[i][j] != 'W'; j++) {
            if (grid[i][j] == 'E') {
                killed++;
            }
        }
        return killed;
    }

    private int calcCol(char[][] grid, int i, int j) {
        int killed = 0;
        for (; i < grid.length && grid[i][j] != 'W'; i++) {
            if (grid[i][j] == 'E') {
                killed++;
            }
        }
        return killed;
    }

    //dynamic programming
    public int maxKilledEnemies2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        //up[i][j] max enemies killed blowing upward if bomb placed at i j
        int[][] up = new int[m][n];
        int[][] down = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        int res = 0;

        //up
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        up[i][j] = 1;
                    }
                    if (i != 0) {
                        up[i][j] += up[i - 1][j];
                    }
                }
            }
        }

        //down
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        down[i][j] = 1;
                    }
                    if (i != m - 1) {
                        down[i][j] += down[i + 1][j];
                    }
                }
            }
        }

        //left
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        left[i][j] = 1;
                    }
                    if (j != 0) {
                        left[i][j] += left[i][j - 1];
                    }
                }
            }
        }

        //right
        for (int j = n - 1; j >= 0; j--) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        right[i][j] = 1;
                    }
                    if (j != n - 1) {
                        right[i][j] += right[i][j + 1];
                    }
                }
            }
        }

        //res
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    res = Math.max(res, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] grid = {{'0', 'E', '0', '0',},
        {'E', '0', 'W', 'E',},
        {'0', 'E', '0', '0',},};
        MaxKilledEnemies ms = new MaxKilledEnemies();
        System.out.println(ms.maxKilledEnemies(grid));
        System.out.print(true || grid[-1][2] == 'W');
    }
}
