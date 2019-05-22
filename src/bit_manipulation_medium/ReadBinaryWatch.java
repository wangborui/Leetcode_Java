/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bit_manipulation_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class ReadBinaryWatch {
    static List<String> readBinaryWatch(int num) {
        int [] watch = new int[]{60,120,240,480,1,2,4,8,16,32};
        boolean [] isOn = new boolean[watch.length];
        return combo(watch, isOn, new ArrayList(), num,0);
    }
    static List<String> combo(int[]watch, boolean[] isOn, List<String> res, int rem, int start){
        if(rem == 0){
            int min = 0;
            for(int i = 0; i < isOn.length;i++){
                if(isOn[i]) min+=watch[i];
            }
            res.add(min/60+":"+(min%60<10?"0":"")+min%60);
            return res;
        }
        for(int i = start; i < watch.length;i++){
            if(!isOn[i]){
                isOn[i] = true;
                combo(watch, isOn, res, rem-1,i+1);
                isOn[i] = false;
            }
        }
        return res;
    }
    public static void main(String[] args){
        System.out.println(readBinaryWatch(3));
    }
    
}
