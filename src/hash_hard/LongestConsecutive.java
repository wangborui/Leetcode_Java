// Source : https://oj.leetcode.com/problems/longest-consecutive-sequence/
// Date   : 01/04/2017

/********************************************************************************** 
* 
* Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
* 
* For example,
* Given [100, 4, 200, 1, 3, 2],
* The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
* 
* Your algorithm should run in O(n) complexity.
* 
*               
**********************************************************************************/

/**
 * Analysis:
 * The most intuitive way is to sort array, then traverse array to find longest consecutive elements.
 * However, this approach take O(nlogn) time, and we have O(n) time requirement
 * 
 * The second most intuitive way is to brute force O(n^2) algorithm, as the following
 * 
 *      1) for each item num[i] in the array
 *      2) for loop search ...... num[i-2], num[i-1],  num[i]+1, num[i]+2 ......
 * 
 * However, search is really heavy, and we naturally think about HashMap with O(1) time for searching
 * 
 * So, the following solution is uses Hash Map. Each item is marked twice by HashMap, false then true, so we have O(n) time, space O(n)
 */
 
package Leetcode_Java.hash_hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int maxLength = Integer.MIN_VALUE;
        //map(number value, is searched default false)
        Map<Integer, Boolean> map = new HashMap();
        //fill out hash map
        for(int num : nums) {
            map.put(num, false);
        }
        
        for(int num : nums) {
            //the number has already been searched
            if(map.get(num) == true) {
                continue;
            } else {
                //mark num as searched
                map.put(num, true);
                int up = num + 1;
                int down = num - 1;
                
                while(map.containsKey(up)) {
                    map.put(up++, true);
                }
                while(map.containsKey(down)) {
                    map.put(down--, true);
                }
                maxLength = Math.max(maxLength, up - down - 1);
            }
        }
        return maxLength;
    }
}
