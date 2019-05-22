package Leetcode_Java.tree_medium;

// Source : https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/#/description
// Date   : 03/13/2017

/**
 * ********************************************************************************
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * For example:
 * Given binary tree,
 * 5
 * / \
 * 1   5
 * / \   \
 * 5   5   5
 * return 4.
 *********************************************************************************
 */
/**
 *
 * @author Borui Wang
 */
public class LongestConsecutive {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    private int longest = 0;
    //这个题的难点在于使用一个local变量记录已经访问过的连续节点，一个全局变量来记录总体最长连续节点
    //深度优先搜索
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return longest;
        }
        dfsHelper(1, null, root);
        return longest;
    }

    private void dfsHelper(int conSize, TreeNode prev, TreeNode root) {
        longest = Math.max(longest, conSize);
        if (root == null) {
            return;
        } else {
            if (prev != null && prev.val + 1 == root.val) {
                conSize++;
            } else {
                conSize = 1;
            }
            dfsHelper(conSize, root, root.left);
            dfsHelper(conSize, root, root.right);
        }
    }
}
