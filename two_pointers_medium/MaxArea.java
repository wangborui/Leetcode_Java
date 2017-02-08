// Source : https://oj.leetcode.com/problems/container-with-most-water/
// Date   : 02/08/2017

/********************************************************************************** 
* 
* Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
* n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
* 
* Find two lines, which together with x-axis forms a container, such that the container contains the most water.
* 
* Note: You may not slant the container.
* 
*               
**********************************************************************************/
package Leetcode_Java.two_pointers_medium;

/**
 *
 * @author Borui Wang
 */
public class MaxArea {

    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // because the area is decided by the shorter edge
            // so we increase the area is to increase the shorter edge
            //
            //if height[left] < height[right] then left ++
            //if height[left] >= height[right] then right--;
            if (height[left] < height[right]) {
                maxArea = Math.max(maxArea, (right - left) * height[left]);
                left++;
            } else {
                maxArea = Math.max(maxArea, (right - left) * height[right]);
                right--;
            }
        }

        return maxArea;
    }
}
