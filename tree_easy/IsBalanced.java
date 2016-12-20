/*
Source: https://leetcode.com/problems/balanced-binary-tree/

********************************************************************************
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
********************************************************************************

Definition of balanced tree: 
1. difference of max depth of left and right sub-tree is less than or equal to one
2. left and right sub-trees are both balanced 

Solutions:
1. Brute force: 
ensure the difference of max depth of right and left subtree is no greater than 1
ensure left subtree is balanced 
ensure right subtree is balanced
Time O(n^2): each maxDepth has time O(n), 
go down left and right sub-tree each call maxDepth once O(n), we need to go down n times
space(1) not including recursion stack

2. Divide and conquer
split tree and sub-trees into left and right branch
when returning recursion stack compare 2 branch height, return -1 if height differentiate more than 1
Time O(n) Space O(1)

 */
package Leetcode_Java.tree_easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class IsBalanced {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    
    //brute force*******************************************
    static boolean isBalancedBruteForce(TreeNode root) {
        if(root == null) {
            return true;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return (int)Math.abs(left - right) <=1 
                && isBalancedBruteForce(root.left) 
                && isBalancedBruteForce(root.right);
    }
    static int maxDepth(TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
    
    //Divide N Conquer*******************************************
    static boolean isBalancedDivideNConquer(TreeNode root) {
        return maxDepthDivideNConquer(root) != -1;
    }
    static int maxDepthDivideNConquer(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepthDivideNConquer(root.left);
        int right = maxDepthDivideNConquer(root.right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

     

    static TreeNode createTree(Integer[] nodes) {
        Queue<TreeNode> q = new LinkedList();
        int index = 0;
        TreeNode root = new TreeNode(nodes[index++]);
        q.add(root);

        while (!q.isEmpty() && index < nodes.length) {
            TreeNode temp = q.poll();

            TreeNode left = nodes[index++] == null ? null : new TreeNode(nodes[index - 1]);
            TreeNode right = nodes[index++] == null ? null : new TreeNode(nodes[index - 1]);
            temp.left = left;
            temp.right = right;
            if (left != null) {
                q.add(left);
            }
            if (right != null) {
                q.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        //create tree
        /*
         *                4
         *              /   \
         *             5     6
         *              \     \
         *               3     5
         *                    /
         *                   10
         *                    \
         *                     7
        */
        TreeNode root =  createTree(new Integer[]{4, 5, 6, null, 3, null, 5, null, null, 10, null, null, 7});
        System.out.println(isBalancedBruteForce(root));
        System.out.println(isBalancedDivideNConquer(root));
    }
}
