/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class NumArray {
   
   int[] heap;
   int N, numsLen;
   public NumArray(int[] nums) {
       //int N = nums.length;
       numsLen = nums.length;
       if (numsLen <= 0) return;
       N = 1;
       while ( (numsLen - 1) / N > 0) 
           N = N << 1;
       N = N<<1; 
       N++;// heapsize = 2^a + 1, we use 1-based index here
       heap = new int[N];
       Arrays.fill(heap, 0);
       initBT(nums, heap, 0, numsLen  - 1,  1);
   }
   
   private void initBT(int[] nums, int[] heap, int lo, int hi, int index)
   {
       if (lo == hi) { heap[index] = nums[lo]; return;}
       int mid = (lo + hi)/2;
       initBT(nums, heap, lo, mid, 2*index);
       initBT(nums, heap, mid + 1, hi,  2*index + 1);
       heap[index] = heap[2*index] + heap[2*index + 1];
   }
   

   void update(int i, int v) {
       int leafIndex = (N - 1)/2 + i + 1;
       int delta = v - heap[leafIndex];
       int parent = leafIndex/2;
       while (parent > 0) {
           heap[parent] += delta;
           parent = parent / 2;
       }
   }
   
   public int sumRange(int i, int j) {
       if (numsLen == 0) return 0;
       return sumRangehelper(i, j, 0, numsLen - 1, 1);
   }
   
   private int sumRangehelper(int i, int j, int rangfrom, int rangeto, int root) {
       if (i <= rangfrom && j >= rangeto) return heap[root]; // totally overlaped
       int mid = (rangfrom + rangeto) >>> 1;
       if (i > rangeto || j < rangfrom) return 0; // non overlapped;
       
       //now partially overlap
       return sumRangehelper(i, j, rangfrom, mid, 2*root) + 
              sumRangehelper(i, j, mid + 1, rangeto, 2*root + 1);
       
   }
   public static void main(String [] args){
       // Your NumArray object will be instantiated and called as such:
 NumArray numArray = new NumArray(new int[]{0,2});
 System.out.println(numArray.sumRange(0, 1));
 numArray.update(1, 10);
 System.out.println(numArray.sumRange(1, 2));
   }
   
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
