/*
Source: https://leetcode.com/problems/n-queens/
********************************************************************************
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
********************************************************************************
 */
package Leetcode_Java.binary_search_hard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class NQueen {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList();
        if (n <= 0) {
            return res;
        }
        //queenCols means at index oldRow, j = queenCols.get(oldRow) , there is a queen on row oldRow col j
        solve(res, new ArrayList(), n);
        return res;
    }

    private void solve(List<List<String>> res, List<Integer> queenCols, int n) {
        //when there are n rows added, one possibility is complete add to result
        if (queenCols.size() == n) {
            res.add(drawBoard(queenCols));
            return;
        }

        for (int i = 0; i < n; i++) {
            //try placing queen at next row at col oldRow
            if (isValid(queenCols, i)) {
                queenCols.add(i);
                solve(res, queenCols, n);
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

    private List<String> drawBoard(List<Integer> cols) {
        int n = cols.size();
        List<String> res = new ArrayList();
        for (int i = 0; i < n; i++) {
            int queenPos = cols.get(i);
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(queenPos == j ? "Q" : ".");
            }
            res.add(row.toString());
        }
        return res;
    }
}
