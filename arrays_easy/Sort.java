/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.arrays_easy;

/**
 *
 * @author Borui Wang
 */
public class Sort {
    static class ListNode {
      int val;
      ListNode next;
      ListNode(int val) {
          this.val = val;
          this.next = null;
      }
  }
    static ListNode sortList(ListNode head) {  
        // write your code here
        if (head == null) return null;
        //ListNode dummy = new ListNode(0);
        qsort(head, null);
        return head;
    }
  
    
    static void qsort(ListNode start, ListNode end) {
        if (start == null || start == end) return;  
        
        ListNode fast = start, slow = start;
        int pivot = start.val;
      
        /* invariant
         [start,slow] <= pivot, (slow, fast) > pivot, [fast, end] unknown  */
      
      
      
        while (fast != end) {
            if (fast.val > pivot) {
              fast = fast.next;
            } else {
              if (fast != slow) {
                swap(fast, slow);
              }
              slow = slow.next;
              fast = fast.next;
            }
        }
      
        qsort(start, slow);
      
        //if (slow != null) {
        qsort(slow.next, end);
        //}
    }
  
    static void swap(ListNode p, ListNode q) {
      int tmp = p.val;
      p.val = q.val;
      q.val = tmp;
    }
    public static void main(String [] args){
        ListNode n = new ListNode(7);
        ListNode a= new ListNode(2);
        ListNode aa = new ListNode(4);
        ListNode b = new ListNode(5);
        
        n.next = a;
        a.next = aa;
        aa.next = b;
        sortList(n);
    }
}
