// Source : https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
// Date   : 03/11/2017

/*************************************************************************************** 
 *
 * Serialization is the process of converting a data structure or object into a 
 * sequence of bits so that it can be stored in a file or memory buffer, or transmitted 
 * across a network connection link to be reconstructed later in the same or another 
 * computer environment. 
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no 
 * restriction on how your serialization/deserialization algorithm should work. You 
 * just need to ensure that a binary tree can be serialized to a string and this string 
 * can be deserialized to the original tree structure.
 * 
 * For example, you may serialize the following tree
 * 
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * 
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary 
 * tree. You do not necessarily need to follow this format, so please be creative and 
 * come up with different approaches yourself.
 * 
 * Note: Do not use class member/global/static variables to store states. Your 
 * serialize and deserialize algorithms should be stateless.
 * 
 * Credits:Special thanks to @Louis1992 for adding this problem and creating all test 
 * cases.
 *               
 ***************************************************************************************/

package Leetcode_Java.trie_hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class Codec {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    用“，” 来分割每个串行化后的节点，用“#”来代表空节点
//    用递归前序遍历的方法我们可以把原二叉树串行化成一个字符串
//    在解串行化的时候，因为我们可以确定之前做的二叉树串行化一定是合法的
//    我们可以先把串行化的字符串用队列存储成之前的前序遍历，因为我们可以确定所有的原二叉树空节点都用了“#”来表示，再次使用递归的方法前序遍历队列即可返回原二叉树
    private static final String SPLITTER = ",";
    private static final String NN = "#";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //using string builder to increase performance
        //because we need to keep adding new nodes to our string, stringbuilder would allow us to do it faster
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        //we can split string like so "1," with separator "," to get [1]
        return sb.toString();
    }
    private void buildString(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append(NN).append(SPLITTER);
        } else {
            sb.append(root.val).append(SPLITTER);
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue q = new LinkedList();
        q.addAll(Arrays.asList(data.split(SPLITTER)));
        return buildTree(q);
    }
    private TreeNode buildTree(Queue<String> q) {
        String cur = q.poll();
        if(cur.equals(NN)) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.valueOf(cur));
            root.left = buildTree(q);
            root.right = buildTree(q);
            return root;
        }
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

    public static void main(String[] args) {
        TreeNode root = createTree(new Integer[]{1,2,3});
        Codec c = new Codec();
        root = c.deserialize(c.serialize(root));
        System.out.println("5,4,".split(",")[1]);
    }
}
