/*
Source: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
********************************************************************************
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: 
“The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6       2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
********************************************************************************

Note: 
1. This question assumes gives nodes p and q, which are always unique, because p and q are part of the tree
assume p is the node 5, then when traversing the tree and we get to node 5, p == cur is true, but any other node with value 5 is false p != cur
we are comparing objects, not values in objects

2.Additionally, if the tree is the following with p = 5 and q = 1 node, then LCA is 3
        _______3______
       /              \
    ___5(p)__          ___1(q)__
   /      \        /      \
   6       2       0       8
         /  \
         7   4

3.Given tree like this , p = 1, q = 5, the LCA is 1 not 3
        _______3______
       /              \
    ___5__          ___1(p)__
   /      \        /      \
   6       2       0       5(q)
         /  \
         7   4
Time O(n), space O(1) not including call stack
 */
package Leetcode_Java.tree_medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class LowestCommonAncestors {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    我们需要先判定当前根节点是否为空，如果是空的话，说明我们肯定没有公共祖先，那么直接返回根
//    再次判断如果当前的根是p树或者q树的根，那么我们就找到了p和q树的最近公共祖先，返回根
//    不然的话通过分治的方法来
//            递归地寻找找这棵树的左子树看有没有包括p和q树的最近公共祖先， 并返回查找结果
//            递归地寻找找这棵树的右子树看有没有包括p和q树的最近公共祖先， 并返回查找结果
//    如果左子树查找结果为空的话，那么说明左子树既没有p也没有q
//    如果右子树查找结果为空的话，那么说明右子树既没有p也没有q
//    如果两边查找结果都不为空的话，p和q树分散在当前根的两边
 
                    
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        
        //divide
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        //possible outcomes
        
        //conquer
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        return root;
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
    public static void main(String[] args){
        //TreeNode root = createTree(new Integer[]{3,5,1,6,33,0,5,null,null,7,9});
        TreeNode root = createTree(new Integer[]{1,2,3});
        TreeNode res = lowestCommonAncestor(root,new TreeNode(2), root.right);
        System.out.println(res.val);
     }
}
