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

    static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
       
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
