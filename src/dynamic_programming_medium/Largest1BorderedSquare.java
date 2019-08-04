package dynamic_programming_medium;

public class Largest1BorderedSquare {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] left = new int[m][n];
        int[][] top = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
                    top[i][j] = i > 0 ? top[i - 1][j] + 1 : 1;
                }
            }
        }

        //start with the largest 1-bordered square
        for(int l = Math.min(m, n); l > 0; l--) {
            for(int i = 0; i < m - l + 1; i++) {
                for(int j = 0; j < n - l + 1; j++) {
                    boolean leftVal = top[i + l - 1][j] >= l;
                    boolean rightVal = top[i + l - 1][j + l - 1] >= l;
                    boolean topVal = left[i][j + l - 1] >= l;
                    boolean botVal = left[i + l - 1][j + l - 1] >= l;

                    if(leftVal &&  rightVal && topVal && botVal) {
                        return l * l;
                    }
                }
            }
        }

        return 0;
    }
}
