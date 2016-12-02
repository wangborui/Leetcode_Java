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
public class MoveZeros {
   public void moveZeroes(int[] nums) {
        int n = nums.length;
        if(n<=1) return;
        int k = 0;
        for(int i = 0; i < n; i++){
            if(nums[i]==0)continue;
            else{
                exch(nums,i,k++);
            }
        }
    }
    private void exch(int[]nums, int i, int k){
        int temp = nums[i];
        nums[i] = nums[k];
        nums[k] = temp;
    }
    public static void main(String[] args){
        
    }
}
