// Source : https://leetcode.com/problems/paint-fence/#/description
// Date   : 05/21/2017
/**
 * *************************************************************************************
 *
 * There is a fence with n posts, each post can be painted with one of the k colors.
 *
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 *
 * Return the total number of ways you can paint the fence.
 *
 * Note:
 * n and k are non-negative integers.
 **************************************************************************************
 */
package Leetcode_Java.dynamic_programming_easy;

/**
 *
 * @author Borui Wang
 */
public class PaintFence {
//    reference: https://segmentfault.com/a/1190000005740990
//    Because we cannot have three or more continuous post with the same color, we can have at most 2 continuous posts with the same color
//    
//    We are only concerned with the LAST 2 painted post and the new post
//            
//    Set two variables:
//    
//            same: the number of ways LAST 2 posts can be painted the same color
//            diff: the number of ways LAST 2 posts can be painted different color
//                    
//    Therefore, the total number of ways to paint the last post is "same" + "diff"
//    
//    In order to get the number of ways to paint the "third" post, we need to calculate the updated "same" and "diff"
//    
//    We have two circumstances:
//        
//        1.) Last two posts have the same color, therefore, the third post must have a new color, we have same * (k - 1) ways.
//            making the second and third post to have different colors, marking diff = same * (k - 1)
//        2.) Last two posts have different colors, we have the following options for the third post:
//            
//            a.) The third post has different color from the second post, we have diff * (k - 1) ways to paint the third post
//                making the second and third post having different colors, marking diff = diff * (k - 1)
//            b.) The third post has the same color as the second post, have diff * 1 ways to paint the third post, making the second
//                and third post have the same color, marking same = diff * 1
//        our updated diff = (same + diff) * (k - 1), same = diff
//        our total ways are diff + same
    public int numWays(int n, int k) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return k;
        } else if (n == 2) {
            return k * k;
        }
        //here n must be greater than 2
        if (k < 2) {
            return 0;
        }

        int same = k;
        int diff = k * (k - 1);
        int oldDiff = 0;

        for (int i = 3; i <= n; i++) {
            oldDiff = diff;
            diff = (same + diff) * (k - 1);
            same = oldDiff;
        }
        return same + diff;
    }
}
