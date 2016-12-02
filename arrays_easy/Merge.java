/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class Merge {
    //answer found online 
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1, j=n-1, k=m+n-1;
        while (i>-1 && j>-1) nums1[k--]= (nums1[i]>nums2[j]) ? nums1[i--] : nums2[j--];
        while (j>-1)         nums1[k--]=nums2[j--];
        StdOut.println(Arrays.toString(nums1));
    }
//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        if(m == 0 || n == 0) return;
//        int []nums3 = new int[m+n];
//        int i,j,h;
//        i = h = j = 0;
//        while(i<m||j<n){
//            if(i>=m)nums3[h]=nums2[j++];
//            else if(j>=n)nums3[h]=nums1[i++];
//            else{
//                if(nums1[i]<=nums2[j]){
//                    nums3[h]=nums1[i++];
//                }else{
//                    nums3[h]=nums2[j++];
//                }
//            }
//            h++;
//        }
//        for(int c = 0; c < nums3.length; c++){
//            nums1[c] = nums3[c];
//        }
//        
//    }
    public static void main(String[] args){
        int []a={2,4,6,8,0,0,0};
        int []b ={7,9,11};
        new Merge().merge(a, 4, b, 3);
    }
}
