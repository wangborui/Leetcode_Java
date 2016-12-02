/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_programming_easy;

/**
 *
 * @author Borui Wang
 */
public class NumArray {
    private int []  sums;
    public NumArray(int[] nums) {
        sums = new int[nums.length];
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum+=nums[i];
            sums[i]= sum;
        }
    }

    public int sumRange(int i, int j) {
        return i == 0?sums[j]:sums[j]-sums[i-1];
    }
    public static void main(String[] args){
        NumArray n = new NumArray(new int[]{-2,0,3,-5,2,-1});
        System.out.println(n.sumRange(5, 5));
    }
}
