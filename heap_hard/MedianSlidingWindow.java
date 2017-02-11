//Source : https://leetcode.com/problems/sliding-window-median/
//Date   : 02/11/2017

/********************************************************************************
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
* You can only see the k numbers in the window. Each time the sliding window moves right by one position. 
* Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
*********************************************************************************/
package Leetcode_Java.heap_hard;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * @author Borui Wang
 */
//Analysis:
//
//This problem is similar to median in data stream
//we need a max heap and a min heap to find median number
//we maintain invariant: all number in max heap are smaller than min num in min heap
//the number of items in max heap is greater than or equal to min heap by at most 1
//
//Steps:
//
//    1.) first add k numbers into max and min heap and update result[0] as we add
//    2.) start moving the window, for each new number i, we add this number into max min heap, and remove num[i - k] from max min heap, update result[i - k + 1]
//    3.) return result
//
//Pitfalls: 
//
//    1.) when getting median of existing max min heap, the value could overflow(when max heap size equals min heap size, 
//        we need to get average of max from max heap min from min heap) cast it to double
//    2.) cover edge case when k = n, we update result[0] initially as we add k numbers into max min heap
//
//Time O(n * k) remove from heap take time O(k)   Space O(k) k is the window size
//
//Optimization:
//Using Hashheap: remove time O(log n) 
//Time O(O log k) Space O(k) k is the window size

public class MedianSlidingWindow {
   static PriorityQueue<Integer> minQ = new PriorityQueue();
   static PriorityQueue<Integer> maxQ = new PriorityQueue(Collections.reverseOrder());
    static double[] medianSlidingWindow(int[] nums, int k) {
        //edge case, this will not happen
        if(nums == null || nums.length == 0 || k > nums.length) {
            return new double[0];
        }
        double [] res = new double[nums.length - k + 1];
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            //add all numbers before k into heap, and update result[0] to avoid edge case
            if(i <= k - 1) {
                addNum(nums[i]);
                res[0] = getMedian();
            } else {
                addNum(nums[i]);
                removeNum(nums[i - k]);
                res[i - k + 1] = getMedian();
            }
//            System.out.println("maxQ: " + maxQ + " median: " + getMedian() + " minQ: " + minQ);
 
        }
        return res;
    }
    static double getMedian() {
        if(maxQ.isEmpty()) {
            return 0.0;
        }
        else if(maxQ.size() == minQ.size()) {
            //cast number to double, integer addition could overflow
            return ((double)maxQ.peek() + (double)minQ.peek()) / 2.0;
        } else {
            return (double)maxQ.peek();
        }
    }
    static void addNum(int num) {
      double median = getMedian();
      if(num <= median) {
          maxQ.add(num);
      } else {
          minQ.add(num);
      }
      if(maxQ.size() - minQ.size() == 2) {
          minQ.add(maxQ.poll());
      }
      if(maxQ.size() < minQ.size()) {
          maxQ.add(minQ.poll());
      }
    }
    static void removeNum(int num) {
        double median = getMedian();
        if(num <= median) {
            maxQ.remove(num);
        } else {
            minQ.remove(num);
        }
        
        if(maxQ.size() < minQ.size()) {
            maxQ.add(minQ.poll());
        }
        if(maxQ.size() - minQ.size() == 2) {
            minQ.add(maxQ.poll());
        }
    }
    static void printArray(double[] a) {
        for(double d : a) {
            System.out.print(d + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        //[1,3,-1,-3,5,3,6,7]
        //[1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
        //3
        printArray(medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
    }
}
