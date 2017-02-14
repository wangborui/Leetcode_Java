//Source : http://www.lintcode.com/en/problem/convert-expression-to-reverse-polish-notation/
//Date   : 02/12/2017

/**********************************************************************************
 * Given an expression string array, return the Reverse Polish notation of this expression. 
 * (remove the parentheses)

Have you met this question in a real interview? Yes
Example
For the expression [3 - 4 + 5] (which denote by ["3", "-", "4", "+", "5"]), return [3 4 - 5 +] (which denote by ["3", "4", "-", "5", "+"])
 ***********************************************************************************/

package Leetcode_Java.stack_hard;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class ConvertToRPN {

    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    static class TreeNode {

        int priority;
        String val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int p, String s) {
            this.priority = p;
            this.val = s;
        }
    }
    private static final int PLUSMINUS = 1;
    private static final int MULTDIVID = 2;
    private static final int NUMBER = Integer.MAX_VALUE;
    private static int base = 0;

//    Analysis:
//    
//    if we define priority of each operator 
//            "+ -" equals 1 when base is 0, or 1 + base
//            "* /" equals 2 when base is 0, or 1 + base
//            "int" equals infinite
//            "( )" equals base + 10 or - 10
//    
//    we can build a mintree based on this priority with smallest priority element as parent of a binary tree
//    post order traversal this tree, we can then get the reverse polish notation
    
//    Optimization:
//    the order elements are popped from stack while building the mintree is post order traversal of mintree, we can then save postorder traversal
//    Pitfalls: 
//    
//            1.)if a number is in a stack, then all numbers <= this number to its right and left can be its parent
//            2.)when a number is in a stack, its left number = its right number, and they are both less than current number, add current to left number's right child
    static ArrayList<String> convertToRPN(String[] expression) {
        ArrayList<String> res = new ArrayList();
        if (expression == null || expression.length == 0) {
            return res;
        }
        // convert to min tree
        TreeNode root = convertMinTree(expression, res);
        postOrderVisit(root, res);
        return res;
    }

    static void postOrderVisit(TreeNode root, ArrayList<String> res) {
        if (root == null) {
            return;
        }
        postOrderVisit(root.left, res);
        postOrderVisit(root.right, res);
        res.add(root.val);
    }

    static TreeNode convertMinTree(String[] expression, ArrayList<String> res) {
        Stack<TreeNode> stack = new Stack();
        for (int i = 0; i <= expression.length; i++) {
            String curS = i == expression.length ? "end" : expression[i];
            TreeNode right;
            if (curS.equals("end")) {
                right = new TreeNode(Integer.MIN_VALUE, curS);
            } else if (curS.equals("(")) {
                base += 10;
                continue;
            } else if (curS.equals(")")) {
                base -= 10;
                continue;
            } else if (curS.equals("+") || curS.equals("-")) {
                right = new TreeNode(PLUSMINUS + base, curS);
            } else if (curS.equals("*") || curS.equals("/")) {
                right = new TreeNode(MULTDIVID + base, curS);
            } else {
                right = new TreeNode(NUMBER, curS);
            }

            while (!stack.isEmpty() && right.priority <= stack.peek().priority) {
                TreeNode nodeNow = stack.pop();
                res.add(nodeNow.val);
                if (stack.isEmpty()) {
                    right.left = nodeNow;
                } else {
                    TreeNode left = stack.peek();
                    if (left.priority < right.priority) {
                        right.left = nodeNow;
                    } else {
                         left.right = nodeNow;
                    }
                }
            }
            stack.push(right);
        }
        return stack.peek().left;
    }
    public static void main(String[] args) {
        String [] input = {"3","-","4","+","5"};
        System.out.println(convertToRPN(input));
    }
}
