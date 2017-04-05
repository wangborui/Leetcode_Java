/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
//从下至上的搜索二叉查找树，耗费时间O(n)
//先分治的查找左子树和右子树的最大二叉查找树，
//如果左右两边都分别是二叉查找树，那么由当前根节点组成的必然也是二叉查找树，跟新全局最大值

public class LargestBSTSubtree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private class Result {

        int size;
        int lower;
        int upper;

        public Result(int s, int l, int u) {
            this.size = s;
            this.lower = l;
            this.upper = u;
        }
    }
    protected int max = 0;
//    使用全局变量
    public int largestBSTSubtree(TreeNode root) {
        find(root);
        return max;
    }
    private Result find(TreeNode root) {
        if(root == null) {
            return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Result left = find(root.left);
        Result right = find(root.right);
        
        if(left.size < 0 || right.size < 0 || left.upper >= root.val || right.lower <= root.val) {
            return new Result(-1, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        int s = left.size + 1 + right.size;
        max = Math.max(max, s);
        return new Result(s, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
//    不使用全局变量，所有的都是当地变量
    public int largestBSTSubtree2(TreeNode root) {
        Result res = find(root);
        return Math.abs(res.size);
    }

    private Result find2(TreeNode root) {
        if (root == null) {
            return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Result left = find2(root.left);
        Result right = find2(root.right);

        if (left.size < 0 || right.size < 0 || left.upper >= root.val || right.lower <= root.val) {
            return new Result(-Math.max(Math.abs(left.size), Math.abs(right.size)), Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        int s = left.size + 1 + right.size;
        return new Result(s, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
    
}
