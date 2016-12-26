/*
Source: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
********************************************************************************
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
********************************************************************************

Time O(n): each number in array needs to be converted to tree node once
Space O(1):
 */
package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class SortedArrayToBST {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode root = helper(nums, 0, nums.length - 1);
        return root;
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.right = helper(nums, mid + 1, end);
        root.left = helper(nums, start, mid - 1);
        return root;
    }
}
