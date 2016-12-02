/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linked_list_easy;

/**
 *
 * @author Borui Wang
 */
public class RemoveElements {
     public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
     /*
     solution found
       public ListNode removeElements(ListNode head, int val) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode curr = head, prev = fakeHead;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            curr = curr.next;
        }
        return fakeHead.next;
    }
     */
     //my solution
     public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode walker = dummy;
        while(walker.next != null){
            if(walker.next.val == val){
                walker.next = walker.next.next;
            }
            else{
                walker = walker.next;
            }
        }
        return dummy.next;
    }
    public static void main(String[] args){
        
    }
}
