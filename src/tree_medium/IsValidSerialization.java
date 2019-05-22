// Source : https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
// Date   : 03/11/2017

/*************************************************************************************** 
 *
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter 
 * a non-null node, we record the node's value. If it is a null node, we record using a 
 * sentinel value such as #.
 * 
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * 
 * For example, the above binary tree can be serialized to the string 
 * "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 * 
 * Given a string of comma separated values, verify whether it is a correct preorder 
 * traversal serialization of a binary tree. Find an algorithm without reconstructing 
 * the tree.
 * 
 * Each comma separated value in the string must be either an integer or a character 
 * '#' representing null pointer.
 * 
 * You may assume that the input format is always valid, for example it could never 
 * contain two consecutive commas such as "1,,3".
 * 
 * Example 1:
 * "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Return true
 * Example 2:
 * "1,#"
 * Return false
 * Example 3:
 * "9,#,#,1"
 * Return false
 * 
 * Credits:Special thanks to @dietpepsi for adding this problem and creating all test 
 * cases.
 ***************************************************************************************/
package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class IsValidSerialization {
//    我们知道每个节点有2个出度和一个入度，2个孩子节点，一个父亲节点
//    如果一个节点为空，则这个节点有一个入度和0个出度（没有孩子节点）
//    如果我们按照数组提供的前序遍历来访问这个树， 计算出入度的差 diff = 出度 - 入度， 最后看diff 是不是 0
//    每次访问一个新节点，我们的入度就+1，就相当于diff - 1， 如果这个节点不是叶子节点，我们的总出度就+2， 相当于diff + 2
//    这里要注意的是，diff的值永远不能小于0（以防“#”作为父亲节点的前序遍历序列化）
//    比如"#,#,3,5,#"， 虽然这个序列化最后的diff是0，但是他有几个父亲节点是“#”
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        //diff = outdegree - indegree
        //regular nodes have diff = 2 - 1 = 1, we assume root comes from a valid parent node
        int diff = 1;
        for (String node : nodes) {
            //getting a new node indgree + 1, therefore, diff - 1
            diff--;
            //make sure "#" can not be used as parent node, for example "#,#,#"
            if (diff < 0) {
                return false;
            }
            //none leave nodes have 2 outdgrees, therefore outdegree + 2, and diff + 2
            if (!node.equals("#")) {
                diff += 2;
            }
        }
        return diff == 0;
    }
}
