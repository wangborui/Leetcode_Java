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
public class SingleNumber {
     public int singleNumber(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<Integer>();
        
        for(int a: nums){
            if(set.contains(a)) set.remove(a);
            else{
                set.add(a);
            }        
        }
        int result = 0;
        for(int a: set){
            result = a;
        }
        return result;
    }
     /*
     solution fround
     */
}
