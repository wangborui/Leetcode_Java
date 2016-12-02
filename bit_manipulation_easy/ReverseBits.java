/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bit_manipulation_easy;

/**
 *
 * @author Borui Wang
 */
public class ReverseBits {
    static int reverseBits(int n) {
        int rev = 0;
       for(int i =0 ; i < 32; i++){
           rev<<=1;
           rev = rev + (n&1);
           n>>=1;
       }
       return rev;
    }
     public static void main(String[] args){
        System.out.println(reverseBits(1 ));
    }
}
