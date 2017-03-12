//Source: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
//Date  : 03/11/2017
/*
********************************************************************************
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
********************************************************************************

Time O(n): each number in array needs to be converted to tree node once
Space O(1):

This is an in order traversal of binary search tree
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
        if(nums == null || nums.length == 0) {
            return null;
        }
        TreeNode root = inOrderTraversal(nums, 0, nums.length - 1);
        return root;
    }
    //这道题的核心还是中序迭代遍历
    //首先找到数组的中点位置，这个位置必然是BST的根部
    //再用递归的方式分别构建BST的左子树（用数组从0到mid - 1的部分）和右子树（用数组从mid + 1 到n -1部分）
    //把当前的根节点左子树设置为构建左子树，同理右子树，再返回
    private TreeNode inOrderTraversal(int[]nums, int start, int end) {
        if(start > end) {
            return null;
        }
        
        int mid = start + (end - start) / 2;
        TreeNode left = inOrderTraversal(nums, start, mid - 1);
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode right = inOrderTraversal(nums, mid + 1, end);
        
        root.left = left;
        root.right = right;
         
        return root;
    }
}
