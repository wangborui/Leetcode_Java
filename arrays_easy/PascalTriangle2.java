/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class PascalTriangle2 {
    static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
         
        for(int i = 0; i <= rowIndex; i ++){
            row.add(0,1);
            for(int j = 1; j<i;j++){
                row.set(j, row.get(j)+row.get(j+1));
            }
        }
        return row;
    }
    public static void main(String [] args){
        StdOut.println(getRow(5));
    }
}
