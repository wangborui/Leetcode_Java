// Source : https://oj.leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
// Date   : 03/13/2017

/********************************************************************************** 
* 
* Given a binary tree, return the zigzag level order traversal of its nodes' values. 
* (ie, from left to right, then right to left for the next level and alternate between).
* 
* For example:
* Given binary tree {3,9,20,#,#,15,7},
* 
*     3
*    / \
*   9  20
*     /  \
*    15   7
* 
* return its zigzag level order traversal as:
* 
* [
*   [3],
*   [20,9],
*   [15,7]
* ]
* 
* confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
* 
* OJ's Binary Tree Serialization:
* 
* The serialization of a binary tree follows a level order traversal, 
* where '#' signifies a path terminator where no node exists below.
* 
* Here's an example:
* 
*    1
*   / \
*  2   3
*     /
*    4
*     \
*      5
* 
* The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}". 
* 
*               
**********************************************************************************/
package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class ZigzagLevelOrder {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    这个题的思路和“level order traversal”是一样的
//    不一样的地方在于需要保存一个boolean值，告诉我们这层是从左往右加还是从右往左加
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        boolean fromLeft = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            //size needs to be kept in the begining of the loop, because size changes as more elements are polled out of queue
            int size = queue.size();
            List<Integer> level = new ArrayList();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (fromLeft) {
                        level.add(node.val);
                    } else {
                        level.add(0, node.val);
                    }

                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            if (!level.isEmpty()) {
                levels.add(level);
                fromLeft = !fromLeft;
            }
        }
        return levels;
    }
}
