// Source : https://leetcode.com/problems/basic-calculator/
// Date   : 02/14/2017
/**
 * ********************************************************************************
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
 * non-negative integers and empty spaces  .
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 *
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 *
 * Note: Do not use the eval built-in library function.
 *
 *
 *********************************************************************************
 */
package Leetcode_Java.stack_hard;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class BasicCalculator {

    /***************************************************************************
     * Solution 1 (RPN)
     *
     * 1.) convert to Reverse Polish Notation 
     * 2.) Calculate RPN
     *
     * @param s
     * @return
     *********************************************************************************/
    static int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        ArrayList<String> RPN = convertToRPN(s);
        return calculateRPN(RPN);
    }

    static int calculateRPN(ArrayList<String> RPN) {
        Stack<Integer> stack = new Stack();
        //cover edge case where there is only parenthesis
        stack.push(0);
        for (String s : RPN) {
            if (Character.isDigit(s.charAt(0))) {
                stack.push(Integer.parseInt(s));
            } else if (s.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            } else if (s.equals("*")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            } else if (s.equals("-")) {
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

        for (int i = 0; i < expression.length(); i++) {
            String s = "" + expression.charAt(i);
            if (Character.isDigit(s.charAt(0))) {
                //parse number
                int j = i;
                while (j + 1 < expression.length() && Character.isDigit(expression.charAt(j + 1))) {
                    j++;
                }
                res.add(expression.substring(i, j + 1));
                i = j;
            } else if (s.equals(" ")) {
                continue;
            } else if (s.equals("(")) {
                stack.push(s);
            } else if (s.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    res.add(stack.pop());
                }
                //pop out "("
                stack.pop();
            } else {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(s)) {
                    res.add(stack.pop());
                }
                //add the current character
                stack.add(s);
            }
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        System.out.println(res);
        return res;
    }

    static int getPriority(String s) {
        if (s.equals("(")) {
            return 0;
        } else if (s.equals("+") || s.equals("-")) {
            return 1;
        } else {
            return 2;
        }
    }

    /***************************************************************************
     * Simple Stack Solution 2
     *
     * @param s
     * @return
     ***************************************************************************/
//    The idea is:
//    
//    the problem statement says what we only need to deal with plus, minus, and parenthesis
//    Due to the fact that addition and subtraction have similar calculation priorities, we can do calculations in the order equation is presented
//    
//    what do we do if we only have plus and minus signs without parenthesis? 
//    we can use a variable called sign to record last operator, plus or minus, 
//    this way when we encounter a number, we can multiple sign with number and add to result
//            
//    what do we do if we add parenthesis into our equations?
//    we can treat every thing in parenthesis as one number, this one number is again affected by the sign variable, how?
//        if we have a minus sign '-' before '()', then all signs in the parenthesis becomes minus * own sign
//                but if we have one more parenthesis, we need to keep track of this parenthesis's sign, and when parenthesis ends, we need to remove the "parenthesis sign"
//    
//    Therefore, we can use a stack to keep track of signs of a parenthesis
    
//Note: we need to remove all spaces first
    static int calculate2(String s) {
        int sign = 1;
        int res = 0;
        Stack<Integer> stack = new Stack();
        //add 1 to stack, assuming we have a "+(...)" structure for the equation
        stack.push(1);
        s = s.replace(" ", "");
        int i = 0;
        while (i < s.length()) {
            char cur = s.charAt(i);
            //change signs, when we see plus sign
            if (cur == '+') {
                sign = 1;
                i++;
            //change signs, when we see minus sign
            } else if (cur == '-') {
                sign = -1;
                i++;
            //when encounter left parenthesis, find out current parenthesis sign, push to stack
            //we multiply current sign with previous parenthesis sign
            } else if (cur == '(') {
                stack.push(sign * stack.peek());
                sign = 1;
                i++;
            //when encounter right parenthesis, current parethesis sign finishes, and pop off of stack
            } else if (cur == ')') {
                stack.pop();
                i++;
            //when we encouter number, find out its sign, then add to result
            } else {
                int num = 0;
                //2147483647
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    cur = s.charAt(i);
                    num = 10 * num + (int) (cur - '0');
                    i++;
                }
                res += sign * stack.peek() * num;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        //"1 + 1", " 2-1 + 2 ", "(1+(4+5+2)-3)+(6+8)",
//        String[] inputs = {"12+ 1", "1 + 1", " 2-1 + 2 ", "(1+(4+54+2)-3)+(6+8)", "1-(3+5-2+(3+19-(3-1-4+(9-4-(4-(1+(3)-2)-5)+8-(3-5)-1)-4)-5)-4+3-9)-4-(3+2-5)-10"};
//        for (String input : inputs) {
//            System.out.println(calculate(input));
//        }
        String in = "1-(5)";
        System.out.println(calculate2(in));

    }
}
