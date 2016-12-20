/*
Source: https://leetcode.com/problems/binary-tree-preorder-traversal/

********************************************************************************
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
********************************************************************************

Solution:
1. Recursion
add each root value to the result list, and recurse left sub-tree then right
2. Iterative
using stack and array list and helper data structures
3. Divide and conquer
 */
package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class PreorderTraversal {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> list = new ArrayList();
        helper(root, list);
        return list;
    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        helper(root.left, list);
        helper(root.right, list);
    }

    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> list = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp != null) {
                list.add(temp.val);
                stack.push(temp.right);
                stack.push(temp.left);
            }
        }
        return list;
    }
    static ArrayList<Integer> preorderTraversalDivideNConquer(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        // null or leaf
        if (root == null) {
            return result;
        }

        // Divide
        List<Integer> left = preorderTraversalDivideNConquer(root.left);
        List<Integer> right = preorderTraversalDivideNConquer(root.right);

        // Conquer
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }
    public static void main(String[] args) {
        /*create tree
         *      1
         *     / \
         *    3   7
         *  /   /  \
         * 6   8    10
         *
        */
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(7);
        TreeNode d = new TreeNode(6);
        TreeNode e = new TreeNode(8);
        TreeNode f = new TreeNode(10);
        
        a.left = b;
        a.right = c;
        b.left = d;
        c.left = e;
        c.right = f;
        System.out.println(preorderTraversalDivideNConquer(a));
    }
}
