/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_programming_medium;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class GenerateTrees {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public List<TreeNode> generateTrees(int n) {
        if(n==0) return new LinkedList<TreeNode>();
        return dp(1,n);
    }
    public List<TreeNode> dp(int l, int h){
        List<TreeNode> res = new LinkedList<>();
        
        if(l > h){
            res.add(null);
            return res;
        }
        else if(l == h){
            res.add(new TreeNode(l));
            return res;
        }
        else{
            for(int i = l;i <= h;i++){
                List<TreeNode> lefts = dp(l,i-1);
                List<TreeNode> rights = dp(i+1,h);
                for(TreeNode le : lefts){
                    for(TreeNode ri : rights){
                        TreeNode root = new TreeNode(i);
                        root.left = le;
                        root.right = ri;
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }
    public static void main(String [] args){
        GenerateTrees s = new GenerateTrees();
        s.generateTrees(2);
    }
    
}
