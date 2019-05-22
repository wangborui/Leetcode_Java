// Source : https://leetcode.com/problems/island-perimeter/#/description
// Date   : 05/21/2017
/**
 * ********************************************************************************
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * Grid cells are connected horizontally/vertically (not diagonally).
 * The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1.
 * The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 * Example:
 *
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 *
 * Answer: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 *
 *********************************************************************************
 */
package Leetcode_Java.hash_easy;

/**
 *
 * @author Borui Wang
 */
public class IslandPerimeter {

    private final int VISITED = 2;
    private final int[] dx = {-1, 1, 0, 0};
    private final int[] dy = {0, 0, -1, 1};

    /**
     * DFS traversal
     *
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int perim = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    perim = helper(grid, i, j);
                    break;
                }
            }
        }
        return perim;
    }

    private int helper(int[][] grid, int i, int j) {
        int perim = 0;
        if (grid[i][j] == VISITED) {
            return perim;
        }
        //mark as visited
        grid[i][j] = VISITED;

        for (int d = 0; d < 4; d++) {
            int updatedX = i + dx[d];
            int updatedY = j + dy[d];
            if (updatedX < 0 || updatedX >= grid.length
                    || updatedY < 0 || updatedY >= grid[0].length
                    || grid[updatedX][updatedY] == 0) {
                perim++;
            } else {
                perim += helper(grid, updatedX, updatedY);
            }
        }
        return perim;
    }

    //smart solution counting the number of islands and the "down" and "right" neighbor of each island
    //finally return islands * 4 - neighbor * 2
    public int islandPerimeter2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int land = 0;
        int neighbors = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    land++;
                    //count down neighbors
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                        neighbors++;
                    }
                    //count right neighbors
                    if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
                        neighbors++;
                    }
                }
            }
        }

        return land * 4 - neighbors * 2;
    }

    public static void main(String[] args) {
        //[[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//        int[][] grid = {{0,1,0,0},
//                        {1,1,1,0},
//                        {0,1,0,0},
//                        {1,1,0,0}};
        int[][] grid = {{1, 0}};
        //System.out.println(islandPerimeter(grid));
    }
}
