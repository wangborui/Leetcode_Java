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
public class RemoveNthFromEnd {
         static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
         //my solution/ found solution(one pass)
          static ListNode removeNthFromEnd(ListNode head, int n) {
              if(head == null) return null;
              ListNode dummy = new ListNode(0);
              dummy.next = head;
              ListNode first = dummy;
              ListNode second =  dummy;
              
              for(int i =0; i < n; i ++){
                  first = first.next;
              }
              while(first.next != null){
                  first = first.next;
                  second = second.next;
              }
              second.next = second.next.next;
              return dummy.next;
          }
         //my solutions(recursion, not the best approach)
//        static ListNode removeNthFromEnd(ListNode head, int n) {
//        if(head == null || n == 0) return head;
//        remove(head, n);
//        return head;
//    }
//    static int remove(ListNode head, int n){
//        if(head.next == null){
//            if(n == 1) return -1;
//            return 1;
//        }
//        if(remove(head, n) == -1){
//            head.next = null;
//        }
//        int index = 1+remove(head, n);
//        if(index == n){
//            head.val = head.next.val;
//            head.next = head.next.next;
//        }
//        return index;
//    }
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
         ListNode result = removeNthFromEnd(head,3);
           while(result != null){
            StdOut.println(result.val);
            result = result.next;
        }
      
        
    }
}
