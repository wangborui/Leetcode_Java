package two_pointers_medium;

/**
 * Given and array of integers, return an integer denoting the maximum difference between any pair of elements in the
 * array. Such that i < j, and a[j] > a[i], and if no such number exists(all elements in array is in descending order
 * a[j] < a[i]), return -1 instead.
 */
public class MaxDiffArray {
    public int maxDiff(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int diff = 0;
        int n = nums.length;
        int j = 0;

        for (int i = 0; i < n; i++) {
            diff = Math.max(diff, nums[i] - nums[j]);
            if (i + 1 < n && nums[i + 1] < nums[i]) {
                j = i + 1;
            }
        }

        return diff == 0 ? -1 : diff;
    }

    public static void main(String[] args) {
        MaxDiffArray maxDiffArray = new MaxDiffArray();
        int[] test1 = {2, 3, 10, 2, 4, 8, 1};
        int res = maxDiffArray.maxDiff(test1);
        System.out.println(res);
    }
}
