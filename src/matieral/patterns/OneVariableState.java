package matieral.patterns;

import java.util.TreeMap;

/**
 * Test: https://leetcode.com/problems/split-a-string-in-balanced-strings/
 * Test: https://leetcode.com/problems/my-calendar-ii/
 */
public class OneVariableState {
    public int balancedStringSplit(String s) {
        int state = 0, sub = 0;
        for (char c : s.toCharArray()) {
            if (c == 'R') state++;
            else state--;

            if (state == 0) {
                sub++;
            }
        }
        return sub;
    }

}
class MyCalender {
    TreeMap<Integer, Integer> map;
    public MyCalender() {
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