// Source : https://oj.leetcode.com/problems/median-of-two-sorted-arrays/
// Date   : 01/18/2017
/**
 * ********************************************************************************
 *
 * There are two sorted arrays A and B of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 *********************************************************************************
 */
package Leetcode_Java.binary_search_hard;

/**
 *
 * @author Borui Wang
 */
public class FindMedianOfSortedArrays {
 
    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //edge case checking if both arrays are null or length 0
        if((nums1 == null && nums2 == null) || (nums1.length == 0 && nums2.length == 0)) {
            return 0;
        }
        
        //edge case checking if one of the arrays is null or length 0
        //if array size is odd [1,2,3] = 3, return A[mid] 3 / 2 = 1, A[1] = 2
        //if array size is even [1,2,3,4] = 4, return (A[mid] + A[mid - 1]) / 2.0
        //  mid = 4 / 2 = 2, mid - 1 = 1, return (2 + 3) / 2.0 = 2.5
        if(nums1 == null || nums1.length == 0) {
            return nums2.length % 2 == 1 
                 ?  nums2[nums2.length / 2] 
                 : (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2.0;
        }
        
        if(nums2 == null || nums2.length == 0) {
            return nums1.length % 2 == 1 
                 ?  nums1[nums1.length / 2] 
                 : (nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2.0;
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int length = n + m;
        
        //find kth smallest in sorted array, 1 <= k <= length
        // If two arrays total length is odd, just simply return the merged array M[mid], where is mid?
        //   suppose A={ 1,3,5 } B={ 2,4 }, then A.length = 3, B.length = 2, mid = (3 + 2) / 2 = 2, M[2] = 3
        //   suppose A={ 3,5 }   B={1,2,4}, then A.length = 2, B.length = 3, mid = (2 + 3) / 2 = 2, M[2] = 3
        //   suppose A={ 1,3,4,5 }   B={2}, then A.length = 4, B.length = 1, mid = (4 + 1) / 2 = 2, M[2] = 3
        // so return merged array M[mid]
        //If two arrays total length is even, what do we return? we return their merged array (M[mid] + M[mid - 1]) / 2.0, why?
        //   suppose A={ 1,3,5,6 } B={ 2,4 }, then A.length = 4, B.length = 2, mid = (4 + 2) / 2 = 3, mid - 1 = 2, M[3] = 4, M[2] = 3
        //   suppose A={ 3,5,6 }   B={1,2,4}, then A.length = 3, B.length = 3, mid = (3 + 3) / 2 = 3, mid - 1 = 2, M[3] = 4, M[2] = 3
        //   suppose A={ 1,3,4,5 }   B={2,6}, then A.length = 4, B.length = 2, mid = (4 + 2) / 2 = 3, mid - 1 = 2, M[3] = 4, M[2] = 3
        if(length % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, length / 2 + 1);
        } else {
            return (
                   findKth(nums1, 0, nums2, 0, length / 2)
                 + findKth(nums1, 0, nums2, 0, length / 2 + 1)) 
                 / 2.0;
        }
    }
    static int findKth(int[] nums1, int oneStart, int[] nums2, int twoStart, int k) {
        if(oneStart >= nums1.length) {
            return nums2[twoStart + k - 1];
        }
        if(twoStart >= nums2.length) {
            return nums1[oneStart + k - 1];
        }
        if(k == 1) {
            return Math.min(nums1[oneStart], nums2[twoStart]);
        }
        
        //if kth element is in the range of nums1, find it
        //else remove segment nums2[twoStart : twoStart + k / 2 - 1]
        int keyOne = oneStart + k / 2 - 1 < nums1.length 
                     ? nums1[oneStart + k / 2 - 1]
                     : Integer.MAX_VALUE;
        int keyTwo = twoStart + k / 2 - 1 < nums2.length
                     ? nums2[twoStart + k / 2 - 1]
                     : Integer.MAX_VALUE;
                     
        if(keyOne < keyTwo) {
            return findKth(nums1, oneStart + k / 2, nums2, twoStart, k - k / 2);
        } else {
            return findKth(nums1, oneStart, nums2, twoStart + k / 2, k - k / 2);
        }
    }
 
    public static void main(String[] args) {
        int[]a ={1,3};
        int[]b={2};
        System.out.println(findMedianSortedArrays(a,b));
    }
}
