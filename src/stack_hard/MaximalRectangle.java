//Source : https://leetcode.com/problems/maximal-rectangle/?tab=Description
//Date   : 02/19/2017


/********************************************************************************** 
* 
* Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle 
* containing all ones and return its area.
*               
**********************************************************************************/
package Leetcode_Java.stack_hard;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class MaximalRectangle {
// The problem can be convert to the problem - "Largest Rectangle in Histogram"
//   1) we can take each row to calculate each row's histogram.
//   2) using the algorithm of "Largest Rectangle in Histogram" to find the largest area histogram.
//   3) tracking the maximal area.
//
// For the 1), it's easy. 
//     heights[j] = 0,                     if (matrix[i][j] == 0)
//     heights[j] = heights[j] + 1;,       if (matrix[i][j] == 1)
//
// For the 2), please referr to "Largest Rectangle in Histogram"
// 
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int[] height = new int[matrix[0].length];

        for (char[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                height[i] = row[i] == '0' ? 0 : height[i] + 1;
            }
            //find "largest area in rectangle"
            int area = calculateArea(height);
            max = Math.max(max, area);
        }
        return max;
    }

    private int calculateArea(int[] height) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack();
        int rightBound = 0;
        int n = height.length;

        while (rightBound <= n) {
            int rightHeight = rightBound == n ? -1 : height[rightBound];
            if (stack.isEmpty() || rightHeight >= height[stack.peek()]) {
                stack.push(rightBound++);
            } else {
                int minHeight = height[stack.pop()];
                int leftBound = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, minHeight * (rightBound - leftBound - 1));
            }
        }

        return maxArea;
    }
}
