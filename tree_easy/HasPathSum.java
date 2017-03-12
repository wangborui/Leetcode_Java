// Source : https://oj.leetcode.com/problems/path-sum/
// Date   : 03/12/2017

/********************************************************************************** 
* 
* Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
* such that adding up all the values along the path equals the given sum.
* 
* For example:
* Given the below binary tree and sum = 22,
* 
*               5
*              / \
*             4   8
*            /   / \
*           11  13  4
*          /  \      \
*         7    2      1
* 
* return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*               
**********************************************************************************/
package Leetcode_Java.tree_easy;

/**
 *
 * @author Borui Wang
 */
public class HasPathSum {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    首先判断当前节点是否为空，如果是的话肯定没有能组成的路径和，返回false
//    再判断当前节点是否是叶子节点，即两个孩子节点都为空，再判断当前节点的值是否是我们要找的和
//    如果上述条件都不符合，我们分别递归的判断此节点的左右子树是否包含sum - root.val
            
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
