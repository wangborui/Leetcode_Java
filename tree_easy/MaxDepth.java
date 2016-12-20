/*
Source: https://leetcode.com/problems/maximum-depth-of-binary-tree/

********************************************************************************
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
********************************************************************************

Solution: 
traversal Recursive vs divide and conquer recursive
 */
package Leetcode_Java.tree_easy;

/**
 *
 * @author Borui Wang
 */
public class MaxDepth {
    private int maxD = 0;
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public int maxDepthDivideNConquer(TreeNode root) {
        if(root == null) {
            return 0;
        }
        //divide
        int left = maxDepthDivideNConquer(root.left);
        int right = maxDepthDivideNConquer(root.right);
        //conquer recursive 
        return Math.max(left, right) + 1;
    }
    //traversal one node at a time
    public int maxDepthTraverse(TreeNode root) {
        helper(root, 0);
        return maxD;
    }
    private void helper(TreeNode root, int depth) {
        if(root == null) {
            return;
        } 
        if(depth > maxD) {
            maxD = depth;
        }
        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }
}
