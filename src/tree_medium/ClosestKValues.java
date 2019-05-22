// Source : https://leetcode.com/problems/path-sum-iii/#/description
// Date   : 03/15/2017
/**
 * ********************************************************************************
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 *
 * Note:
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 *
 * Hint:
 *
 * Consider implement these two helper functions:
 * getPredecessor(N), which returns the next smaller node to N.
 * getSuccessor(N), which returns the next larger node to N.
 * Try to assume that each node has a parent pointer, it makes the problem much easier.
 * Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
 * You would need two stacks to track the path in finding predecessor and successor node separately.
 *
 *********************************************************************************
 */
package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class ClosestKValues {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    中序遍历（左，中，右）一个二叉查找树可以获得所有节点从小到大的排列顺序
//    逆中序遍历（右，中，左）一个二叉查找树可以获得所有节点从大到小的排列顺序
//    用一个栈来记录中序遍历结果，当当前访问元素大于target的时候停止访问
//    用另外一个栈来记录逆中序遍历结果，当当前访问元素小于target的时候停止访问
//    合并两个栈的元素，直到合并结果大于k
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList();
        if (root == null) {
            return res;
        }

        Stack<Integer> pred = new Stack();
        Stack<Integer> suc = new Stack();

        inorder(root, target, pred, false);
        inorder(root, target, suc, true);

        while (k-- > 0) {
            if (pred.isEmpty()) {
                res.add(suc.pop());
            } else if (suc.isEmpty()) {
                res.add(pred.pop());
            } else if (Math.abs(pred.peek() - target) < Math.abs(suc.peek() - target)) {
                res.add(pred.pop());
            } else {
                res.add(suc.pop());
            }
        }
        return res;
    }

    private void inorder(TreeNode root, double target, Stack<Integer> stack, boolean isReverse) {
        if (root == null) {
            return;
        }
        inorder(isReverse ? root.right : root.left, target, stack, isReverse);

        if (isReverse) {
            if (root.val > target) {
                stack.push(root.val);
            } else {
                return;
            }
        } else if (root.val <= target) {
            stack.push(root.val);
        } else {
            return;
        }

        inorder(isReverse ? root.left : root.right, target, stack, isReverse);
    }
}
