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
    public ArrayList<Integer> inorderTraversalIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        TreeNode curt = root;
        while (curt != null || !stack.empty()) {
            while (curt != null) {
                stack.add(curt);
                curt = curt.left;
            }
            curt = stack.peek();
            stack.pop();
            result.add(curt.val);
            curt = curt.right;
        }
        return result;
    }
}
