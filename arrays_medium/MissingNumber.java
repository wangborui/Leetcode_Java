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
public class MissingNumber {
    static int missingNumber(int[] nums) {
int res = nums.length;
    for(int i=0; i<nums.length; i++){
        res ^= i;
        res ^= nums[i];
    }
    return res;
}
    public static void main(String[] args){
        System.out.println(missingNumber(new int[]{0,1,3}));
    }
}
