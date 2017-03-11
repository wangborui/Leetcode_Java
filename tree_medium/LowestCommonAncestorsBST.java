/*
Source : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
Date   : 01/19/2017
********************************************************************************
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: 
“The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6
Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Show Company Tags
Show Tags
Show Similar Problems
********************************************************************************
 */
package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class LowestCommonAncestorsBST {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //我们有三颗树，需要找到他们的最近公共祖先,大树root， p树和q树
    //首先判断他们三颗树当中有没有一个为空， 如果有的话我们直接可以返回空，不可能有哪个公共祖先是空的
    //因为这个树是bst，所以如果p树和q树都小于当前根节点的话，我们可以递归在根节点左子树去寻找
    //如果p树和q树都大于当前根节点的话，我们可以递归在根节点右子树去寻找
    //不然的话当前根节点就是最近公共祖先
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || q == null || p == null) {
            return null;
        }
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
