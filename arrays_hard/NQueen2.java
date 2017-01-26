// Source : https://oj.leetcode.com/problems/n-queens-ii/
// Date   : 01/26/2017

/********************************************************************************** 
* 
* Follow up for N-Queens problem.
* 
* Now, instead outputting board configurations, return the total number of distinct solutions.
* 
*               
**********************************************************************************/
package Leetcode_Java.arrays_hard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class NQueen2 {
    //can be solved the same way as n queens 1
    private int ways = 0;
    public int totalNQueens(int n) {
        if (n <= 0) {
            return ways;
        }
        //queenCols means at index oldRow, j = queenCols.get(oldRow) , there is a queen on row oldRow col j
        solve(new ArrayList(), n);
        return ways;
    }

    private void solve(List<Integer> queenCols, int n) {
        //when there are n rows added, one possibility is complete add to result
        if (queenCols.size() == n) {
            ways++;
            return;
        }

        for (int i = 0; i < n; i++) {
            //try placing queen at next row at col oldRow
            if (isValid(queenCols, i)) {
                queenCols.add(i);
                solve(queenCols, n);
                queenCols.remove(queenCols.size() - 1);
            }
        }

    }

    private boolean isValid(List<Integer> currentQueens, int nextQueenCol) {
        int rows = currentQueens.size();
        for (int oldRow = 0; oldRow < rows; oldRow++) {
            //1.check if next queen has columns conflicts with other queens
            //2.no need to check row conflicts because we use backtracking to check one position in a row at a time
            int oldCol = currentQueens.get(oldRow);
            if (oldCol == nextQueenCol) {
                return false;
            }
            // to check the diagonal, we just check |x'-x| == |y'-y|, (x',y') is a Queen will be placed
            if(Math.abs(rows - oldRow) == Math.abs(nextQueenCol - oldCol)) {
                return false;
            }
        }
        return true;
    }
}
