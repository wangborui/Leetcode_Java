//Source: https://leetcode.com/problems/binary-tree-maximum-path-sum/
//Date  : 02/25/2017

/*
********************************************************************************
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
********************************************************************************

Solution: 
Two important variables, global max sum and local max sum
 */
package Leetcode_Java.tree_hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class MaxPathSum {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //global sum variable
    //全局最大长度，可以是横跨根节点的，也可以是不横跨根节点的，初始化为负无穷，因为二叉树存在负数
    static int maxSum = Integer.MIN_VALUE;
    static int maxPathSum(TreeNode root) {
        //if the tree is null return Integer.MIN_VALUE
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        helper(root);
        return maxSum;
    }
    //maximum single local path sum starting with current root
    //3 possible outcome, root, root + left, or root + right
    //cannot be left or right alone, because we assume the single local path starts at current node
    //辅助函数，用来计算从当前节点出发，能够得到的最大路径长度并且此路径不能横跨当前节点
    static int helper(TreeNode root) {
        if(root == null) {
            return 0;
        }
        //divide
        //计算左子树不横跨根节点的最大路径大长度
        int leftBranch = helper(root.left);
        //计算右子树不横跨根节点的最大路径长度
        int rightBranch = helper(root.right);
        
        //conquer
        /*
        outcomes:
        1. leftBranch + root.val has max value
        2. rightBranch + root.val has max value
        3. root.val has max value
        4. leftBranch + rightBranch + root.val has max value
        */
        //condition 1.2.3
        //找到以当前节点作为根节点的最大路径长度，此路径不横跨当前节点
        int singlePathMax = Math.max(root.val, Math.max(leftBranch, rightBranch) + root.val);
        //condition 4.
        //找到以当前节点作为根节点的全局最大路径长度，此路径可以横跨当前节点，或者是之前计算的不横跨最大路径长度
        int crossPathMax = Math.max(singlePathMax, leftBranch + rightBranch + root.val);
         
        //global max path
        //更新全局最大路径长度值
        maxSum = Math.max(maxSum, crossPathMax);
        
        //返回非横跨当前节点的最大路径值
        return singlePathMax;
    }

    static TreeNode createTree(Integer[] nodes) {
        Queue<TreeNode> q = new LinkedList();
        int index = 0;
        TreeNode root = new TreeNode(nodes[index++]);
        q.add(root);

        while (!q.isEmpty() && index < nodes.length) {
            TreeNode temp = q.poll();

            TreeNode left = nodes[index++] == null ? null : new TreeNode(nodes[index - 1]);
            TreeNode right = nodes[index++] == null ? null : new TreeNode(nodes[index - 1]);
            temp.left = left;
            temp.right = right;
            if (left != null) {
                q.add(left);
            }
            if (right != null) {
                q.add(right);
            }
        }
        return root;
    }
 //localSinglePathMax assumes starting at current node, max single path value that can be passed up, must have one value
    /*
    there are 3 possibilities:
    1.root val
    2.root val + left.localsinglepathmax
    3.root val + right.localsinglepathmax
    */
    //globalCrossPathMax is the largest cross path sum
    /*
    there are 4 possibilities:
    1.max are below this level max(left.globalCrossPathMax, right.globalCrossPathMax)
    2.max is at this level max(root val + max(left.localsinglepathmax, 0) + max(right.localsinglepathmax, 0),
          previous level max)

    */
    private class ResultType {
        int localSinglePathMax;
        int globalCrossPathMax;
        public ResultType(int localSinglePathMax, int globalCrossPathMax) {
            this.localSinglePathMax = localSinglePathMax;
            this.globalCrossPathMax = globalCrossPathMax;
        }
    }
    public int maxPathSumWithClass(TreeNode root) {
        ResultType res = helperWithClass(root);
        return res.globalCrossPathMax;
    }
    
    private ResultType helperWithClass(TreeNode root) {
        if(root == null) {
            return new ResultType(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        
        //divide
        ResultType left = helperWithClass(root.left);
        ResultType right = helperWithClass(root.right);
        
        //conquer
        //this calculates the 3 possibilities
        int singleMax = Math.max(Math.max(left.localSinglePathMax, right.localSinglePathMax), 0) + root.val;
        
        int globalMax = Math.max(left.globalCrossPathMax, right.globalCrossPathMax);
        globalMax = Math.max(globalMax, 
                            Math.max(left.localSinglePathMax, 0) +
                            Math.max(right.localSinglePathMax, 0) + root.val);
        
        return new ResultType(singleMax, globalMax);
        
    }
    public static void main(String[] args) {

    }
}
