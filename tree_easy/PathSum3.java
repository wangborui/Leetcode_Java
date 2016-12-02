/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree_easy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class PathSum3 {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
     public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        helper(root, 0, sum, preSum);
        return count;
    }
    int count = 0;
    public void helper(TreeNode root, int sum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return;
        }
        
        sum += root.val;

        if (preSum.containsKey(sum - target)) {
            count += preSum.get(sum - target);
        }
        
        if (!preSum.containsKey(sum)) {
            preSum.put(sum, 1);
        } else {
            preSum.put(sum, preSum.get(sum)+1);
        }
        
        helper(root.left, sum, target, preSum);
        helper(root.right, sum, target, preSum);
        preSum.put(sum, preSum.get(sum) - 1);
    }
       private TreeNode addNodes(Integer[] nodes) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
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
           public static void main(String[] args) {
               PathSum3 ps = new PathSum3();
               //TreeNode root = ps.addNodes(new Integer[]{10,5,-3,3,2,null,11,3,-2,null,1});
               TreeNode root = ps.addNodes(new Integer[]{5,10,-3,3,2,null,11,3,-2,null,1});
               System.out.println(ps.pathSum(root, 8));
           }
}
