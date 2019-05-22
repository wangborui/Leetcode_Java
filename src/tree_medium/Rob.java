// Source : https://leetcode.com/problems/house-robber-iii/
// Date   : 03/12/2017

/*************************************************************************************** 
 *
 * The thief has found himself a new place for his thievery again. There is only one
 * entrance to this area, called the "root." Besides the root, each house has one and
 * only one parent house. After a tour, the smart thief realized that "all houses in
 * this place forms a binary tree". It will automatically contact the police if two 
 * directly-linked houses were broken into on the same night.
 * 
 * Determine the maximum amount of money the thief can rob tonight without alerting the
 * police.
 * 
 * Example 1:
 *     3
 *    / \
 *   2   3
 *    \   \ 
 *     3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *     3
 *    / \
 *   4   5
 *  / \   \ 
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 * Credits:
 * Special thanks to @dietpepsi for adding this problem and creating all test cases.
 * 
 ***************************************************************************************/
package Leetcode_Java.tree_medium;

import java.util.HashMap;

/**
 *
 * @author Borui Wang
 */
public class Rob {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    我们其实有两种情况需要考虑
//            1.）抢当前节点，跳过一层，抢下下层的所有节点，跳过一层，抢下下下下层的所有节点...
//            2.）不抢当前节点，抢下一层的所有节点，跳过一层，抢所有节点，跳过一层，抢所有节点...
//    对比1和2哪个值最大就是我们的答案
//    但是我们重复计算了很多层的东西，所以我们需要加一个哈希表来记录已经访问过的东西和节点
  public int rob(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int val = root.val;
        
        //rob skip one level, and rob the next level
        if(root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if(root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }
        
        return Math.max(rob(root.left) + rob(root.right), val);
    }
    public int rob2(TreeNode root) {
        HashMap<TreeNode, Integer> map = new HashMap();
        return robSub(root, map);
    }

    private int robSub(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }

        int val = root.val;
        //rob skip one level, and rob the next level
        if (root.left != null) {
            val += robSub(root.left.left, map) + robSub(root.left.right, map);
        }
        if (root.right != null) {
            val += robSub(root.right.left, map) + robSub(root.right.right, map);
        }
        val = Math.max(robSub(root.left, map) + robSub(root.right, map), val);
        map.put(root, val);
        return val;
    }
}
