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
public class DeleteDuplicates {
  public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode walker = head;
        while(walker != null && walker.next != null){
            if(walker.next.val == walker.val){
                walker.next = walker.next.next;
            }else{
                walker = walker.next;
            }
        }
        return head;
    }
}
