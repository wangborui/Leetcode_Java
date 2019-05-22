// Source : https://oj.leetcode.com/problems/sum-root-to-leaf-numbers/
// Date   : 03/12/2017

/********************************************************************************** 
* 
* Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
* An example is the root-to-leaf path 1->2->3 which represents the number 123.
* 
* Find the total sum of all root-to-leaf numbers.
* 
* For example,
* 
*     1
*    / \
*   2   3
* 
* The root-to-leaf path 1->2 represents the number 12.
* The root-to-leaf path 1->3 represents the number 13.
* 
* Return the sum = 12 + 13 = 25.
* 
*               
**********************************************************************************/
package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class SumNumbers {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfsHelper(0, root);
    }
//    这个题的难点在于记录每一次深度优先搜索树之后组合所有遍历过的节点值
//    另外强调一下这个问题是深度优先搜索不用回溯，所以可以直接返回值
    private int dfsHelper(int num, TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 10 * num + root.val;
        } else {
            int left = dfsHelper(10 * num + root.val, root.left);
            int right = dfsHelper(10 * num + root.val, root.right);
            return left + right;
        }
    }
}
