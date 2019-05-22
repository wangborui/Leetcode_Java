// Source : https://oj.leetcode.com/problems/unique-binary-search-trees-ii/
// Author : Hao Chen
// Date   : 2014-06-25

/********************************************************************************** 
* 
* Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
* 
* For example,
* Given n = 3, your program should return all 5 unique BST's shown below.
* 
*    1         3     3      2      1
*     \       /     /      / \      \
*      3     2     1      1   3      2
* 
* confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
* 
* OJ's Binary Tree Serialization:
* 
* The serialization of a binary tree follows a level order traversal, where '#' signifies 
* a path terminator where no node exists below.
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
package Leetcode_Java.dynamic_programming_medium;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
//这个题的思路和unique BST 1 是一样的
//我们对于一个数字i，我们需要分别创建以1,2,3,4...i - 1，i 为根节点的所有unique BST
//当1作为根节点的时候， 我们需要创建所有2到n的unique BST作为1的右子树
//当2作为根节点的时候，我们需要创建所有1到1的unique BST作为2的左子树，和3到n的unique BST作为2的右子树
//这个任务可以使用递归来完成，我们建立一个函数帮助建立这些所有根节点的unique BST
public class GenerateTrees {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new LinkedList<TreeNode>();
        }
        return dp(1, n);
    }

    public List<TreeNode> dp(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        //cover edge case and terminates recursion
        if (start > end) {
            res.add(null);
            return res;
        } else if (start == end) {
            res.add(new TreeNode(start));
            return res;
        } else {
            for (int i = start; i <= end; i++) {
                List<TreeNode> lefts = dp(start, i - 1);
                List<TreeNode> rights = dp(i + 1, end);
                for (TreeNode le : lefts) {
                    for (TreeNode ri : rights) {
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

    public static void main(String[] args) {
        GenerateTrees s = new GenerateTrees();
        s.generateTrees(2);
    }

}
