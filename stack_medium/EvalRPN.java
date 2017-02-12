// Source : https://oj.leetcode.com/problems/evaluate-reverse-polish-notation/
// Date   : 02/12/2017

/********************************************************************************** 
* 
* Evaluate the value of an arithmetic expression in Reverse Polish Notation.
* 
* Valid operators are +, -, *, /. Each operand may be an integer or another expression.
* 
* Some examples:
* 
*   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
*   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
* 
*               
**********************************************************************************/
package Leetcode_Java.stack_medium;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack();
        for (String token : tokens) {
            if (token.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            } else if (token.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (token.equals("*")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            } else if (token.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
