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
public class HammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        for(int i = 0; i < 32; i++){
            count = (n & 1) == 1?count+1:count;
            n = n>>1;
        }
        return count;
    }
    public static void main(String[] args){
        System.out.println(9&1);
    }
}
