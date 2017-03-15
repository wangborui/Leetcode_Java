// Source : https://leetcode.com/problems/path-sum-iii/#/description
// Date   : 03/15/2017
/**
 * ********************************************************************************
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *********************************************************************************
 */
package Leetcode_Java.tree_easy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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

        TreeNode(int x) {
            val = x;
        }
    }
//    我们需要计算prefix sum， 访问每个节点的时候计算这个节点的prefix sum
//    在计算完当前节点的prefix Sum之后，把这个数字加到哈希表当中表示这个prefix sum出现的次数
//    并且在访问某个节点的时候需要查看当前prefix sum - targetSum 是否也在哈希表中，如果有的话那么我们就加上prefix sum - targetSum 出现过的次数
//    递归的访问当前节点的左子树和右子树，看看target sum在这些子树中出现的次数
//    最后在访问完成当下节点的时候我们需要把当下节点的prefix sum从哈希表当中取掉，因为我们的计算路径只能是从上到下，从根节点到子树节点，不能横跨根部节点
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);
        return helper(0, root, sum, map);
    }

    private int helper(int preSum, TreeNode root, int target, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        preSum += root.val;
        int ways = map.getOrDefault(preSum - target, 0);
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        int left = helper(preSum, root.left, target, map);
        int right = helper(preSum, root.right, target, map);
        ways += left + right;
        //take each visited node out of prefix sum hashmap, so we dont calculate cross root paths
        map.put(preSum, map.get(preSum) - 1);
        return ways;
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
        TreeNode root = ps.addNodes(new Integer[]{5, 10, -3, 3, 2, null, 11, 3, -2, null, 1});
        System.out.println(ps.pathSum(root, 8));
    }
}
