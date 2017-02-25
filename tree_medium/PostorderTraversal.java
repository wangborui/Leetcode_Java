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
    
    static List<Integer> inorderTraversalIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> res = new ArrayList();
        //stack is empty in the beginning of traversal
        //cur is null after traversing all left nodes 
        while(!stack.isEmpty() || root != null) {
            // keep going the left
            if(root != null) {
                if(root.right != null) {
                    stack.push(root.right);
                }
                stack.push(root);
                root = root.left;
            } else {
                // top could be left node or top node with its right node in stack top
                TreeNode top = stack.pop();
                //if top is the last node in stack, after pop dont peek stack top
                if(!stack.isEmpty() && top.right == stack.peek()) {
                    //traverse right sub-tree of top 
                    root = stack.pop();
                    //push top into stack the second time
                    stack.push(top);
                } else {
                    res.add(top.val);
                }
            }
        }
        return res;
    }
   
    /*  tree                  traverse mid, right, left using one stack and push result into postorder stack
    pop postorder stack and add results into res list
         *      1
         *     / \
         *    3   7
         *  /   /  \
         * 6   8    10
         *
      */
    
    static List<Integer> inorderTraversalTwoStacks(TreeNode root) {
        List<Integer> res = new ArrayList();
        Stack<TreeNode> reverseOrder = new Stack();
        Stack<TreeNode> postOrder = new Stack();
        TreeNode cur = root;
        
        while(!reverseOrder.isEmpty() || cur != null) {
            if(cur != null) {
                postOrder.push(cur);
                reverseOrder.push(cur);
                cur = cur.right;
            } else {
                TreeNode temp = reverseOrder.pop();
                cur = temp.left;
            }
        }
        
        while(!postOrder.isEmpty()) {
            res.add(postOrder.pop().val);
        }
        return res;
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
        System.out.println(inorderTraversalTwoStacks(a));
    }
}
