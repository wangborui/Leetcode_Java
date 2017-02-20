//Source : https://algorithm.yuanbin.me/zh-hans/dynamic_programming/longest_increasing_continuous_subsequence_ii.html
//Date   : 02/19/2017
/**
 * ******************************************************************************
 * Give you an integer matrix (with row size n, column size m)，
 * find the longest increasing continuous subsequence in this matrix.
 * (The definition of the longest increasing continuous subsequence here can start at any row or column and go up/down/right/left any direction).
 * Example Given a matrix:
 * [
 * [1 ,2 ,3 ,4 ,5],
 * [16,17,24,23,6],
 * [15,18,25,22,7],
 * [14,19,20,21,8],
 * [13,12,11,10,9]
 * ]
 * return 25
 * Challenge O(nm) time and memory.
 ******************************************************************************
 *
 *
 *
 * @author Borui Wang
 */
package Leetcode_Java.dynamic_programming_medium;
//idea is:
//
//visit each cell in the matrix, and perform dfs to find the longest continuous subsequence that can be extended from current cell
//
//when dfs visit each cell, mark each cell as visited and calculate the longest continuous subsequence that can be extended from this cell
//
//when visiting a visited cell, return pre-calculated LCS
public class LongestIncreasingContinuousSubsequence2 {

    private final int VISITED = 1;
    private int[][] distances;
    private int[][] visited;

    /**
     *
     * @param matrix
     * @return
     */
    private int longestIncreasingContinuousSubsequenceII(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 1;
        distances = new int[m][n];
        visited = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int distance = dfsHelper(i, j, matrix);
                max = Math.max(max, distance);
            }
        }
        return max;
    }
     int[] dx = {-1, 1, 0, 0};
     int[] dy = {0, 0, -1, 1};

    private int dfsHelper(int row, int col, int[][] A) {
        if (visited[row][col] == VISITED) {
            return distances[row][col];
        }

        //longest substring from current point is 1 if it cannnot extend to its neighbors
        distances[row][col] = 1;
        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if (0 <= newRow && newRow < A.length && 0 <= newCol && newCol < A[0].length) {
                if (A[newRow][newCol] < A[row][col]) {
                    distances[row][col] = Math.max(distances[row][col], dfsHelper(newRow, newCol, A) + 1);
                }
            }
        }
        visited[row][col] = VISITED;
        return distances[row][col];
    }

    public static void main(String[] args) {
        int[][] input = {{1, 2, 3, 4, 5},
                         {16, 17, 24, 23, 6},
                         {15, 18, 25, 22, 7},
                         {14, 19, 20, 21, 8},
                         {13, 12, 11, 10, 9}};
//        int[][] input = {{1, 4},
//        {2, 3}};
      //System.out.println(longestIncreasingContinuousSubsequenceII(input));

    }
}
