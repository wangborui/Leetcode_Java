/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linked_list_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class Palindrome {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    //my solution and solution found
      static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode walker = head; ListNode jumper = head;
        while(jumper.next != null && jumper.next.next != null){
            walker = walker.next;
            jumper = jumper.next.next;
        }
        walker = reverseList(walker.next);
        
        while(walker!=null){
            if(walker.val != head.val) return false;
            else{
                walker = walker.next;
                head = head.next;
            }
        }
        return true;
    }
      static ListNode reverseList(ListNode head){
          if(head == null || head.next == null) return head;
          ListNode end  = reverseList(head.next);
          head.next.next = head;
          head.next = null;
          return end;
      }
// my solutions, fails on elements [1,2,2,2,1]
//    static boolean isPalindrome(ListNode head) {
//        if(head == null || head.next == null) return true;
//        Map<Integer,Boolean> map = new HashMap<Integer,Boolean>();
//        ListNode walker = head;
//        int counter =0;
//        while(walker != null){
//            if(map.containsKey(walker.val)){
//                map.put(walker.val, !map.get(walker.val));
//            }
//            else{
//                map.put(walker.val, false);
//            }
//            walker = walker.next;
//            counter++;
//        }
//        int half = counter/2;
//        walker = head;
//        for(int i = 0; i < half; i++,walker = walker.next){
//            if(!map.get(walker.val)) return false;
//        }
//        return true;
//    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        StdOut.println(isPalindrome(head));

    }
}
