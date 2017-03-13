// Source : https://oj.leetcode.com/problems/minimum-depth-of-binary-tree/
// Date   : 03/13/2017

/********************************************************************************** 
* 
* Given a binary tree, find its minimum depth.
* 
* The minimum depth is the number of nodes along the shortest path from the root node 
* down to the nearest leaf node.
*               
**********************************************************************************/
package Leetcode_Java.tree_easy;

/**
 *
 * @author Borui Wang
 */
public class MinDepth {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //这道题有一个难点在于，如果一个树有一个子树为空，那么其最短长度即是根节点加上另外一个不为空子树的长度
    //如果两边子树都为空的话，那这个二叉树的最短长度即是根节点一个
    //如果两边子树都不为空的话，那么此二叉树的最短长度即是两个子树最短的长度加1（根节点长度）
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null || root.right == null) {
            return Math.max(minDepth(root.left), minDepth(root.right)) + 1;
        } else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
}
