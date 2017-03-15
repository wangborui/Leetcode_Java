// Source : https://leetcode.com/problems/find-bottom-left-tree-value/#/submissions
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
 *********************************************************************************/
package Leetcode_Java.tree_medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class FindBottomLeftValue {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    用level order traversal的方式，但是在访问每一层的时候先访问其右子树，在访问其左子树
//    这样的话在访问完毕整颗树的时候，最后一个访问的节点就是我们想要的节点
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
            root = q.poll();
            if (root.right != null) {
                q.add(root.right);
            }
            if (root.left != null) {
                q.add(root.left);
            }
        }
        return root.val;
    }
}
