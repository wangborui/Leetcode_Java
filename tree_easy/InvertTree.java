// Source : https://leetcode.com/problems/invert-binary-tree/
// Date   : 03/11/2017

/********************************************************************************** 
 * 
 * Invert a binary tree.
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 
 * to
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * 
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 * (https://twitter.com/mxcl/status/608682016205344768)
 *
 *  | Google: 90% of our engineers use the software you wrote (Homebrew), 
 *  | but you can’t invert a binary tree on a whiteboard so fuck off.
 *               
 **********************************************************************************/

/**
 * Definition for a binary tree node.
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
public class InvertTree {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //如果这个数的根节点是空的，那么直接返回
    //先储存没有被翻转的左子树，取名temp
    //递归的去翻转右子树，翻转完成后设置为原树的左子树
    //在递归的去翻转temp， 把翻转后的结果设置为原树的右子树，返回树根
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
}
