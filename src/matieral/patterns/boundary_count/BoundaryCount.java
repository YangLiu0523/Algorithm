package matieral.patterns.boundary_count;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/my-calendar-ii/
 */

public class BoundaryCount {
    TreeMap<Integer, Integer> map;
    public BoundaryCount() {
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
