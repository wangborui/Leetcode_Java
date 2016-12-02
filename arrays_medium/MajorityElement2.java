/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class MajorityElement2 {
//    static List<Integer> majorityElement(int[] nums) {
//         List<Integer> list = new ArrayList();
//         int n = nums.length;
//         if(nums == null || n == 0) return list;
//         int n1 = find(0,n/2-1,nums);
//         int n2 = find(n/2,n-1,nums);
//         int c1 = 0, c2 = 0;
//         for(int num:nums){
//             if(num == n1)c1++;
//             else if(num == n2) c2++;
//         }
//         if(c1 > n/3) list.add(n1);
//         if(n1 != n2 && c2 > n/3) list.add(n2);
//        return list;
//    }
//    static int find(int start, int end, int[]nums){
//        int candidate = nums[start];
//        int count = 0;
//        for(int i = start;i<end;i++){
//            if(count == 0){
//                candidate = nums[i];
//            }
//            if(nums[i] == candidate){
//                count++;
//            }
//            else count--;
//        }
//        return candidate;
//    }
//static List<Integer> majorityElement(int[] nums) {
//	if (nums == null || nums.length == 0)
//		return new ArrayList<Integer>();
//	List<Integer> result = new ArrayList<Integer>();
//	int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
//	for (int i = 0; i < len; i++) {
//		if (nums[i] == number1)
//			count1++;
//		else if (nums[i] == number2)
//			count2++;
//		else if (count1 == 0) {
//			number1 = nums[i];
//			count1 = 1;
//		} else if (count2 == 0) {
//			number2 = nums[i];
//			count2 = 1;
//		} else {
//			count1--;
//			count2--;
//		}
//	}
//	count1 = 0;
//	count2 = 0;
//	for (int i = 0; i < len; i++) {
//		if (nums[i] == number1)
//			count1++;
//		else if (nums[i] == number2)
//			count2++;
//	}
//	if (count1 > len / 3)
//		result.add(number1);
//	if (count2 > len / 3)
//		result.add(number2);
//	return result;
//}

static List<Integer> majorityElement(int[] nums) {
         List<Integer> res = new ArrayList();
         int n = nums.length;
         if(n == 0) return res;
         int n1 = nums[0],c1 = 0,n2 = nums[0],c2 = 0;
         //1,2,2,3,2,1,1,3
         for(int num:nums){
             if(num == n1){
                 c1++;
             }
             else if(num == n2){
                 c2++;
             }
             else if(c2 == 0){
                 n2 = num;
                 c2 = 1;
             }
             else if(c1 == 0){
                 n1 = num;
                 c1 = 1;
             }
             else{
                 c1--;c2--;
             }
         }
         c1 = 0;
         c2 = 0;
         for(int num:nums){
             if(num == n1) c1++;
             else if(num == n2) c2++;
         }
         if(c1 > n/3) res.add(n1);
         if(n2 != n1 && c2 > n/3) res.add(n2);
         return res;
    }
    public static void main(String[] args){
       System.out.println(majorityElement(new int[]{1,2,2,3,2,1,1,3}));
    }
}
