/*
Source: https://leetcode.com/problems/find-median-from-data-stream/
Date: 01/04/2017

********************************************************************************
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2
********************************************************************************
Heap insert time O(log n), delete O(log n), and there are n elements, therefore, 
we have total running time O(n log n)
 */
 
package Leetcode_Java.heap_hard;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * @author Borui Wang
 */
public class MedianFinder {

    //priority queue defaults to be min heap
    PriorityQueue<Integer> minHeap = new PriorityQueue();
    //Collections.reverseOrder() returns a comparator that reverses the natural ordering of collections
    PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());

    // Adds a number into the data structure.
    public void addNum(int num) {
        /**
         * Analysis: 
         * 1. max heap stores numbers less than or equal to the median 
         * 2. min heap stores numbers greater than median 
         * 3. make sure max element in max heap is always smaller than or equal to min element in min heap 
         * 4. invariant: maxHeap.size() - minHeap.size() = 1 or 0
         */
        //1)2)3)
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        //4)
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        //we have even numbers seen so far
        if (minHeap.size() == maxHeap.size()) {
            //0.5 automatically cast the int to double
            return (minHeap.peek() + maxHeap.peek()) * 0.5;
        } else {
            return maxHeap.peek();
        }

    }

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
}
