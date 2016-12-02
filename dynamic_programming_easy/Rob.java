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
public class Rob {
    //first try
//    static int rob(int[] nums) {
//        int n = nums.length;
//        int [] result = new int[n];
//        return rob(nums,result,n-1);
//    }
//    static int rob(int [] nums, int[] result, int start){
//        if(start < 0) return 0;
//        result[start] = (int)Math.max(rob(nums, result, start-2)+nums[start], rob(nums,result,start-1));
//        return result[start];
//    }
    //my working solutions
    static int rob(int[] nums) {
       int n = nums.length;
        if(n==0) return 0;
        if(n==1) return nums[0];
        if(n==2) return Math.max(nums[0], nums[1]);
        int [] result = new int[n+1];
        result[0] = 0;
        result[1] = nums[0];
        result[2] = Math.max(nums[0], nums[1]);
        for(int i = 3; i <= n;i++){
            result[i] = Math.max(result[i-2]+nums[i-1], result[i-1]);
        }
        return result[n];
        
    }
    public static void main(String[] args){
        System.out.println(rob(new int[]{3,1,5,8,0,4,12}));
    }
}
