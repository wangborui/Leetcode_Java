/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class GroupStrings {
    //simple solution found
      static List<List<String>> groupStrings(String[] strings) {
   	HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	for (String s : strings) {
		StringBuilder keyTemp = new StringBuilder();
		String key;
		for (int i = 1; i < s.length(); i++)
			keyTemp.append((s.charAt(i) - s.charAt(i - 1) + 26) % 26);
		key = keyTemp.toString();
		if (!map.containsKey(key))
			map.put(key.toString(), new ArrayList<String>());
		map.get(key.toString()).add(s);
	}
	return new ArrayList<List<String>>(map.values());
}
      /*
      public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strings) {
            int offset = str.charAt(0) - 'a';
            String key = "";
            for (int i = 0; i < str.length(); i++) {
                char c = (char) (str.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                key += c;
            }
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<String>();
                map.put(key, list);
            }
            map.get(key).add(str);
        }
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            Collections.sort(list);
            result.add(list);
        }
        return result;
    }
      */
    //my solution: did not work
//       public List<List<String>> groupStrings(String[] strings) {
//        if(strings == null || strings.length == 0) return new ArrayList<List<String>>();
//        List<List<String>> lists = new ArrayList<List<String>>();
//        Map<Integer, ArrayList<ArrayList<String>>> map = new HashMap<Integer,ArrayList<ArrayList<String>>>();
//        
//        for(String temp: strings){
//            if(map.containsKey(temp.length())){
//                ArrayList<ArrayList<String>> arrs = map.get(temp.length());
//                for(ArrayList<String> arr: arrs){
//                    String s = arr.get(0);
//                    int indexDif = (s.charAt(0)-temp.charAt(0))%26;
//                    for(int i = 0; i < s.length(); i++){
//                        if((s.charAt(i)-temp.charAt(i))%26 != indexDif){
//                            ArrayList<String> toAdd = new ArrayList<String>();
//                            toAdd.add(temp);
//                            arrs.add(toAdd);
//                            break;
//                        }
//                        else{
//                            
//                        }
//                    }
//                }
//                arrs.add(temp);
//                map.put(temp.length(), list);
//            }    
//            else{
//                ArrayList<ArrayList<String>> arrs = new ArrayList<ArrayList<String>>();
//                ArrayList<String> arr = new ArrayList<String>();
//                arr.add(temp);
//                arrs.add(arr);
//                map.put(temp.length(), arrs);
//            }
//        }
//        for(int i:map.keySet()){
//            lists.add(map.get(i));
//        }
//        return lists;
//    }
      public static void main(String[] args){
          String [] a = {"abc","bcd","acef","xyz","az","ba","a","z"};
          groupStrings(a);
          StdOut.println(-2%26);
      }
}
