/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class InorderTraversal {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
     public List<Integer> inorderTraversalRecursive(TreeNode root) {
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
        list.add(root.val);
        helper(list, root.right);
        //add right
    }
    static ArrayList<Integer> inorderTraversalIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        TreeNode curt = root;
        while (curt != null || !stack.empty()) {
            //keep going down left sub-tree until null
            //this means left node is null
            while (curt != null) {
                stack.add(curt);
                curt = curt.left;
            }
            //now go to "mid" node
            //get last inserted left sub-tree node
            curt = stack.pop();
            result.add(curt.val);
            //check "right" node
            curt = curt.right;
        }
        return result;
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
        result.add(root.val);
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
        System.out.println(inorderTraversalIterative(a));
    }
}
