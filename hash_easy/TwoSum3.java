/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class TwoSum3 {
    //my solution
//    Set<Integer> set = new HashSet<Integer>();
//    // Add the number to an internal data structure.
//	public void add(int number) {
//	    if(set.contains(number)){
//	        int temp = number + number;
//	        if(temp != 0){
//                    while(set.contains(temp)){
//                        temp += number;
//                    }
//                     set.add(temp);
//	        }
//	    }
//	    else{
//	        set.add(number);
//	    }
//	}
//
//    // Find if there exists any pair of numbers which sum is equal to the value.
//	public boolean find(int value) {
//	    if(set.size() == 1) return false;
//	    for(int n: set){
//	        int counter = value - n;
//	        if(set.contains(counter)) return true;
//	    }
//	    return false;
//	}
//        public static void main(String [] args){
//            TwoSum3 twoSum = new TwoSum3();
//            twoSum.add(0);
//            twoSum.add(0);
//            StdOut.print(twoSum.find(0));
//            
// twoSum.add(number);
// twoSum.find(value);
//        }
     Map<Integer, Integer> map = new HashMap<>();
     // Add the number to an internal data structure.
	public void add(int number) {
	    if(map.containsKey(number)){
	        map.put(number, 2);
	    }
	    else{
	        map.put(number, 1);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for(int num: map.keySet()){
	        int counter = value - num;
	        if(counter == num){
	            if(map.get(num) == 2) return true;

	        }else{
	            if( map.containsKey(counter)) return true;
	        }
	    }
	    return false;
	}
        public static void main(String [] args){
            TwoSum3 twoSum = new TwoSum3();
            twoSum.add(0);
            twoSum.add(-1);
            twoSum.add(1);
            twoSum.find(0);
        }
}
