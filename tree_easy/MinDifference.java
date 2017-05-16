// Source : https://leetcode.com/problems/minimum-absolute-difference-in-bst/#/description
// Date   : 05/16/2017
/**
 * *************************************************************************************
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 *
 * Example:
 *
 * Input:
 *
 * 1
 * \
 * 3
 * /
 * 2
 *
 * Output:
 * 1
 *
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 * Note: There are at least two nodes in this BST.
 **************************************************************************************
 */
package Leetcode_Java.tree_easy;

import java.util.TreeSet;

public class MinDifference {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    TreeSet<Integer> set = new TreeSet<>();
    int min = Integer.MAX_VALUE;
    //O(n log n)  O(n) solution
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return min;
        }

        if (!set.isEmpty()) {
            if (set.floor(root.val) != null) {
                min = Math.min(min, root.val - set.floor(root.val));
            }
            if (set.ceiling(root.val) != null) {
                min = Math.min(min, set.ceiling(root.val) - root.val);
            }
        }

        set.add(root.val);

        getMinimumDifference(root.right);
        getMinimumDifference(root.left);

        return min;
    }
    private int minDiff = Integer.MAX_VALUE;
    private TreeNode prev = null;
    //O(n) O(n) solution
    public int getMinimumDifference2(TreeNode root) {
        if (root == null) {
            return minDiff;
        }
        //traverse left
        getMinimumDifference2(root.left);

        if (prev != null) {
            minDiff = Math.min(minDiff, root.val - prev.val);
        }
        prev = root;
        //traverse right
        getMinimumDifference2(root.right);

        return minDiff;
    }
}
