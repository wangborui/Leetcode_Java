/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_easy;
 
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Borui Wang
 */
public class ContainsNearbyDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length <= 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                int firstIndex = map.get(nums[i]);
                if(Math.abs(i-firstIndex)<=k) return true;
            }
            map.put(nums[i], i);
             }
         return false;
}
}
     
    
