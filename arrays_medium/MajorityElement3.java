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
//    建立一个哈希表来储存所有的k - 1 个元素
//    遍历数组里面所有的数字，如果数字已经在哈希表当中了，把他在哈希表中的出现次数加一
//    如果当前数字不在哈希表中，把数字加到哈希表当中标记其出现次数为1。
//    检查哈希表储存数字的个数是否大于等于k，如果是的话先把哈希表所有数字出现次数减一，在删除那些出现0次的数字
//    最后再从头遍历一次整个数组，验证哈希表当中的数字确实是出现了大于n/k次
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
