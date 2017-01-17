/*
*Source: https://leetcode.com/problems/search-for-a-range/

********************************************************************************** 
*Inputs: sorted integer array and target value T
<T = values less than T
 T = values equal to T
>T = values greater than T

-T exists:
1. [T,...T, T], >T, >T, >T
2. <T, <T, [T,...T, T]
3. <T, <T, <T, [T,...T, T], >T, >T, >T

-T does not exist: return [-1,-1] after one search
********************************************************************************** 

*Solutions:
search for the left most index of T, if does not exist return [-1,-1]
search for the right most index of T, then return range[left, right]
-Left Search Time O(log n), SpaceO(1):
left pointer points to start of the array 0, right pointer point to end of the array n.length - 1
-Invariant: 
left pointer always point to values less than T
right values always point to values larger than or equal to T
-Loop termination:
left pointer points to largest value less than T, except when array starts with T value
right pointer points to first value larger than or equal to T, which is the left range index

-Right Search follows similar logic Time O(log n), SpaceO(1)

-Total Time(log n), Space O(1)
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class SearchRange {

    static int[] searchRange(int[] nums, int target) {
        //left range index scan
        //left pointer val less than T
        //right pointer val greater than or equal to T
        int leftRange = -1;
        int rightRange = -1;
        int left = 0;
        int right = nums.length - 1;
        //search for left index
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        //after loop termination
        //left pointer val less than T
        //right pointer first val greater than or equal to T
        //handles special case where T starts at index 0,[T,...T, T], >T, >T, >T
        if (nums[left] == target) {
            leftRange = left;
        } else if (nums[right] == target) {
            leftRange = right;
        } else {
            return new int[]{-1, -1};
        }
        left = 0;
        right = nums.length - 1;
        //right range index scan
        //left pointer val less than or equal T
        //right pointer val greater than to T
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        //after loop termination, we know target must exist in the array now
        //left pointer last val less than or equal T, which is the right range index
        //right pointer first val greater than to T
        //handle special case where last index of array is T, 2. <T, <T, [T,...T, T]
        if (nums[right] == target) {
            rightRange = right;
        } else {
            rightRange = left;
        }
        return new int[]{leftRange, rightRange};
    }

    public static void main(String[] args) {
        //test cases
        //<T, <T, <T, [T,...T, T], >T, >T, >T
        int[] result = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        System.out.println(result[0] + ":" + result[1]);
        //2. <T, <T, [T,...T, T]
        result = searchRange(new int[]{5, 7, 7, 8, 8, 8}, 8);
        System.out.println(result[0] + ":" + result[1]);
        //1. [T,...T, T], >T, >T, >T
        result = searchRange(new int[]{8, 8, 8, 9, 10}, 8);
        System.out.println(result[0] + ":" + result[1]);
        //1. [T,...T, T], >T, >T, >T
        result = searchRange(new int[]{8}, 8);
        System.out.println(result[0] + ":" + result[1]);
        // T does not exist
        result = searchRange(new int[]{1, 1, 2}, 8);
        System.out.println(result[0] + ":" + result[1]);
    }

}
