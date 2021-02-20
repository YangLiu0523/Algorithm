package matieral.search.greedy;

/**
 * Test: https://leetcode.com/problems/car-pooling/
 * Test: https://leetcode.com/problems/meeting-rooms-ii/
 * Test: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * Test: https://leetcode.com/problems/non-overlapping-intervals/
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class ScheduleMinRooms {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> a - b);
        int rooms = 0;
        for (int[] inter : intervals) {
            if (!pq.isEmpty() && pq.peek() <= inter[0]) {
                pq.poll();
            }

            pq.offer(inter[1]);
            rooms = Math.max(rooms, pq.size());
        }
        return rooms;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> trips[a][2] - trips[b][2]);
        int load = 0;
        for (int i = 0; i < trips.length; i++) {
            while (!pq.isEmpty() && trips[pq.peek()][2] <= trips[i][1]) {
                load -= trips[pq.poll()][0];
            }
            pq.offer(i);
            load += trips[i][0];
            if (load > capacity) {
                return false;
            }
        }
        return true;
    }

    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int arrows = 1;
        Arrays.sort(points, (a, b)-> Integer.compare(a[1], b[1]));
        int prevEnd = points[0][1];
        for (int[] p : points) {
            int start = p[0], end = p[1];
            if (start > prevEnd) {
                arrows++;
                prevEnd = end;
            }
        }

        return arrows;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int removed = 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int prevEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prevEnd) {
                prevEnd = intervals[i][1];
            }
            else removed++;
        }
        return removed;
    }
}
