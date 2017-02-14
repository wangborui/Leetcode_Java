//Source : http://www.lintcode.com/en/problem/expression-evaluation/#
//Date   : 02/14/2017

/**********************************************************************************
Given an expression string array, return the final result of this expression

 Notice

The expression contains only integer, +, -, *, /, (, ).

Have you met this question in a real interview? Yes
Example
For the expression 2*6-(23+7)/(1+2),
input is

[
  "2", "*", "6", "-", "(",
  "23", "+", "7", ")", "/",
  (", "1", "+", "2", ")"
],
return 2
 ***********************************************************************************/
package Leetcode_Java.stack_hard;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class EvaluateExpression {
     /**
     * @param expression: an array of strings;
     * @return: an integer
     */
    //idea is:
//    
//    1.) convert expression to Reverse Polish Notation(RPN)
//            using either min tree or stack trick, see convert to RPN
//    2.) calculate RPN
//    
//    Time O(n) Space O(n)
    public int evaluateExpression(String[] expression) {
        if(expression == null || expression.length == 0) {
            return 0;
        }
        ArrayList<String> RPN = convertToRPN2(expression);
        return calculateRPN(RPN);
    }
    private int calculateRPN(ArrayList<String> RPN) {
        Stack<Integer> stack = new Stack();
        //cover edge case where there is only parenthesis
        stack.push(0);
        for(String s : RPN) {
            if(Character.isDigit(s.charAt(0))) {
                stack.push(Integer.parseInt(s));
            } else if(s.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            } else if(s.equals("*")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            } else if(s.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            }
        }
        return stack.pop();
    }
    private ArrayList<String> convertToRPN2(String[] expression) {
        ArrayList<String> res = new ArrayList();
        Stack<String> stack = new Stack();
        
        for(String s : expression) {
            if(Character.isDigit(s.charAt(0))) {
                res.add(s);
            } else if(s.equals(" ")) {
                continue;
            } else if(s.equals("(")) {
                stack.push(s);
            } else if(s.equals(")")) {
                while(!stack.isEmpty() && !stack.peek().equals("(")) {
                    res.add(stack.pop());
                }
                //pop out "("
                stack.pop();
            } else {
                while(!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(s)) {
                    res.add(stack.pop());
                }
                //add the current character
                stack.add(s);
            }
        }
        while(!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }
    private int getPriority(String s) {
        if(s.equals("(")) {
            return 0;
        } else if(s.equals("+") || s.equals("-")) {
            return 1;
        } else {
            return 2;
        }
    }
}
