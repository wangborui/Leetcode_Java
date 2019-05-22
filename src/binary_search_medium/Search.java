// Source : https://oj.leetcode.com/problems/search-in-rotated-sorted-array/
// Date   : 02/24/2017

/********************************************************************************** 
* 
* Suppose a sorted array is rotated at some pivot unknown to you beforehand.
* 
* (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
* 
* You are given a target value to search. If found in the array return its index, otherwise return -1.
* 
* You may assume no duplicate exists in the array.
*               
**********************************************************************************/
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class Search {
/*
 *    Using binary search idea, 
 *    1) Spliting the array to two part, one part should be non-rotated, another one is rotated.
 *    2) Checking the "key" whether is possible in non-rotated sorted part.
 *        2.1) if it is, then go to the classcial binary searh.
 *        2.2) if it not, then keep spliting the rorated part.
 *
 */
    public int search(int[] nums, int target) {
        int s = 0, e = nums.length - 1;
        while (s + 1 < e) {
            int mid = (s + e) >>> 1;

            if (nums[s] <= nums[mid]) {
                //on the first rising half
                if (nums[s] <= target && target <= nums[mid]) {
                    e = mid;
                } else {
                    s = mid;
                }
            } else //on the second rising half
            if (nums[mid] <= target && target <= nums[e]) {
                s = mid;
            } else {
                e = mid;
            }
        }
        if (nums[s] == target) {
            return s;
        } else if (nums[e] == target) {
            return e;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        //System.out.println(bin_search(new int[]{0,1,2,3,5},0,4,4));
        /* System.out.println(  Integer.MAX_VALUE );
        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE  )) + " size: "+ Integer.toBinaryString((Integer.MAX_VALUE  )).length());
         System.out.println(  (Integer.MAX_VALUE +1 ) );
        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE +1 ) ) + " size: "+ Integer.toBinaryString((Integer.MAX_VALUE +1 ) ).length());
        System.out.println(  (Integer.MIN_VALUE  ) );
        System.out.println(Integer.toBinaryString((Integer.MIN_VALUE   ) ) + " size: "+ Integer.toBinaryString((Integer.MIN_VALUE   ) ).length());*/
        System.out.println(Integer.toBinaryString(-1).length());
        System.out.println(Integer.toBinaryString(-1 - 1));
//        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE +2 )>>>1) + " size: "+ Integer.toBinaryString((Integer.MAX_VALUE +2 )>>>1).length());
//        System.out.println(  (Integer.MAX_VALUE +50 )>>>1);
//        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE +50 )>>>1) + " size: "+ Integer.toBinaryString((Integer.MAX_VALUE +50 )>>>1).length());

//        System.out.println((Integer.MAX_VALUE +3)>>>1 );
//        System.out.println((Integer.MAX_VALUE +4) );
//        System.out.println((Integer.MAX_VALUE +5) );
//        System.out.println((Integer.MAX_VALUE + 1)>>>1);
//        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE + 1)));
//        System.out.println((Integer.MAX_VALUE + 1)/2);
//        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE + 1)/2) );
//         System.out.println(Integer.toBinaryString((Integer.MAX_VALUE + 1)>>>1) );
    }
}
