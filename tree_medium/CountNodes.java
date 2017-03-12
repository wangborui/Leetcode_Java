// Source : https://leetcode.com/problems/count-complete-tree-nodes/
// Date   : 03/12/2017

/********************************************************************************** 
 * 
 * Given a complete binary tree, count the number of nodes.
 * 
 * Definition of a complete binary tree from Wikipedia:
 * http://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees
 *
 * In a complete binary tree every level, except possibly the last, is completely filled, 
 * and all nodes in the last level are as far left as possible. 
 * It can have between 1 and 2^h nodes inclusive at the last level h.
 *               
 **********************************************************************************/

package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class CountNodes {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    此题告诉了我们有一个完全二叉树，说明如果从根节点出发，一直往左走能走的距离减去一直往右走能走的距离小于等于1
//    很自然我们想到了递归的做法
//    首先计算二叉树的左边边长是多少（从根节点到最左叶子节点的距离）
//    再计算二叉树的右边边长是多少
//    如果左右边长相等的话，我们总共有2^(边长长度) - 1 个节点
//    如果不等的话我们递归计算左子树的总共节点，和右子树的总共节点，加上当前节点，就算出了总共节点数
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLeft(root);
        int right = countRight(root);
        if (left == right) {
            return (1 << left) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int countLeft(TreeNode root) {
        int count = 0;
        while (root != null) {
            count++;
            root = root.left;
        }
        return count;
    }

    private int countRight(TreeNode root) {
        int count = 0;
        while (root != null) {
            count++;
            root = root.right;
        }
        return count;
    }
}
