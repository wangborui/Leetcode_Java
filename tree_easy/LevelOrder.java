/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree_easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class LevelOrder {
     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
     //my solution
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root  == null) return new LinkedList<>();
        List<List<Integer>> levels = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            List<Integer> level = new LinkedList<>();
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode temp = q.poll();
                level.add(temp.val);
                if(temp.left !=  null){
                    q.add(temp.left);
                }
                if(temp.right != null){
                    q.add(temp.right);
                }
            }
            levels.add(level);
        }
        return levels;
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
    public static void main(String [] args){
        LevelOrder l = new LevelOrder();
        TreeNode root = l.addNodes(new Integer[]{3,9,20,null,null,15,7});
        l.levelOrder(root);
        System.out.println( );
    }
}
