/*
Source: https://leetcode.com/problems/binary-tree-inorder-traversal/

********************************************************************************
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
********************************************************************************

3 solutions like preorder traversal
recursive, iterative, and divide and conquer
 */
package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class PostorderTraversal {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
     public List<Integer> PostorderTraversalRecursive(TreeNode root) {
        List<Integer> list = new ArrayList();
        helper(list, root);
        return list;
    }
    private void helper(List<Integer> list, TreeNode root) {
        if(root == null) {
            return;
        }
        //add left
        helper(list, root.left);
        helper(list, root.right);
        list.add(root.val);
        //add right
    }
    
    public List<Integer> inorderTraversalDivideNConquer(TreeNode root) {
         ArrayList<Integer> result = new ArrayList<Integer>();
        // null or leaf
        if (root == null) {
            return result;
        }

        // Divide
        List<Integer> left = inorderTraversalDivideNConquer(root.left);
        List<Integer> right = inorderTraversalDivideNConquer(root.right);

        // Conquer
        result.addAll(left);
        result.addAll(right);
        result.add(root.val);
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
         
    }
}
