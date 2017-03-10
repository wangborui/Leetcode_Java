// Source : https://leetcode.com/problems/add-two-numbers-ii/?tab=Description
// Date   : 03/09/2017
/**
 * ********************************************************************************
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 *********************************************************************************
 */
package Leetcode_Java.linked_list_medium;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class AddTwoNumbers2 {

    /**
     * Definition for singly-linked list. public class ListNode { int val;
     * ListNode next; ListNode(int x) { val = x; } }
     */
    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    //这道题类似于addTwoNumbers 1，区别在于此题中两个需要相加的数字链表把最高位放在了链表开始
    //题目规定我们不能反转两个链表，如果可以的话，我们可以先反转两个链表，然后再用adTwoNumbers 1的解法
    //但是两个数字的加法必须从最低位开始运行，我们自然想到用栈这个数据结构来打辅助
    //首先我们需要建立虚拟链表，因为我们不知道两个数字的和的链表开始节点是什么
    //先把两个数字链表都访问一遍，把最高位放在栈的底端，最低位放在栈的顶端，分别存到两个栈里面，这样我们就可以从最低位对两个数字进行加法了
    //用一个sum变量来记录两个数字每一次相加后的结果，用while循环来访问两个栈，一直到两个栈都为空
    //在每一次做循环的时候把sum 除以10，用来判断上次一位数字的加法是否需要进位
    //把两个栈栈顶的节点弹出，相加存到sum变量里面，用sum % 10 来记录当前位相加的和，并插入到虚拟链表最前端
    //当循环结束的时候判断sum是否大于等于是，如果是的话补上一位1，并插入到虚拟链表最前端
    //最后返回虚拟链表第二个节点
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> num1 = new Stack();
        Stack<Integer> num2 = new Stack();
        ListNode dummy = new ListNode(-1);
        int sum = 0;

        while (l1 != null) {
            num1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            num2.push(l2.val);
            l2 = l2.next;
        }

        while (!num1.isEmpty() || !num2.isEmpty()) {
            sum /= 10;
            if (!num1.isEmpty()) {
                sum += num1.pop();
            }

            if (!num2.isEmpty()) {
                sum += num2.pop();
            }

            ListNode temp = new ListNode(sum % 10);
            temp.next = dummy.next;
            dummy.next = temp;
        }

        if (sum >= 10) {
            ListNode temp = new ListNode(1);
            temp.next = dummy.next;
            dummy.next = temp;
        }

        return dummy.next;
    }

}
