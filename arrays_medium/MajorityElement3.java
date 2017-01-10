/*
Source :
Date   : 01/10/2017
*********************************************************************************
Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.

Find it.

 Notice

There is only one majority number in the array.

Have you met this question in a real interview? Yes
Example
Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
*********************************************************************************
Solution : uses hashmap with O(n) complexity
 */
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class MajorityElement3 {
        //uses hashmap with O(n) complexity
    public int majorityElement(ArrayList<Integer> nums, int k) {
         if(nums == null || nums.size() == 0) {
            return Integer.MIN_VALUE;
        }
        //map stores at most k - 1 numbers, because there can be at most k - 1 numbers appear more than n / k times in array
        Map<Integer, Integer> map = new HashMap();
        int res = Integer.MIN_VALUE;
        for(int num : nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
            
            if(map.size() >= k) {
                reduceNRemove(map);
            }
        }
        //set count to 0 for candidates found
        for(int key : map.keySet()) {
            map.put(key, 0);
        }
        //recalculate counts of possible candidates
        for(int num : nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
        }
        //find all possible candidates with counts greater than n / k
        int maxCount = Integer.MIN_VALUE;
        for(int key : map.keySet()) {
            if(map.get(key) > maxCount) {
                maxCount = map.get(key);
                res = key;
            }
        }
        return res;
    }
    //when size of hashmap >= k, we reduce count of each candidate by 1 and remove the ones with count = 0
    private void reduceNRemove(Map<Integer, Integer> map) {
        List<Integer> deletes = new ArrayList();
        //could not directly delete the elements from map
        for(int key : map.keySet()) {
            map.put(key, map.get(key) - 1);
            if(map.get(key) == 0) {
                deletes.add(key);
            }
        }
        
        for(int delete : deletes) {
            map.remove(delete);
        }
    }
}
