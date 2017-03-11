/*
Source: https://leetcode.com/problems/design-phone-directory/?tab=Description
Date: 03/10/2017

********************************************************************************
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);
********************************************************************************
 */
package Leetcode_Java.linked_list_medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class PhoneDirectory {
    //首先，我们需要添加可用号码到我们的字典里面去，并且记录添加顺序，这个特性可以用queue或者链表来实现
    //其次，我们需要查找已经添加的号码，用O(1)的时间，那么我们可以用hashset来记录
    //我们还需要release，那同样用queue和hashset先查找这个号码是否已经激活，如果已经激活就不需要再释放release了

    public PhoneDirectory() {
    }
    private Queue<Integer> q;
    private Set<Integer> set;

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone
     * directory.
     */
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList();
        set = new HashSet();
        for (int i = 0; i < maxNumbers; i++) {
            q.add(i);
            set.add(i);
        }
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (set.isEmpty()) {
            return -1;
        } else {
            int num = q.poll();
            set.remove(num);
            return num;
        }
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        return set.contains(number);
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        if (set.contains(number)) {
            return;
        } else {
            q.add(number);
            set.add(number);
        }
    }
}
