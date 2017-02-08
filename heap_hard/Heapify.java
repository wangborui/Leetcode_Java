//Source : http://www.lintcode.com/en/problem/heapify/
//Date   : 02/07/2017
//******************************************************************************
//Given an integer array, heapify it into a min-heap array.
//
//For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
//Have you met this question in a real interview? Yes
//Clarification
//What is heap?
//
//Heap is a data structure, which usually have three methods: push, pop and top. 
//where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.
//
//What is heapify?
//Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
//
//What if there is a lot of solutions?
//Return any of them.
//Example
//Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.
//
//Challenge 
//O(n) time complexity
//******************************************************************************
package Leetcode_Java.heap_hard;

/**
 *
 * @author Borui Wang
 */
public class Heapify {

    /**
     * @param A: Given an integer array
     * @return: void
     */
//    Analysis:
//    We are trying to maintain a min heap, therefore, the parent nodes in heap must be smaller than or equal to both of its children
//    We start at the last non-leaf node of the heap to the head of heap, to perform sink operation(if parent is bigger than its children, swap parent node value with the smaller value of its 2 children)
//    We do not need to sink leaf nodes, because they do not have children, and they must be smaller than their children
//    If heap is stored as an array, then last non-leaf node must be the n / 2 - 1th node, why?
//    Because our heap is always a balanced binary tree
//            
//    for example
//                                                        1
//            heap[1,2,3,4] size  = 4                   /   \
//            n / 2 - 1 = 1                          [2]     3
//                                                   /
//                                                 4
//            
//                                                    [1]
//            heap[1,2,3] size  = 3                  /   \
//            n / 2 - 1 = 0                         2     3
//    
//    The time complexity is O(n) not O(n log n), why?
//    
//    for example, we have heap with height h, and level h - 1, h - 2,..., 2, 1
//                                    Level                                                 Max Nodes at level
//                                    h                              x                         2 ^ (h - h) = 2 ^ 0 = 1
//                                                                 /   \
//                                    h - 1                       x     x                      2 ^ (h - h + 1) = 2 ^ 1 = 2
//                                                               / \   / \
//                                    h - 2                     x   x x   x                    2 ^ (h - h + 2) = 2 ^ 2 = 4
//                                      .                     /\   /\ /\   /\
//                      h - (h - 1) = 1                      ..................                2 ^ (h - 1) = 2 ^ (h - 1)
//            
//    Therefore, at level k we have max nodes at level k = 2 ^ (h - k), and they all need to sink down, in worst case, k steps to reach level 1
//    So we have total operations for level k = 2 ^ (h - k) * k 
//    Total operations for entire heap is 
//            
//            2 ^ (h - 1) * 1 + 2 ^ (h - 2) * 2 + 2 ^ (h - 3) * 3 + ... + 2 ^ (1) * (h - 1) + 2 ^ (0) * (h)
//          = 2 ^ h * {1/2 + 2 / 2 ^ 2 + 3 / 2 ^ 3 + ... + (h - 1) / 2^ (h - 1) + h / 2^h}
//          = 2 ^ h * C(to take limit on the summation, mathmatical problems)
//          = 2 ^ (log n) * C
//          = n * C
//          = O(n)
    
//    So our time complexity for heapify is O(n) and Space is O(1)
    
                                             
    static void heapify(int[] A) {
        //start at the first non leave node
        for (int i = A.length / 2 - 1; i >= 0; i--) {
            sink(A, i);
        }
    }

    static void sink(int[] A, int idx) {
        int size = A.length;
        //make sure the children of current idx is smaller than size of A
        while (idx * 2 + 1 < size) {
            int child = idx * 2 + 1;
            if (idx * 2 + 2 < size && A[idx * 2 + 2] < A[child]) {
                child = idx * 2 + 2;
            }
            if (A[idx] <= A[child]) {
                break;
            }

            //swap idx with child
            swap(A, idx, child);
            idx = child;
        }
    }

    static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 4, 5};
        heapify(a);
    }
}
