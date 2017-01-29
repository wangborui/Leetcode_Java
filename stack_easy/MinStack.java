// Source : https://leetcode.com/problems/min-stack/
// Date   : 01/03/2017

/********************************************************************************** 
* 
* Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
* 
* push(x) -- Push element x onto stack.
* 
* pop() -- Removes the element on top of the stack.
* 
* top() -- Get the top element.
* 
* getMin() -- Retrieve the minimum element in the stack.
* 
*               
**********************************************************************************/

package Leetcode_Java.stack_easy;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 * Analysis:
 * We are asked to design a min stack to perform every element in constant time. 
 * Therefore, we need to keep track of everything top and min element, 
 * because min element is dynamic and changing with push and pop operations we need to keep track of min for each element pushed
 * Therefore, we use 2 stack structures.
 *          
 *          1.) first stack keeps track of the real elements pushed and popped
 *          2.) second stack keeps track of min element for each element in first stack
 * 
 *          for example,
 *              if we push the following element 5,6,1,2,3...
 *                      1) 5->6->1->2->3...
 *                      2) 5->5->1->1->1...
 * 
 *then, when we push elements into stack, we need to compare current element x to top element in min stack,
 * if it is smaller than top element in min stack, we push x into min stack, else push top of min stack into min stack
 * the normal stack does push and pop regularly
 * 
 * 
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack();
        minStack = new Stack();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(x < minStack.peek() ? x : minStack.peek());
        }
        stack.push(x);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            minStack.pop();
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return Integer.MIN_VALUE;
    }

    public int getMin() {
        if (!stack.isEmpty()) {
            return minStack.peek();
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(2147483646);
        obj.push(2147483646);
        obj.push(2147483647);
        System.out.println(obj.top());
        obj.pop();
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.getMin());
        obj.pop();
        obj.push(2147483647);
        System.out.println(obj.top());
        System.out.println(obj.getMin());
        obj.push(-2147483648);
        System.out.println(obj.top());
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.getMin());
    }
}
