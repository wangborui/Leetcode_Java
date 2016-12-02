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
public class IsBalanced {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int L = maxDepth(root.left);
        if (L == -1) {
            return -1;
        }
        int R = maxDepth(root.right);
        if (R == -1) {
            return -1;
        }
        return (Math.abs(L - R) <= 1) ? (Math.max(L, R) + 1) : -1;
    }

    //brute force
//    public boolean isBalanced(TreeNode root) {
//        if(root == null) return true;
//        int left = height(root.left);
//        int right = height(root.right);
//        return (int)Math.abs(left - right) <=1 && isBalanced(root.left) && isBalanced(root.right);
//    }
//    private int height(TreeNode root){
//        if(root == null) return 0;
//        return 1 + Math.max(height(root.left),height(root.right));
//    }
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
        IsBalanced ib = new IsBalanced();
        TreeNode root = ib.addNodes(new Integer[]{4, 5, 6, null, 3, null, 5, null, null, 6, null, null, 7});
        System.out.println(ib.isBalanced(root));
    }
}
