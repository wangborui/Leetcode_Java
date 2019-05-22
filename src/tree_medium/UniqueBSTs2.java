/*
Source : https://leetcode.com/problems/unique-binary-search-trees-ii/
Date   : 01/19/2017
********************************************************************************
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
********************************************************************************

Follow the same logic as unique BST 1 
So, we first know that 
when n = 0 there is one way for the BST, which is null
when n = 1 there is one way for the BST, which is just n
when n = i, we need to iterate all values of n, and find total ways we can make its left subtree and right subtree, and multiply the possibilities of the two
 */
package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class UniqueBSTs2 {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //divide and conquer algo

    static List<TreeNode> generateTrees(int n) {
        //edge case checking
        if(n == 0) {
            return new ArrayList();
        }
        return helper(1, n);
    }

    static List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList();
        //when the subtree is empty
        if (start > end) {
            res.add(null);
            return res;
        }
        //generate all left and right subtrees for each root
        for (int i = start; i <= end; i++) {
            //divide
            List<TreeNode> leftSubtrees = helper(start, i - 1);
            List<TreeNode> rightSubtrees = helper(i + 1, end);

            //conquer
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
    public static void main(String [] args) {
       System.out.println(generateTrees(0));
    }
}
