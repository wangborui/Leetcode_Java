//Source : https://leetcode.com/problems/sliding-window-maximum/
//Date   : 02/11/2017
/**
 * ******************************************************************************
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 *
 * Note:
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Hint:
 *
 * How about using a data structure such as deque (double-ended queue)?
 * The queue size need not be the same as the window’s size.
 * Remove redundant elements and the queue should store only elements that need to be considered.
 ********************************************************************************
 */
package Leetcode_Java.heap_hard;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author Borui Wang
 */
public class MaxSlidingWindow {

    /**
     ***************************************************************************
     * Heap Solution O(n log k) O(k) 
     ***************************************************************************/
//    Analysis:
//    We need to find max value in a sliding window, what is the best way to achieve max value in a moving window/data stream?
//    Heap, because we need to keep track of total elements in the window and maintain its max value and we need to remove some values
//    
//    Steps:
//            
//    Create a max heap, to store all elements in window with size k
//    first add k elements into max heap, then set result[0] to be max in max heap
//    start sliding the window to the right, add new elements into max heap, and remove the i - kth element from max heap
//    set result[i - k + 1] = heap.poll(), return result

    private final PriorityQueue<Integer> maxQ = new PriorityQueue(Collections.reverseOrder());

    public int[] maxSlidingWindowHeap(int[] nums, int k) {
        //edge case, would not happen
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];

        for (int i = 0; i < n; i++) {
            if (i < k) {
                maxQ.add(nums[i]);
                res[0] = maxQ.peek();
            } else {
                maxQ.add(nums[i]);
                maxQ.remove(nums[i - k]);
                res[i - k + 1] = maxQ.peek();
            }
        }
        return res;
    }

    /**
     * *****************************************************************************
     * Deque(Double Ended Queue) Solution O(n) O(k)
     *******************************************************************************
     */
//    Create a deque to hold at most k elements in the window
//    when sliding the window to the right, we compare current number to the head of deque, 
//            if current number is greater than head of deque, remove head of deque until either deque is empty or head of deque is larger than current number
//    add current number to the deque
//    if tail of deque element has index equals i - k, then remove it
//    given [1,3,-1,-3,5,3] k = 3, node(index, value)
//    Index  0 1  2  3 4 5        
//    
//                                Head ...................Tail
//            n[0] = 1    deque = [1(0,1)]                        result = [1]
//            n[1] = 3    deque = [3(1,3)]                        result = [3]
//            n[2] = -1   deque = [3(1,3), -1(2,-1)]              result = [3]
//            n[3] = -3   deque = [3(1,3), -1(2,-1), -3(3,-3)]    result = [3,3]
//            n[4] = 5    deque = [5(4,5)]                        result = [3,3,5]
//            n[5] = 3    deque = [5(4,5), 3(5,3)]                result = [3,3,5,5]  
            
    static class Node {

        int index;
        int val;

        public Node(int i, int v) {
            this.index = i;
            this.val = v;
        }
    }

    static int[] maxSlidingWindowDeque(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Node> dq = new LinkedList();
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            while (!dq.isEmpty() && cur > dq.peekFirst().val) {
                dq.pollFirst();
            }
            dq.push(new Node(i, cur));
            if (i < k) {
                res[0] = dq.peekLast().val;
            } else {
                if (i - k == dq.peekLast().index) {
                    dq.pollLast();
                }
                res[i - k + 1] = dq.peekLast().val;
            }
        }
        return res;
    }

    static void printArray(int[] a) {
        for (int num : a) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        printArray(maxSlidingWindow(new int[]{11},1));
//        printArray(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3));
        printArray(maxSlidingWindowDeque(new int[]{1, -1}, 1));
    }
}
