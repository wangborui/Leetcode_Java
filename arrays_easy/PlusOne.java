/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class PlusOne {
    
    //Simple Java Solutions
//    public int[] plusOne(int[] digits) {
//        
//    int n = digits.length;
//    for(int i=n-1; i>=0; i--) {
//        if(digits[i] < 9) {
//            digits[i]++;
//            return digits;
//        }
//        
//        digits[i] = 0;
//    }
//    
//    int[] newNumber = new int [n+1];
//    newNumber[0] = 1;
//    
//    return newNumber;
//}
    //int is out of bound
//    public int[] plusOne(int[] digits) {
//        int N = digits.length -1;
//        int num = 0;
//        int[]result;
//        for(int i = 0; i <= N; i++){
//            num+= Math.pow(10,i)*digits[N-i];
//        }
//        num++;
//        String OP = Integer.toString(num);
//        if(OP.length() == N+1) result = new int[N+1];
//        else result = new int[N+2];
//        for(int i = 0; i < result.length; i++){
//            result[i] = Integer.parseInt(""+OP.charAt(i));
//        }
//        return result;
//    }
    public int[] plusOne(int[] digits) {
        int[] result = digits;
        boolean overflow = true;
        int lastIndex = digits.length - 1;
        while(overflow){
            if(lastIndex<0) return resize(result);
            if(result[lastIndex]+1 == 10){
                result[lastIndex] = 0;
            }
            else{
                result[lastIndex]++;
                overflow = false;
            }
            lastIndex--;
        }
        return result;
    }
    private int[] resize(int[]num){
        int[] p = new int[num.length+1];
        p[0] = 1;
        for(int i = 0; i < num.length; i++){
            p[i+1] = num[i];
        }
        return p;
    }
    public static void main(String [] args){
        int [] dig = {0};
         
        StdOut.println(Arrays.toString(new PlusOne().plusOne(dig)));
    }
}
