package two_pointers_medium;

/**
 * 05/26/2019
 * https://leetcode.com/problems/grumpy-bookstore-owner
 * Let us first begin by assuming there is no grumpy technique the bookstore owner can employ, and if this is the case then we just all up all customers when bookstore owner is **happy**, which we can use a variable called `happyCustSum` to add up all customers[i] such that grumpty[i] = 0, then the final `happyCustSum` is what we wanted.
 *
 * However, the problem told us that there is a grumpy techniques X the owner can use to keep customers happy, so we need to think about how to use that variable. What `X` really does is that it keep track of the customers that could have been happy during window X, but was not happy.
 *
 * ```
 * Input: customers = [1,0,1,2,1,1,7,5],
 *           grumpy = [0,1,0,1,0,1,0,1],
 * 			  X = 3
 * ```
 * Based on the problem statement, if we let X `techniqueWindow` to be the total number of **grumpy** times bookstore owner that **would** otherwise be happy, thus losing a customer.
 *
 * starting at 0[0] and ending at X - 1[2], we then have customers[1] = 0
 * starting at 0[1] and ending at X - 1[3], we then have customers[1] + customers[3] = 0 + 2 = 2
 * starting at 0[2] and ending at X - 1[4], we then have customers[3] = 2
 * ...
 *
 * If we finish iterating the array and calculate all such `techniqueWindow`, and there must be a `maxTechniqueWindow`  that is the greatest of them all.
 *
 * Because `happyCustSum` already kept track of the number of happy customers, and we find `maxTechniqueWindow` which keeps track of the**grumpy** times bookstore owner that **would** otherwise be happy,
 *
 * ```
 * class Solution {
 *     public int maxSatisfied(int[] customers, int[] grumpy, int X) {
 *         if(customers == null || customers.length == 0 || grumpy == null || grumpy.length == 0) {
 *             return 0;
 *         }
 *
 *         int happyCustSum = 0;
 *         int techniqueWindow = 0;
 *         int maxTechniqueWindow = 0;
 *
 *         for(int i = 0; i < customers.length; i++) {
 *             happyCustSum += (grumpy[i] == 0 ? customers[i] : 0);
 *             techniqueWindow += (grumpy[i] == 1 ? customers[i]: 0);
 *
 *             if(i >= X) {
 *                 techniqueWindow -= (grumpy[i - X] == 1 ? customers[i - X] : 0);
 *             }
 *             maxTechniqueWindow = Math.max(maxTechniqueWindow, techniqueWindow);
 *         }
 *
 *         return happyCustSum + maxTechniqueWindow;
 *     }
 * }
 * ```
 */
public class MaxSatisfied {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        if(customers == null || customers.length == 0 || grumpy == null || grumpy.length == 0) {
            return 0;
        }

        int happyCustSum = 0;
        int techniqueWindow = 0;
        int maxTechniqueWindow = 0;

        for(int i = 0; i < customers.length; i++) {
            happyCustSum += (grumpy[i] == 0 ? customers[i] : 0);
            techniqueWindow += (grumpy[i] == 1 ? customers[i]: 0);

            if(i >= X) {
                techniqueWindow -= (grumpy[i - X] == 1 ? customers[i - X] : 0);
            }
            maxTechniqueWindow = Math.max(maxTechniqueWindow, techniqueWindow);
        }

        return happyCustSum + maxTechniqueWindow;
    }
}
