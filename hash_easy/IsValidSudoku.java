/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class IsValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set [] columnSet = new HashSet[9];
        Set<Character> rowSet = new HashSet<>();
        
        for(int i = 0; i < 9; i ++){
            columnSet[i] = new HashSet<>();
        }
        for(int i = 0; i < 9; i ++){
            for(int j = 0; j < 9; j ++){
                char current = board[i][j];
                if(current != '.'){
                    if(rowSet.contains(current)) return false;
                    rowSet.add(current);
                    if(columnSet[j].contains(current)) return false;
                    columnSet[j].add(current);
                }
            }
            rowSet.clear();
        }
        return true;
        
    }
}
