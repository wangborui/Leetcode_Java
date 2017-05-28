// Source : https://leetcode.com/problems/heaters/#/description
// Date   : 05/27/2017
/**
 * ********************************************************************************
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
 *
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.
 *
 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
 *
 * Note:
 * Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * As long as a house is in the heaters' warm radius range, it can be warmed.
 * All the heaters follow your radius standard and the warm radius will the same.
 * Example 1:
 * Input: [1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * Example 2:
 * Input: [1,2,3,4],[1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 *********************************************************************************
 */
package Leetcode_Java.binary_search_easy;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class Heaters {

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int res = 0;

        for(int house : houses) {
            int j = 0;
            while(j < heaters.length - 1 && Math.abs(house - heaters[j]) >= Math.abs(house - heaters[j + 1])) {
                j++;
            }
            res = Math.max(res, Math.abs(house - heaters[j]));
        }
        return res;
    }
    public static int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        
        int res = 0;
        
        for(int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if(index < 0) {
                index = -index - 1;
            }
            //????
            int dist1 = index - 1 < 0 ? Integer.MAX_VALUE : Math.abs(heaters[index - 1] - house);
            int dist2 = index < heaters.length ? Math.abs(heaters[index] - house) : Integer.MAX_VALUE;
            
            res = Math.max(res, Math.min(dist1, dist2));
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] h = {1,2,3,4,5};
        int[] t = {1,4};
        
        findRadius2(h,t);
    }
}
