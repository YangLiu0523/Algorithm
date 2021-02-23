package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/my-calendar-i/ => Like
 * Test: https://leetcode.com/problems/my-calendar-ii/ => Like
 * Test: https://leetcode.com/problems/my-calendar-iii/
 */

class MyCalendar {
    TreeMap<Integer, Integer> calendar;
    public MyCalendar() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start);
        Integer next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}

class MyCalendarTwo {
    TreeMap<Integer, Integer> map;
    public MyCalendarTwo() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int cnt = 0;
        for (int val : map.values()) {
            cnt += val;
            if (cnt >= 3) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}

class MyCalendarThree {
    TreeMap<Integer, Integer> delta;
    public MyCalendarThree() {
        delta = new TreeMap<>();
    }

    public int book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);
        int max = 0, cnt = 0;
        for (int num : delta.values()) {
            cnt += num;
            max = Math.max(max, cnt);
        }
        return max;
    }
}