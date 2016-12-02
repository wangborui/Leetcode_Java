/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class BullsCows {
    //my solution
//    public String getHint(String secret, String guess) {
//       ArrayList<Character> secSet = new ArrayList<Character>();
//       ArrayList<Character> gusSet = new ArrayList<Character>();
//        int bulls, cows; bulls = cows= 0;
//        for(int i = 0; i < secret.length(); i++){
//            char secVal = secret.charAt(i);
//            char gusVal = guess.charAt(i);
//            
//            if(secVal == gusVal) bulls++;
//            else {
//                secSet.add(secVal);
//                gusSet.add(gusVal);
//            }
//        }
//        for(char c:gusSet){
//            if(secSet.contains(c)){
//                cows++;
//                secSet.remove(secSet.indexOf(c));
//            }
//        }
//        return bulls+"A"+cows+"B";
//    }
    //solution found
    public String getHint(String secret, String guess) {
       int [] sec = new int[10];
       int [] gus = new int[10];
        int bulls, cows; bulls = cows= 0;
        for(int i = 0; i < secret.length(); i++){
            char secVal = secret.charAt(i);
            char gusVal = guess.charAt(i);
            
            if(secVal == gusVal) bulls++;
            else {
                sec[secVal - '0']++; 
                gus[gusVal - '0']++;
            }
        }
        for(int i  = 0; i < sec.length;i++){
            cows += Math.min(sec[i], gus[i]);
        }
        return bulls+"A"+cows+"B";
    }
}
