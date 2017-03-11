// Source : https://leetcode.com/problems/remove-linked-list-elements/
// Date   : 03/10/2017

/********************************************************************************** 
 * 
 * Remove all elements from a linked list of integers that have value val.
 * 
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6,  val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 * 
 * Credits:Special thanks to @mithmatt for adding this problem and creating all test cases.
 *               
 **********************************************************************************/

package Leetcode_Java.linked_list_easy;

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
     //因为我们不知道在删除所有目标值的节点后最开始的节点是什么，所以需要建立虚拟节点
     //用一个移动指针来遍历整个链表，如果发现移动指针的后面节点是需要删除的目标节点，把移动指针的下一个节点定为需要删除节点的下个节点
     //一直删除，直到移动指针的下一个节点不是我们想要删除的目标节点为止，我们就可以把移动指针指向他自己的下一个节点
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
