package stack_medium;

import java.util.Date;
import java.util.List;
import java.util.Stack;

/**
 * Twitter Phone Interview
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=534045&hid=138230
 */
public class SpotifyUsers {
    //Time: O(n) Space: O(n)
    public class Entry{

        long timeStamp;
        String operation;
        int playerID;
    }
    public long duration(List<Entry> entries) {
        if(entries == null || entries.isEmpty()) {
            return 0;
        }

        long totalFamilyActiveTime = 0;
        //operations
        Stack<Entry> stack = new Stack();

        for(Entry e: entries) {
            if(e.operation.equals("Play")) {
                stack.push(e);
            } else {
                if(!stack.isEmpty()) {
                    Entry temp = stack.pop();
                    if(stack.isEmpty()) {
                        totalFamilyActiveTime += (e.timeStamp - temp.timeStamp);
                    }
                }
            }
        }

        //left over entrie
        while(!stack.isEmpty()) {

            Entry temp = stack.pop();
            totalFamilyActiveTime += new Date(2017, 1, 15).getTime() - temp.timeStamp;
        }

        return totalFamilyActiveTime;
    }


    //Time: O(n) Space: O(1)
    public long durationImproved(List<Entry> entries) {
        if(entries == null || entries.isEmpty()) {
            return 0;
        }

        long totalFamilyActiveTime = 0;
        //operations
        int count = 0;
        Entry start = null;
        for(Entry e: entries) {
            if(e.operation.equals("Play")) {
                if (count == 0) {
                    start = e;
                    count++;
                } else {
                    continue;
                }
            } else {
                if (--count == 0) {
                    totalFamilyActiveTime += (e.timeStamp - start.timeStamp);
                }
            }
        }

        //left over entrie
        while(count-- != 0) {
            totalFamilyActiveTime += new Date(2017, 1, 15).getTime() - start.timeStamp;
        }

        return totalFamilyActiveTime;
    }
}
