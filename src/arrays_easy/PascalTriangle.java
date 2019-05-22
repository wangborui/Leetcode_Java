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
public class PascalTriangle {
    //solution found
//     static List<List<Integer>> generate(int numRows) {
//     List<List<Integer>> allrows = new ArrayList<List<Integer>>();
//	ArrayList<Integer> row = new ArrayList<Integer>();
//	for(int i=0;i<numRows;i++)
//	{
//		row.add(0, 1);
//		for(int j=1;j<row.size()-1;j++)
//			row.set(j, row.get(j)+row.get(j+1));
//		allrows.add(new ArrayList<Integer>(row));
//	}
//	return allrows;
//	
//    }
      static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> allRows = new ArrayList<List<Integer>>();
        List<Integer> row = new ArrayList<Integer>();
        for(int i = 0; i < numRows; i ++){
            row.add(0,1);
            for(int j = 1; j<i; j++){
                row.set(j, row.get(j)+row.get(j+1));
            }
            allRows.add(new ArrayList<Integer>(row));
        }
        return allRows;
    }
     public static void main(String[] args){
         StdOut.println(generate(5));
     }
}
