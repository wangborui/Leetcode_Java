/*
Source: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
********************************************************************************
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
********************************************************************************
 */
package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class LowestCommonAncestors {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //if root is null return null, if root is either p or q return root, node can be its own LCA
        if (root == null || root == p || root == q) {
            return root;
        }

        //divide
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //possible outcomes
        /*
        1. left is null, right is null: no common ancestors from left or right, return null
        2. left is null, right is not null: right node containing p, q, or both, return right
        3. left is not null, right is null: left node containing p, q, or both, return left
        4. left is not null, right is not null: left and right each contains one node, return root
         */
        //conquer
        if (left == null && right == null) {
            return null;
        } else if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        } else {
            return root;
        }
    }
}
