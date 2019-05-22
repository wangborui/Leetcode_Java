// Source : https://oj.leetcode.com/problems/valid-parentheses/
// Date   : 04/03/2017

/********************************************************************************** 
* 
* Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
* determine if the input string is valid.
* 
* The brackets must close in the correct order, "()" and "()[]{}" are all valid 
* but "(]" and "([)]" are not.
* 
*               
**********************************************************************************/
package Leetcode_Java.stack_easy;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class ValidParenthesis {
    
//    如果看到“左”括号就放到栈里
//    看到“右”括号就看栈是否为空，或者看栈顶是否为“左”括号，并且弹出
//    最后检查栈是否为空
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == '{') {
                stack.push(c);
            } else if (c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            } else if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') {
                    return false;
                }
                stack.pop();
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                }
                stack.pop();
            } else {
                continue;
            }
        }
        return stack.isEmpty();
    }
}
