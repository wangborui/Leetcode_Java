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
public class SwapPairs {
     static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
     /* solution found
       public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode first = head.next;
        ListNode nextSet = head.next.next;
        first.next = head;
        head.next = swapPairs(nextSet);
        return first;
    }
     */ 
     //iterative solutions
    static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        
        while(current.next != null && current.next.next != null){
            ListNode f = current.next;
            ListNode s = current.next.next;
            
            current.next = s;
            f.next = s.next;
            s.next = f;
            current = f;
        }
        return dummy.next;
    }
     //my solution
//    static ListNode swapPairs(ListNode head) {
//        ListNode walker = head;
//        ListNode jumper;
//        if(head == null || head.next == null) return head;
//        head = head.next;
//        
//        while(walker.next != null){
//            jumper = walker.next.next;
//            walker.next.next = walker;
//            walker.next = jumper;
//            if(jumper != null)
//                walker = jumper;
//        }
//        return head;
//    }
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        
        ListNode result = swapPairs(head);
        while(result != null){
            StdOut.println(result.val);
            result = result.next;
        }
        
    }
}
