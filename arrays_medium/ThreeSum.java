// Source : https://oj.leetcode.com/problems/3sum/
// Date   : 01/12/2017

/********************************************************************************** 
* 
* Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
* Find all unique triplets in the array which gives the sum of zero.
* 
* Note:
* 
* Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
* The solution set must not contain duplicate triplets.
* 
*     For example, given array S = {-1 0 1 2 -1 -4},
* 
*     A solution set is:
*     (-1, 0, 1)
*     (-1, -1, 2)
* 
*               
**********************************************************************************/
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
/**
 *
 * @author Borui Wang
 */
/*
 *   Simlar like "Two Number" problem, we can have the simlar solution.
 *
 *   Suppose the input array is S[0..n-1], 3SUM can be solved in O(n^2) time on average by 
 *   inserting each number S[i] into a hash table, and then for each index i and j,  
 *   checking whether the hash table contains the integer - (s[i]+s[j]), or s[i]+s[j]+s[k] = 0
 *
 *   Alternatively, the algorithm below first sorts the input array and then tests all 
 *   possible pairs in a careful order that avoids the need to binary search for the pairs 
 *   in the sorted list, achieving worst-case O(n^2)


* Note: do not remove j and k duplicates before finding solution. 
* for example, nums = [0(i),0(j),0,0(k)] and find 3 pairs of numbers whose sum = 0, 
* if we remove duplicates of j and k first, we should have {null} as solution [0(i),0,0,0(k)(j)] . 
* But we have{0,0,0} as a solution
 *
 */
public class ThreeSum {
//    为了去除重复的答案，先把整个数组排序
//    遍历整个数组，选择当前元素为元素i，如果i - 1的元素等于i的元素，跳过（因为我们已经计算过了以i为开头的三元组元素了）
//    再找出i + 1的元素为j， n - 1的元素为k，while 循环查找 nums[i] + nums[j] + nums[k] 为0的三个元素
//    这里需要注意的是一旦找到了nums[i] + nums[j] + nums[k] 为0的三个元素，我们要跳过所有没有访问过的值为 nums[j] 或者 nums[k]的元素，不然答案就重复了
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        //length - 2 to avoid array overflow, because we check 2 more numbers j and k to the right of i
        for(int i = 0; i < nums.length - 2; i++) {
            //remove duplicates, will stop on the first different item
            if(i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            //find 2 numbers whose sum = -nums[i]
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0) {
                    List<Integer> list = new ArrayList();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    //remove duplicates of pointer j, will stop on the last identical item
                    while(j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    //remove duplicates of pointer k, will stop on the last identical item
                    while(j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    //move both pointers one step
                    j++;
                    k--;
                } else if(sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
     public static void main(String[] args){
         System.out.println(threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0})); 
     }
}
