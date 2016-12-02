/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linked_list_easy;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class ReverseList {
   static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
         /*
         Iterative solution found
         public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode nextTemp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextTemp;
    }
    return prev;
}
   

         */
         //my solution
//    static ListNode reverseList(ListNode head) {
//        if(head == null || head.next == null) return head;
//        ListNode cur = head; ListNode next = head.next; ListNode prev = null;
//        while(next != null){
//            cur.next = prev;
//            prev = cur;
//            cur = next;
//            next = next.next;
//        }
//        cur.next = prev;
//        return cur;
//    }//my solution recursive /   recursive solution found
       static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
     public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        
         ListNode result = reverseList(head);
        while(result != null){
            StdOut.println(result.val);
            result = result.next;
        }
        
    }
}
