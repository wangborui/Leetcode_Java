/*
Source: https://leetcode.com/problems/linked-list-cycle/
Date: 12/24/2016
********************************************************************************
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
********************************************************************************

Solution: use fast and slow pointers 

Time complexity : O(n) 

List has no cycle:
The fast pointer reaches the end first and the run time depends on the list's length, which is O(n).

List has a cycle:
fast and slow pointers will eventually meet

Space complexity : O(1). We only use two nodes (slow and fast) so the space complexity is O(1)O(1).
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class HasCycle {

    private class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    } 
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        
        do{
            slow = slow.next;
            fast = fast.next.next;
        } 
        while(fast != null && fast.next != null && fast != slow);
        
        return slow == fast;
    }

    public static void main(String[] args) {

    }
}
