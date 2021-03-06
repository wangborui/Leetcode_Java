/*
Source: https://leetcode.com/problems/implement-queue-using-stacks/
Date: 01/03/2017
/********************************************************************************** 
 * 
 * Implement the following operations of a queue using stacks.
 * 
 * push(x) -- Push element x to the back of queue.
 * 
 * pop() -- Removes the element from in front of queue.
 * 
 * peek() -- Get the front element.
 * 
 * empty() -- Return whether the queue is empty.
 * 
 * Notes:
 * 
 * You must use only standard operations of a stack -- which means only push to top, 
 * peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a 
 * stack by using a list or deque (double-ended queue), as long as you use only 
 * standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations 
 * will be called on an empty queue).
 *               
 **********************************************************************************/
package Leetcode_Java.stack_easy;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class MyQueue {

    Stack<Integer> in = new Stack();
    Stack<Integer> out = new Stack();

    // Push element x to the back of queue.
    public void push(int x) {
        in.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (out.isEmpty()) {
            if (in.isEmpty()) {
                return;
            } else {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }
        out.pop();
    }

    // Get the front element.
    public int peek() {
        if (out.isEmpty()) {
            if (in.isEmpty()) {
                return Integer.MIN_VALUE;
            } else {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }
        return out.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue a = new MyQueue();
        a.push(1);
        a.push(2);
        System.out.println(a.peek());
    }
}
