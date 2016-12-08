/*
Source:https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
similar analysis with RemoveElementFromSortedArray
 */
package Leetcode_Java.arrays_easy;

/**
 *
 * @author Borui Wang
 */
public class RemoveElementFromSortedArray2 {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        //i and j starts at 1 because the first element could not be duplicate of itself
        int j = 1;
        //number of times duplicates repeats
        int rep = 0;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]) {
                rep++;
                if(rep <= 1) {
                    nums[j++] = nums[i];
                }
            } else {
                nums[j++] = nums[i];
                //clears repetition count when encounters a different number
                rep = 0;
            }
        }
        return j;
    }
}
