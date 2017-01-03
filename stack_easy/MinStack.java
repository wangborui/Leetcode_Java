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
 */
public class MinStack {
    /*********************
     * using linked list to implement a stack
      
    private Node min;
    private Node head;
    private int N;
    
    private class Node{
        private int val;
        private Node next;
    }*/
    /** initialize your data structure here. */
     /*
    public MinStack() {
        head = null;
        min = new Node();
        min.val = Integer.MAX_VALUE;
        N = 0;

    }
    
    public void push(int x) {
        Node temp = new Node();
        temp.val = x;
        temp.next = head;
        if(min.val > temp.val) min = temp;
        head = temp;
        N++;
         
    }
    
    public void pop() {
        if(N>0){
            if(head == min){
                Node walker = head.next;
                int minVal = Integer.MAX_VALUE;
                min = new Node();
                min.val = minVal;
                while(walker != null){
                    if(walker.val < minVal){
                        min = walker;
                        minVal = walker.val;
                    }
                    walker = walker.next;
                }
            }
            head = head.next;
            N--;
        }  
         
    }
    
    public int top() {
        return head == null?0:head.val;
    }
    
    public int getMin() {
        return (min.val == Integer.MAX_VALUE && N == 0)?0:min.val;
    }
    */
    /**
     *Implementations:
     * 
     * use a stack to keep track of the numbers pushed in
     * use a min stack to keep track of current min value
     *  
     *  for example:
     *      push      5,7,1,10,9
     *      stack   : 5,7,1,10,9
     *      minStack: 5,5,1,1, 1  <== push numbers in min stack that are smaller than top of min stack
     * 
     * pop()   : pop number out of stack and min stack
     * getMin(): return the top of min stack 
     */
    
    Stack<Integer> stack;
    Stack<Integer> minStack;

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
