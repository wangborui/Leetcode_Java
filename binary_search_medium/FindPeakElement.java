/*
2 similar questions:
case 1:
 this question condition -infinity{0- - - - -numbers}-infinity
so we set s = 0, e = n - 1, and when mid go out of bound we set it to -inifity 

case 2: 
The numbers in adjacent positions are different.
A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
we need to set s  = 1, e = n - 2 to avoid overflow
time O(log n) space O(1)

Another clever time O(n)

public int findPeakElement(int[] nums) {
        for(int i = 1; i < nums.length;i++){
            if(nums[i] < nums[i-1])
                return i-1;
        }
        return nums.length - 1;
    }

 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class FindPeakElement {
    static int findPeakElement(int[] nums) {
        int s = 0, e = nums.length - 1;
        while(s + 1 < e){
            int mid = (s + e) >>> 1;
            //made a mistake  <= 0, should be  >= 0
            int prev = (mid - 1) >= 0 ? nums[mid-1] : Integer.MIN_VALUE;
            if(nums[mid] < prev) {
                e = mid;
            }
            else {
                s = mid;
            }
        }
        if(nums[s] < nums[e]) {
            return e;
        }
        else {
            return s;
        }
    }
    public static void main(String [] args){
        System.out.println(findPeakElement(new int[]{1,2}));
    }
}
