/*
Source: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
********************************************************************************
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: 
“The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6       2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
********************************************************************************

Note: 
1. This question assumes gives nodes p and q, which are always unique, because p and q are part of the tree
assume p is the node 5, then when traversing the tree and we get to node 5, p == cur is true, but any other node with value 5 is false p != cur
we are comparing objects, not values in objects

2.Additionally, if the tree is the following with p = 5 and q = 1 node, then LCA is 3
        _______3______
       /              \
    ___5(p)__          ___1(q)__
   /      \        /      \
   6       2       0       8
         /  \
         7   4

3.Given tree like this , p = 1, q = 5, the LCA is 1 not 3
        _______3______
       /              \
    ___5__          ___1(p)__
   /      \        /      \
   6       2       0       5(q)
         /  \
         7   4
Time O(n), space O(1) not including call stack
 */
package Leetcode_Java.tree_medium;

import java.util.LinkedList;
import java.util.Queue;

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

    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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
    public static void main(String[] args){
        TreeNode root = createTree(new Integer[]{3,5,1,6,33,0,5,null,null,7,9});
        TreeNode res = lowestCommonAncestor(root,root.right, root.right.right);
        System.out.println(res.val);
     }
}
