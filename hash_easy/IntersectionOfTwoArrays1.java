/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class IntersectionOfTwoArrays1 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0) return new int[0];
        
        Set<Integer> nums1Set = new HashSet<Integer>();
        Set<Integer> resultSet = new HashSet<Integer>();
        
        for(int num1: nums1){
            nums1Set.add(num1);
        }
        for(int num2: nums2){
            if(nums1Set.contains(num2)){
                resultSet.add(num2);
            }
        }
        
        int []result = new int[resultSet.size()];
        int index = 0;
        for(int temp: resultSet){
            result[index++] = temp;
        }
        return result;
    }
}
