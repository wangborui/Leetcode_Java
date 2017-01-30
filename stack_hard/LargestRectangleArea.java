/*
Source: https://leetcode.com/problems/largest-rectangle-in-histogram/
/********************************************************************************** 
 * 
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
 * find the area of largest rectangle in the histogram.
 * 
 *                    6          
 *                  +---+        
 *               5  |   |        
 *              +---+   |        
 *              |   |   |        
 *              |   |   |        
 *              |   |   |     3  
 *              |   |   |   +---+
 *        2     |   |   | 2 |   |
 *      +---+   |   |   +---+   |
 *      |   | 1 |   |   |   |   |
 *      |   +---+   |   |   |   |
 *      |   |   |   |   |   |   |
 *      +---+---+---+---+---+---+
 *      
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *      
 *      
 *                    6          
 *                  +---+        
 *               5  |   |        
 *              +-------|        
 *              |-------|        
 *              |-------|        
 *              |-------|     3  
 *              |-------|   +---+
 *        2     |-------| 2 |   |
 *      +---+   |-------|---+   |
 *      |   | 1 |-------|   |   |
 *      |   +---|-------|   |   |
 *      |   |   |-------|   |   |
 *      +---+---+---+---+---+---+
 *      
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * 
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 * 
 *               
 **********************************************************************************/

package Leetcode_Java.stack_hard;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 * As we know, the area =  width * height
 * For every bar, the 'height' is determined by the lowest bar
 * 
 * 1) We traverse all bars from left to right, maintain a stack of bars, and every bar is pushed to stack once.
 * 2) A bar is popped from stack when a bar of smaller height than top of the stack is seen.
 * 3) When a bar is popped, we calculate the area with the popped bar as the smallest bar.
 * 4) How do we get the left and right boundaries of the popped bar -
 *  the current index tells us the "right index" and the index of the previous item in stack is the "left index".
 * 
 * In other word, stack only stores bars with increasing heights, and all bars in stack have found their "left index" but not their "right index"
 * the "left index" of a bar is either the -1(stack is empty after popping bar) or stack top after popping bar
 * 
 * let's see some examples
 * 
 * Example 1
 * ------------
 * height = [1,2,3,4]
 * 
 *  stack[] = [0,1,2,3], i = 4
 *  1) pop 3, area = height[3] * 1 = 4
 *  2) pop 2, area = height[2] * 2 = 6
 *  3) pop 1, area = height[1] * 3 = 6
 *  4) pop 0, area = height[0] * 4 = 4
 * 
 * // Example 2
// ---------
// height = [2,1,2]
//
//    stack[] = [ 0 ], i=1
//    1) pop 0,  area = height[0] * 1 = 2
//
//    stack[] = [ 1,2 ], i=3, meet the end
//    1) pop 2,  area = height[2] * 1 = 2
//    2) pop 1,  area = height[1] * 3 = 3
//
//
// Example 3
// ---------
// height =  [4,2,0,3,2,5]  
//
//    stack[] = [ 0 ], i=1, height[1] goes down
//    1) pop 0,  area = height[0] * 1 = 4
//
//    stack[] = [ 1 ], i=2, height[2] goes down
//    1) pop 1,  area = height[1] * 2 = 4 // <- how do we know the left?
//                                              start from the 0 ?? 
//
//    stack[] = [ 2, 3 ], i=4, height[4] goes down
//    1) pop 3,  area = height[3] * 1 = 3
//    2) pop 2,  area = height[2] * ? = 0 // <- how do we know the left? 
//                                              start from the 0 ??
//
//    stack[] = [ 2,4,5 ], i=6,  meet the end
//    1) pop 5,  area = height[5] * 1 = 5
//    2) pop 4,  area = height[4] * 3 = 6 // <- how do we know the left?
//                                              need check the previous item.
//    3) pop 2,  area = height[2] * ? = 4 // <- how do we know the left?
//                                              start from the 0 ??
//
//    so, we can see, when the stack pop the top, the area formular is 
//
//          height[stack_pop] *  i - stack[current_top] - 1,   if stack is not empty
//          height[stack_pop] *  i,                            if stack is empty
 */
public class LargestRectangleArea {
    static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int n = heights.length;
        //stack of indexes
        Stack<Integer> stack = new Stack();
        int rightBound = 0;
        while (rightBound <= n) {
            int curHeight = (rightBound == n) ? -1 : heights[rightBound];
            //If stack is empty or current height is higher than the bar at top of stack, push index "i" to stack
            if (stack.isEmpty() || curHeight >= heights[stack.peek()]) {
                stack.push(rightBound++);
            } else {
            //If bar is smaller than top of stack, then keep removing top of stack while top of the stack is greater.
            //let the removed bar be minHeight, calculate area of rectangle with minHeight as smallest bar
            //for minHeight, the "left index" is previous (previous to top) item in the stack, or -1 if stack is empty
            //"right index" is right bound
                int minHeight = heights[stack.pop()];
                int leftBound =  stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(minHeight * (rightBound - leftBound - 1), maxArea);

            }
        }

        return maxArea;
    }
    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
        System.out.println(largestRectangleArea(new int[]{4,2,0,3,2,5}));
    }
}
