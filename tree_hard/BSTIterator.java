/*
Source: https://leetcode.com/problems/binary-search-tree-iterator/
Date: 12/21/2016

**********************************************************************************
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
**********************************************************************************

Solution: using inorder traversal
 */
package Leetcode_Java.tree_hard;

import java.util.Stack;

public class BSTIterator {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack();
        TreeNode cur = root;
        //inorder iteratvie traversal
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode cur = stack.pop();
        int val = cur.val;
        cur = cur.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return val;
    }
}
