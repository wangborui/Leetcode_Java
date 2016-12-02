/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree_easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class IsSymmetric {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    //recursive solution found
//    public boolean isSymmetric(TreeNode root) {
//        if(root == null) return true;
//        return isSymmetric(root.left, root.right);
//    }
//    private boolean isSymmetric(TreeNode n1, TreeNode n2){
//        if(n1 == null && n2 == null) return true;
//        if(n1 == null || n2 == null) return false;
//        return (n1.val == n2.val)&& isSymmetric(n1.right,n2.left) && isSymmetric(n1.left, n2.right);
//    }
    public boolean isSymmetric(TreeNode root){
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if(t1 == null && t2 == null) continue;
            if(t1 == null || t2 == null) return false;
            if(t1.val != t2.val) return false;
            q.add(t1.right);
            q.add(t2.left);
            q.add(t1.left);
            q.add(t2.right);
        }
        return true;
    }
}
