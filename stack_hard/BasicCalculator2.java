// Source : https://leetcode.com/problems/basic-calculator-ii/
// Date   : 02/14/2017

/********************************************************************************** 
 * 
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces  . 
 * The integer division should truncate toward zero.
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples:
 * 
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * 
 * Note: Do not use the eval built-in library function.
 * 
 * Credits:Special thanks to @ts for adding this problem and creating all test cases.
 **********************************************************************************/
package Leetcode_Java.stack_hard;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class BasicCalculator2 {
     static int calculate(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        ArrayList<String> RPN = convertToRPN(s);
        return calculateRPN(RPN);
    }
    static int calculateRPN(ArrayList<String> RPN) {
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
    static ArrayList<String> convertToRPN(String expression) {
        ArrayList<String> res = new ArrayList();
        Stack<String> stack = new Stack();
        
        for(int i = 0; i < expression.length(); i++) {
            String s = "" + expression.charAt(i);
            if(Character.isDigit(s.charAt(0))) {
                //parse number
                int j = i;
                while(j + 1 < expression.length() && Character.isDigit(expression.charAt(j + 1))) {
                    j++;
                }
                res.add(expression.substring(i, j+1));
                i = j;
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
        System.out.println(res);
        return res;
    }
    static int getPriority(String s) {
        if(s.equals("(")) {
            return 0;
        } else if(s.equals("+") || s.equals("-")) {
            return 1;
        } else {
            return 2;
        }
    }
}
