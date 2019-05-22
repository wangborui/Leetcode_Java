/*
Source: https://leetcode.com/problems/linked-list-cycle-ii/
Date: 12/24/2016
********************************************************************************
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
********************************************************************************

Solution:
use a slow and fast pointer, that move one step and two steps at a time
if they meet, then there is a cycle in the list, move slow pointer to the beginning of the list
and move slow and fast one step at a time until they meet again, then that is where the cycle begins
if they don't meet, and we reached fast to a null node, then there is no cycle

Note:
slow and fast needs to start at head to simplify input cases
Input types:

Case 1: cycle starts in the middle of the list, and when slow and start ends they meet somewhere in the cycle
(H)
 1 - 2 - 3 - 4
         |   |
         6 - 5
Case 2: cycle starts at the head of the list, and when slow and start ends they meet at the head of the cycle
(H) 
 3 - 4
 |   |
 6 - 5
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class HasCycle2 {
    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    } 
   static ListNode slow, fast;
   static ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        if(findCycle(head) == false) {
            return null;
        }
        //covers two cases, 
        //1. when head = slow to start with, meaning cycle starts at head
        //2. cycle starts somewhere in list, and when head = slow, we found the start of cycle
        while(head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
   }
   static boolean findCycle(ListNode head) {
       slow = head;
       fast = head;
       
       do{
           slow = slow.next;
           fast = fast.next.next;
       } while(fast != null && fast.next != null && slow != fast);
       return slow == fast;
   }
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next =  b;
        b.next = a;
        System.out.print(detectCycle(a));
    }
}
