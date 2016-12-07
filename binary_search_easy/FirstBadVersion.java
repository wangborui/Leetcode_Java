/*
 * regardless n is even or odd, s = good, e = bad
 */
package Leetcode_Java.binary_search_easy;

/**
 *
 * @author Borui Wang
 */
public class FirstBadVersion {
//    public int firstBadVersion(int  n) {
//        int s = 1, e = n, res = e;
//        while(s + 1 < e) {
//            int mid = (s + e) >>> 1;
//            if(isBadVersion(mid)) {
//                e = mid;
//            }
//            else {
//                s = mid;
//            }
//        }
//        if(isBadVersion(e)) {
//            res = e;
//        }
//        if(isBadVersion(s)) {
//            res = s;
//        }
//        return res;
//    }
    
    static int firstBadVersion(int [] n) {
        int s = 0, e = n.length - 1, res = e;
        while(s + 1 < e) {
            int mid = (s + e) >>> 1;
            //8 is bad, 2 is good
            if(n[mid] == 8) {
                e = mid;
            }
            else {
                s = mid;
            }
        }
        if(n[e] == 8) {
            res = e;
        }
        if(n[s] == 8) {
            res = s;
        }
        return res;
    }
    public static void main(String[] args){
         // int [][] m = new int[][]{{-1},{-1}};
         
        System.out.println(firstBadVersion(new int[]{2,2,2,8,8,8,8}));
    }
}
