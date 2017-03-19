// Source : https://leetcode.com/problems/implement-stack-using-queues/
// Date   : 01/03/2017

/********************************************************************************** 
 * 
 * Implement the following operations of a stack using queues.
 * 
 * push(x) -- Push element x onto stack.
 * 
 * pop() -- Removes the element on top of the stack.
 * 
 * top() -- Get the top element.
 * 
 * empty() -- Return whether the stack is empty.
 * 
 * Notes:
 * 
 * You must use only standard operations of a queue -- which means only push to back, 
 * peek/pop from front, size, and is empty operations are valid.
 *
 * Depending on your language, queue may not be supported natively. You may simulate 
 * a queue by using a list or deque (double-ended queue), as long as you use only 
 * standard operations of a queue.
 *
 * You may assume that all operations are valid (for example, no pop or top operations 
 * will be called on an empty stack).
 * 
 * Update (2015-06-11):
 * The class name of the Java function had been updated to MyStack instead of Stack.
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and all test cases.
 *               
 **********************************************************************************/
package Leetcode_Java.stack_easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 * Analysis:
 * We know that stack is first in last out but queue is first in first out,
 * so, in order to maintain order of stack using queue, when we add an element into queue.
 * we first add the new element into queue, then poll() old element from queue then add old element back into queue.
 * repeat the entire process until all old elements are "reversed"
 * 
 * for example,
 *          
 *          if we push elements 3,5,6,2...
 *          queue head: 3
 *          queue head: 3 -> 5                   reversed queue: 5 -> 3
 *          queue head: 5 -> 3 -> 6              reversed queue: 6 - > 5 -> 3
 *          queue head: 5 -> 3 -> 6 -> 2         reversed queue: 2 -> 5 -> 3 -> 6
 */
public class MyStack {
//    建立一个队列，
//    为了模仿栈的功能，我们必须在每次新加一个元素进入队列后都把队列里原有的东西出队列再入队列
//    这样可以保证队列的top就是刚刚加入的元素
    Queue<Integer> q = new LinkedList();
    // Push element x onto stack.
    public void push(int x) {
        q.add(x);
        int size = q.size();
        // "-- >" is not a new operator, meaning size minus minus greater than 1
        //same as for (i = 0; i < size - 1; i++)
        while(size-- > 1) {
            q.add(q.poll());
        }
        
    }

    // Removes the element on top of the stack.
    public void pop() {
        q.poll();
    }

    // Get the top element.
    public int top() {
        return q.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q.isEmpty();
    }
    public static void main(String args){
        MyStack ms = new MyStack();
    }
}
