/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math_easy;

/**
 *
 * @author Borui Wang
 */
public class IsPowerOfThree {
      static boolean isPowerOfThree(int n) {
        if(n <= 0) return false;
        int base3 = (int)(Math.log(n)/Math.log(3.0));
        return Math.pow(3,base3) == n; 
    }
        public static void main(String[] args){
        //System.out.println(214748364 );
         System.out.println(isPowerOfThree(243));
    }
}
