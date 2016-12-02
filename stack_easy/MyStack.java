/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack_easy;

import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class MyStack {
       // Push element x onto stack.
    private ArrayList<Integer> arr = new ArrayList<Integer>();
 
    public void push(int x) {
        arr.add(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
       arr.remove(arr.size()-1);
    }

    // Get the top element.
    public int top() {
       return arr.get(0);
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return arr.isEmpty();
    }
    public static void main(String args){
        System.out.println(2&3);
    }
}
