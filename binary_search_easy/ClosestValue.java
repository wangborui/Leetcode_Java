/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary_search_easy;

/**
 *
 * @author Borui Wang
 */
public class ClosestValue {
      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
      /*
      recursive solution
      public int closestValue(TreeNode root, double target) {
    int a = root.val;
    TreeNode kid = target < a ? root.left : root.right;
    if (kid == null) return a;
    int b = closestValue(kid, target);
    return Math.abs(a - target) < Math.abs(b - target) ? a : b;
}
      */
      /*
      iterative
      public int closestValue(TreeNode root, double target) {
    int ret = root.val;   
    while(root != null){
        if(Math.abs(target - root.val) < Math.abs(target - ret)){
            ret = root.val;
        }      
        root = root.val > target? root.left: root.right;
    }     
    return ret;
}
      */
      //my solution
//    public int closestValue(TreeNode root, double target) {
//        return closestValue(root, target, root.val);
//    }
//    public int closestValue(TreeNode root, double target, int close){
//        if(root == null) return close;
//        else{
//            if(root.val == target) return root.val;
//            else {
//                TreeNode kid= root.val > target?root.left:root.right;
//                if(Math.abs(root.val - target) < Math.abs(close - target))
//                    close = root.val;
//                return closestValue(kid, target, close);
//            
//            }
//        }
//    }
}
