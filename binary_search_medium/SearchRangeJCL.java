/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class SearchRangeJCL {

    static int[] searchRange(int[] nums, int target) {
        int pos = binarySearch(nums, 0, nums.length - 1, target);
        int left = -1;
        int right = -1;
        if(pos != -1) {
            //search left range index
            left = pos;
            int l = left;
             do {
                left = l;
                l = binarySearch(nums, 0, left - 1, target); 
            } while(l != -1);
            //search right range index
            right = pos;
            int r = right;
            do {
                right = r;
                r = binarySearch(nums,  right + 1, nums.length - 1, target);
            } while(r != -1);
        }
        return new int[]{left , right};
    }
    static int binarySearchJCL(int [] a, int start, int end, int target) {
        //start - 1 is always less than target
// end + 1 is always larger than target
//use <=, when end could be target, < when end could NOT be target
//example {5,6} target 6
        while(start <= end) {
            int mid = start + (end - start) / 2; 
            if(a[mid] == target) {
                return mid;
            } else if(a[mid] < target) {
                //use mid + 1, if start + 1 = end, mid = start, mid will always be start, therefore use start = mid + 1 to break out of loop condition start <= end
                start = mid + 1;
            } else {
                //end = mid or end = mid - 1 is not the same thing, 
                //end = mid, if start and end are far away, mid will be in the middle, else if start + 1 = end, mid will be start, and if a[mid] > target again, end will move to position of start, loop will never end
                //example, {4,5} target 3
                //end = mid - 1, if start and end are far away, mid will be in the middle, else if start + 1 = end, mid will be start, and if a[mid] > target again, end will move to position start - 1, ending loop
                //example, {4,5} target 3
                end = mid - 1;
            }
        }
        return -1;
    }
    static int binarySearch(int [] a, int start, int end, int target) {
        //start is less than mid, end is greater than mid
        //<,<,<,<,start, end,>,>,>
        //exception either start == target or end == target, or start = end to start with
        //this template will fail if start is less than 0 or end is greater than n - 1 when target = start or target = end
        if(start > end) {
            return -1;
        }
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(a[mid] == target) {
                return mid;
            } else if(a[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(a[start] == target) {
            return start;
        } else if(a[end] == target) {
            return end;
        } else {
            return -1;
        }
    }
    public static void main(String [] args) {
        //target 8, loop terminates on "mid" values;
        int [] a = {5, 7, 7, 8, 9, 10};
         //target 10, loop terminates, and "end" value is returned
        int [] b = {5, 5, 5, 8, 8, 10};
        //target 5, loop terminates on "mid" values;
        int [] c = {5, 5, 5, 5, 5, 5};
        //target  5, loop terminates, and "start" value is returned
        int [] d = {5, 6, 6, 8, 8, 10};
        //target 11, loop terminates and -1 is returned
        int [] e = {5, 6, 6, 8, 8, 10};
        //target 4, loop terminates and -1 is returned
        int [] f = {5, 6, 6, 8, 8, 10};
//        System.out.println(binarySearch(a, 0, a.length - 1, 8));
//        System.out.println(binarySearch(b, 0, b.length - 1, 10));
//        System.out.println(binarySearch(c, 0, c.length - 1, 5));
//        System.out.println(binarySearch(d, 0, d.length - 1, 5));
//        System.out.println(binarySearch(e, 0, e.length - 1, 11));
//        System.out.println(binarySearch(f, 0, f.length - 1, 4));
        
        System.out.println(searchRange(a, 8)[0] + ":" + searchRange(a, 8)[1]);
        System.out.println(searchRange(b, 10)[0] + ":" + searchRange(b, 10)[1]);
        System.out.println(searchRange(c, 5)[0] + ":" + searchRange(c, 5)[1]);
        System.out.println(searchRange(d, 5)[0] + ":" + searchRange(d, 5)[1]);
    }
}
