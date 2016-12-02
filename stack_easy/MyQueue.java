/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack_easy;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class MyQueue {
      Stack<Integer> stack = new Stack<>();
 
    // Push element x to the back of queue.
    public void push(int x) {
        stack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        Stack<Integer> temp = new Stack<Integer>();
        
        while(!stack.isEmpty()){
            temp.push(stack.pop());
        } 
        temp.pop();
        while(!temp.isEmpty()){
            stack.push(temp.pop());
        }
    }

    // Get the front element.
    public int peek() {
         Stack<Integer> temp = new Stack<Integer>();
        
        while(!stack.isEmpty()){
            temp.push(stack.pop());
        } 
        int top = temp.peek();
        while(!temp.isEmpty()){
            stack.push(temp.pop());
        }
        return top;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.isEmpty();
    }
    public static void main(String[] args){
//        MyQueue a = new MyQueue();
//        a.push(1);
//        a.push(2);
//        System.out.println(a.peek());
        System.out.println(2&3);
    }
}
