/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string_easy;

/**
 *
 * @author Borui Wang
 */
public class CompareVersion {
    public int compareVersion(String version1, String version2) {
        String [] one = version1.split("\\.");
        String [] two = version2.split("\\.");
        
        for(int i = 0; i < Math.max(one.length, two.length);i++){
            Integer num1 = i<one.length?Integer.parseInt(one[i]):0;
            Integer num2 = i<two.length?Integer.parseInt(two[i]):0;
            
            if(num1.compareTo(num2) !=0) return num1.compareTo(num2);
        }
        return 0;
    }
}
