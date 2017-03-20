// Source : https://oj.leetcode.com/problems/word-search/
// Date   : 03/20/2017
/**
 * ********************************************************************************
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * For example,
 * Given board =
 *
 * [
 *   ["ABCE"],
 *   ["SFCS"],
 *   ["ADEE"]
 * ]
 *
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 *
 *********************************************************************************
 */
package Leetcode_Java.trie_hard;

/**
 *
 * @author Borui Wang
 */
public class WordSearch {
    //访问一个2D 矩阵的所有节点，在每次访问节点的时候，用深度优先搜索查找四个方向是否有需要查找的关键词
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean isFound = dfsSearch(i, j, board, word);
                if (isFound) {
                    return true;
                }
            }
        }
        return false;
    }
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    private boolean dfsSearch(int i, int j, char[][] board, String word) {
        if (word.length() == 0) {
            return true;
        }
        //check if i and j are out of bound, board position is unvisited, the current char matches first char in word
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 0 || board[i][j] != word.charAt(0)) {
            return false;
        }
        char original = board[i][j];
        boolean found = false;
        board[i][j] = 0;
        for (int s = 0; s < 4; s++) {
            found = found || dfsSearch(i + dx[s], j + dy[s], board, word.substring(1));
        }
        board[i][j] = original;
        return found;
    }
}
