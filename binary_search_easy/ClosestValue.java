//Source: https://leetcode.com/problems/closest-binary-search-tree-value/#/description
//Date  : 04/05/2017
/**
 * ********************************************************************************
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 *********************************************************************************
 */
package Leetcode_Java.binary_search_easy;

/**
 *
 * @author Borui Wang
 */
public class ClosestValue {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    通过二分的方式查找和target值最接近的节点值
//    如果当前值大于target值，那么我们要递归的查找其左子树
//    如果当前值小于target值，那么我们要递归的查找其右子树
    public int closestValue(TreeNode root, double target) {
        int val = root.val;
        TreeNode child = target < val ? root.left : root.right;
        if (child == null) {
            return val;
        } else {
            int sub = closestValue(child, target);
            return Math.abs(val - target) < Math.abs(sub - target) ? val : sub;
        }
    }
}
