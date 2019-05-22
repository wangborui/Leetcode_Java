/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_easy;

/**
 *
 * @author Borui Wang
 */
public class MaxRotateFunction {
       public int maxRotateFunction(int[] A) {
        int n = A.length;
        if(n <= 0) return 0;
        int max = Integer.MIN_VALUE;
        for(int f= 0;f<n;f++){
            int sum = 0;
            for(int i= 0; i< n; i++){
                sum+=A[(f+i)%n]*i;
            }
            if(sum > max) max = sum;
        }
        return max;
    }
}
