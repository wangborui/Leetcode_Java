// Source : https://oj.leetcode.com/problems/same-tree/
// Date   : 03/11/2017

/********************************************************************************** 
* 
* Given two binary trees, write a function to check if they are equal or not.
* 
* Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
* 
*               
**********************************************************************************/

/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
package Leetcode_Java.tree_easy;

/**
 *
 * @author Borui Wang
 */
public class IsSameTree {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //如果当前两个数根都是空，那么他们一定是同一个树
    //下面来排除他们不是同一个树的可能性，因为刚刚已经说了，他们两个树当中不是都为空
    //所以如果他们两个其中有一个是空的，那么他们就不相等了，可以返回不相等
    //或者他们两个根的值不相等，两个树也不相等了，可以返回不相等
    //如果两个树的根都不为空，并且目前根的值又一样，那么我们就需要递归的查看其左子树和右子树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
}
