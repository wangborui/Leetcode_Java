// Source : https://leetcode.com/problems/summary-ranges/
// Date   : 03/25/2017

/********************************************************************************** 
 * 
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * 
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 *               
 **********************************************************************************/
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class SummaryRange {
//    扫描一遍数组，使用一个变量叫left，另外一个变量叫right
//    刚刚开始的时候让left和right都指向当前遍历元素。
//    如果right的下一个元素比right 大1 就让right一直往后走，直到right的下一个元素-right >1
//    对比left和right看看他们是否相同，如果不同的话就把range 加入结果里面
    
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList();
        if (nums == null || nums.length == 0) {
            return res;
        }

        int n = nums.length;
        int i = 0;
        int left = 0;
        int right = 0;

        while (i < n) {
            left = nums[i];
            right = left;

            while (i < n - 1 && nums[i + 1] == right + 1) {
                right = nums[++i];
            }
            //right hasn't moved at all
            if (left == right) {
                res.add("" + left);
            } else {
                res.add(left + "->" + right);
            }
            i++;
        }
        return res;
    }
}
