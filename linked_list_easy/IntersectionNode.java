/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linked_list_easy;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class IntersectionNode {
      static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
      //  solution found
       public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
           if(headA == null || headB == null) return null;
           Set<ListNode> set = new HashSet<ListNode>();
        ListNode walkerA = headA;
        ListNode walkerB = headB;
        while(walkerA != null){
            set.add(walkerA);
            walkerA = walkerA.next;
        }
        while(walkerB != null){
            if(set.contains(walkerB))
                return walkerB;
            else{
                walkerB = walkerB.next;
            }
        }
        
           
           return null;
       }
      //my solution exceed time limit
//      public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        if(headA == null || headB == null) return null;
//        int countA, countB;
//        countA = countB = 0;
//        ListNode walkerA = headA;
//        ListNode walkerB = headB;
//        while(walkerA != null){
//            walkerA = walkerA.next;
//            countA++;
//        }
//        while(walkerB != null){
//            walkerB = walkerB.next;
//            countB++;
//        }
//        walkerA = headA;
//        walkerB = headB;
//        
//        if(countA > countB){
//            for(int i = 0; i < countA-countB; i++){
//                walkerA = walkerA.next;
//            }
//        }
//        else{
//            for(int i = 0 ;i < countB - countA; i++){
//                walkerB = walkerB.next;
//            }
//        }
//        
//        while(walkerA != null && walkerB != null){
//            if(walkerA.val == walkerB.val){
//                return walkerA;
//            }
//        }
//        return null;
//    }
}
