// Source : https://oj.leetcode.com/problems/trapping-rain-water/
// Date   : 02/08/2017
/**
 * ********************************************************************************
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 *     ^
 *     |
 *   3 |                       +--+
 *     |                       |  |
 *   2 |          +--+xxxxxxxxx|  +--+xx+--+
 *     |          |  |xxxxxxxxx|  |  |xx|  |
 *   1 |   +--+xxx|  +--+xxx+--+  |  +--+  +--+
 *     |   |  |xxx|  |  |xxx|  |  |  |  |  |  |
 *   0 +---+--+---+--+--+---+--+--+--+--+--+--+----->
 *       0  1   0  2  1   0  1  3  2  1  2  1
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case,
 * 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 *********************************************************************************
 */

/*
 * The idea is:
 *    1) have 2 pointers, left and right. left = 0, right = n - 1. find leftHeight and rightHeight at current index
 *    2) traverse the left pointer from left toward right,
 *       when find a bar lower than height of leftHeight, add the difference in height to total height, this is where water can be trapped in the graph
 *       when find a bar heigher than height of leftHeight, set leftHeight to the new height.  
 *    3) Compare current leftHeight and rightHeight, move the smaller pointer towards the larget one. do the same process for right pointer and rightHeight until "left" meets "right"
 *       
 *
 * The code below is quite clear!
 *
 */
package Leetcode_Java.two_pointers_hard;

/**
 *
 * @author Borui Wang
 */
public class TrapWater {

    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int left = 0;
        int leftHeight = height[left];
        int right = height.length - 1;
        int rightHeight = height[right];
        //total water this graph can trap
        int water = 0;

        while (left < right) {
            //right pointer move towards left
            if (leftHeight > rightHeight) {
                right--;
                if (height[right] < rightHeight) {
                    water += rightHeight - height[right];
                } else {
                    rightHeight = height[right];
                }
            } else { //left pointer move towards right
                left++;
                if (height[left] < leftHeight) {
                    water += leftHeight - height[left];
                } else {
                    leftHeight = height[left];
                }
            }
        }

        return water;
    }
}
