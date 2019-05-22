/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class RangeAddition {
    static int[] getModifiedArray(int length, int[][] updates) {

    int[] res = new int[length];
     for(int[] update : updates) {
        int value = update[2];
        int start = update[0];
        int end = update[1];
        
        res[start] += value;
        
        if(end < length - 1)
            res[end + 1] -= value;
        
    }
    
    int sum = 0;
    for(int i = 0; i < length; i++) {
        sum += res[i];
        res[i] = sum;
    }
    
    return res;
}
    public static void main(String[] args){
        getModifiedArray(5,new int[][]{{1,  3,  2},
                                        {2,  4,  3},
                                        {0,  2, -2},});
        int x  =1; int y = 1;
        x^=y;
        y^=x;
        x^=y;
        System.out.println(y);
        
    }
}
