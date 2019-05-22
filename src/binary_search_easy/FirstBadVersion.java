/*
source: https://leetcode.com/problems/first-bad-version/

Conditions: this question assumes there is always one bad version in the batch
Inputs:
1. first one is bad B,B,B,B,B,B.....
2. second one is bad G,B,B,B,B,B....
3. bad in mid G,G,G,G,B,B,B...
4. last bad G,G,G,G,G,G,B...
5. just one entry B

*Solutions:
If we set left pointer to 1, right to n, and find mid = left + (right - left) / 2
*Invariant: 
left is always pointing to good version
right is always pointing to the first bad version
*Loop ending conditions:
while(left + 1 < right)
left and right pointers are the same when there is only one entry, terminate loop
find middle of left and right 
left is always next to right pointer when loop ends


 * 
 */
package Leetcode_Java.binary_search_easy;

/**
 *
 * @author Borui Wang
 */
public class FirstBadVersion {
    static int firstBadVersion(int [] n) {
        if(n[0] == 8) return 0;
        int s = 0, e = n.length - 1;
        //left is good
        //right is bad
        while(s + 1 < e) {
           //left is good
           //right is bad
            int mid = (s + e) >>> 1;
            //8 is bad, 2 is good
            if(n[mid] == 8) {
                e = mid;
            }
            else {
                s = mid;
            }
        }
        //left is good
        //right is first bad
        return e;
    }
    public static void main(String[] args){
         // int [][] m = new int[][]{{-1},{-1}};
         
        //System.out.println(firstBadVersion(new int[]{2,2,2,8,8,8,8}));
        System.out.println(firstBadVersion(new int[]{2,8,8,8,8}));
    }
}
