/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string_easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class GeneratePossibleNextMoves {
        public List<String> generatePossibleNextMoves(String s) {
       
        List<String> result = new ArrayList<>();
        for(int i = 0; i < s.length() - 1; i++){
            char[] temp = s.toCharArray();
            if(temp[i] == '+' && temp[i+1] == '+'){
                temp[i] = '-';
                temp[i+1] = '-';
                result.add(new String(temp));
            }
        }
        return result;
    }
        
}
