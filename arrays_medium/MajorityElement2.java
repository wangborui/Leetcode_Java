// Source : https://leetcode.com/problems/majority-element-ii/
// Date   : 01/10/2017
/**
 * ********************************************************************************
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 *
 *   How many majority elements could it possibly have?
 *   Do you have a better hint? Suggest it!
 *********************************************************************************
 */
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class MajorityElement2 {
//    这个题要求我们要O（1）的空间，如此的话就只能设置变量而不能用哈希表了
//    因为最多出现两个majority element 都超过总数的1/3，设置number1 和number2，count1和count2来记录这两个数字出现的次数
//    遍历整个数组，看看当前的数字是不是number1和number2里面其中的一个，如果是的话增加他们出现的次数
//    如果不是的话，看看number1和number2 是否都还未被设置，设置其中一个为当前数字
//    再不然的话就把count1和count2都减1
    
   
    static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int number1 = 0, number2 = 0, count1 = 0, count2 = 0, len = nums.length;
       
//         Inside for loop
//         check num == number1 or num == number2 before checking count for
//         example,
//         
//               number1 = 1, count1 = 0 
//               number2 = 2, count2 = 2 
//               num = 2 
//               count1 == 0,
//               therefore number1 = 2 should have count2 = 3 
//               fails test case[1,2,2,3,2,1,1,3]
         
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1) {
                count1++;
            } else if (nums[i] == number2) {
                count2++;
            } else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1) {
                count1++;
            } else if (nums[i] == number2) {
                count2++;
            }
        }
        if (count1 > len / 3) {
            result.add(number1);
        }
        if (count2 > len / 3) {
            result.add(number2);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{1, 2, 2, 3, 2, 1, 1, 3}));
    }
}
