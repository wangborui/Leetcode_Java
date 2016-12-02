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
public class MinStack {
//    private Node min;
//    private Node head;
//    private int N;
//    
//    private class Node{
//        private int val;
//        private Node next;
//    }
//    /** initialize your data structure here. */
//    public MinStack() {
//        head = null;
//        min = new Node();
//        min.val = Integer.MAX_VALUE;
//        N = 0;
//
//    }
//    
//    public void push(int x) {
//        Node temp = new Node();
//        temp.val = x;
//        temp.next = head;
//        if(min.val > temp.val) min = temp;
//        head = temp;
//        N++;
//         
//    }
//    
//    public void pop() {
//        if(N>0){
//            if(head == min){
//                Node walker = head.next;
//                int minVal = Integer.MAX_VALUE;
//                min = new Node();
//                min.val = minVal;
//                while(walker != null){
//                    if(walker.val < minVal){
//                        min = walker;
//                        minVal = walker.val;
//                    }
//                    walker = walker.next;
//                }
//            }
//            head = head.next;
//            N--;
//        }  
//         
//    }
//    
//    public int top() {
//        return head == null?0:head.val;
//    }
//    
//    public int getMin() {
//        return (min.val == Integer.MAX_VALUE && N == 0)?0:min.val;
//    }
 //solution found
Stack<Integer> stack = new Stack<>();
int min = Integer.MAX_VALUE;
public void push(int x) {
  if(x < min){
      stack.push(min);
      min = x;
  }
  stack.push(x);
}
public void pop() {
 if(stack.peek() == min){
     stack.pop();
     min = stack.pop();
 }else{
     stack.pop();
 }
}
public int top() {
 return stack.peek();
}
public int getMin() {
 return min;
}
    public static void main(String[] args){
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
