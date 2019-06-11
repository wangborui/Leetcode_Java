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
 * June 10, 2019
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * Intuition:
 * If we have both T and H pointers starting at the head of the linked list, traveling at different speed(T = T.next; H = H.next.next), and there
 * are cycles in the linked list, then T and H will eventually meet inside the cycle at point M.
 *
 * If T and H pointers meet and find cycles in the linked list, then where does the cycle start?
 *
 * Graphical:
 *  * D: the distance a pointer needs to traver before entering the cycle nodes
 *  * K: the distance pointer H and T travel from the start of cycle to meet for the first time
 *  * C: the length of cycle
 *  * i: the number of cycles T pointer travels to be able to meet H pointer
 *  * j: the number of cycles H pointer travels to be able to meet H pointer
 *  * N: the total distance T pointer travels to meet H pointer
 *
 * +--------D-------><----------------K
 *                                    |
 * +--+    +--+    +--+   +--+        |
 * | 1+----> 2+--->+ 3+-->+ 4+-----+  |
 * +--+    +--+    +--+   +--+     |  |
 *                   ^             |  |
 *                   |           +-++ |
 *                 +-++     C    | 5| M
 *                 | 7|          +-++
 *                 +-++            |
 *                   ^    +--+     |
 *                   +----+ 6+<----+
 *                        +--+
 *
 * Proof 1: Where is the starting point for the cycle?
 * Total distance traveled by T and H pointer:
 *      T: N  = D + k + C(i)
 *      H: 2N = D + k + C(j)
 *
 *      If T travels a total 2N distance, then H and T will again meet at the same point M, and the starting point of
 *      the cycle is M - k, and M can be accomplished with many cycles of C(i or j)
 *
 *      2T = H
 *      2(D + k + C(i)) = D + k + C(j)
 *      2D + 2k + 2Ci   = D + k + Cj
 *      D + k           = Cj - 2Ci
 *      D               = C(j - 2i) - k
 *
 * Analysis:
 * C(j - 2i) - k is the starting point of the cycle, because C(j - 2i) is many times of the complete cycle(point M), which
 * can also be achieved by traveling D distance to find the beginning of the cycle.
 * Note that this equation holds true if D = 0, meaning the entire linked list is a cycle, and H and T pointers will always
 * meet at the beginning of the cycle. becuase the Cycle has length C, H travels 2 steps, and T travels 1, it will take C
 * steps for H pointer to overlap T pointer.
 *
 *
 *
 *
 * Reference:
 * https://www.youtube.com/watch?v=-YiQZi3mLq0
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
