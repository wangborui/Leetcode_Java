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
public class MergeTwoLists {
         static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
         /*
         recursive solution
         public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
}
         */
         //my solution passed(iterative)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        ListNode walker = dummy;
        while(l1 != null || l2 != null){
            if(l1 == null){
                walker.next = l2;
                l2 = l2.next;
            }
            else if(l2 == null){
                walker.next = l1;
                l1 = l1.next;
            }
            else{
                if(l1.val <= l2.val){
                    walker.next = l1;
                    l1 = l1.next;
                }else{
                    walker.next = l2;
                    l2 = l2.next;
                }
            }
            walker = walker.next;
        }
        return dummy.next;
    }
}
