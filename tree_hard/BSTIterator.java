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
    //这道题需要用到中序遍历的迭代方法
    //首先我们需要把根节点的所有左边节点都放入栈当中
    //当用户call next的时候，我们需要弹出栈顶元素，把这个元素的右边元素都放入栈中，在返回当前元素
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
