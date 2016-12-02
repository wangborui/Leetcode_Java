/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary_search_easy;

/**
 *
 * @author Borui Wang
 */
public class Search {
//   static int bin_search(int A[], int left, int right, int k)
//{
//    while(left < right){
//        int mid = left +(right-left)/2;
//        if(A[mid]==k) return mid;
//        else if(A[mid] < k) left = mid + 1;
//        else right = mid - 1;
//    }
//    return -1;
//}
      static int bin_search(int A[], int left, int right, int k)
{
   /* while(left < right){
        int mid = left +(right-left)/2;
        if(A[mid] < k) left = mid + 1;
        else right = mid;
    }
    return left;*/
        while(left < right){
           int mid = (left+right)>>>1;
        if(A[mid] < k) left = mid + 1;
        else right = mid;
         
        }
        return left;
}
      public static void main(String[] args) {
        //System.out.println(bin_search(new int[]{0,1,2,3,5},0,4,4));
       /* System.out.println(  Integer.MAX_VALUE );
        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE  )) + " size: "+ Integer.toBinaryString((Integer.MAX_VALUE  )).length());
         System.out.println(  (Integer.MAX_VALUE +1 ) );
        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE +1 ) ) + " size: "+ Integer.toBinaryString((Integer.MAX_VALUE +1 ) ).length());
        System.out.println(  (Integer.MIN_VALUE  ) );
        System.out.println(Integer.toBinaryString((Integer.MIN_VALUE   ) ) + " size: "+ Integer.toBinaryString((Integer.MIN_VALUE   ) ).length());*/
         System.out.println( Integer.toBinaryString(-1).length() );
         System.out.println( Integer.toBinaryString(-1-1)  );
//        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE +2 )>>>1) + " size: "+ Integer.toBinaryString((Integer.MAX_VALUE +2 )>>>1).length());
//        System.out.println(  (Integer.MAX_VALUE +50 )>>>1);
//        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE +50 )>>>1) + " size: "+ Integer.toBinaryString((Integer.MAX_VALUE +50 )>>>1).length());
 
//        System.out.println((Integer.MAX_VALUE +3)>>>1 );
//        System.out.println((Integer.MAX_VALUE +4) );
//        System.out.println((Integer.MAX_VALUE +5) );
//        System.out.println((Integer.MAX_VALUE + 1)>>>1);
//        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE + 1)));
//        System.out.println((Integer.MAX_VALUE + 1)/2);
//        System.out.println(Integer.toBinaryString((Integer.MAX_VALUE + 1)/2) );
//         System.out.println(Integer.toBinaryString((Integer.MAX_VALUE + 1)>>>1) );
    }
}
