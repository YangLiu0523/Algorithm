package matieral.patterns.greedy;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/course-schedule-iii/ => end & duration
 * Test: https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/ => start & end
 * Test: https://leetcode.com/problems/maximum-profit-in-job-scheduling/ => start & end & value
 * Test: https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/ => start & end & value & mission number
 * Test: https://leetcode.com/problems/meeting-rooms-ii/
 * Test: https://leetcode.com/problems/meeting-scheduler/
 */

public class Schedule {
    public int scheduleCourse(int[][] courses) {
        int n = courses.length;
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        int takenCourses = 0, time = 0;
        for (int i = 0; i < n; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                courses[takenCourses++] = courses[i];
            }
            else {
                int maxDuration = i;
                for (int j = 0; j < takenCourses; j++) {
                    if (courses[j][0] > courses[maxDuration][0]) {
                        maxDuration = j;
                    }
                }
                if (courses[maxDuration][0] > courses[i][0]) {
                    time -= (courses[maxDuration][0] - courses[i][0]);
                    courses[maxDuration] = courses[i];
                }
            }
        }
        return takenCourses;
    }

    public int maxEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (e1, e2) -> e1[0] - e2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int canAttend = 0, i = 0;
        for (int d = 1; d <= 100000; d++) {
            while (!pq.isEmpty() && pq.peek() < d) {
                pq.poll();
            }
            while (i < n && events[i][0] == d) {
                pq.offer(events[i++][1]);
            }
            if (!pq.isEmpty()) {
                pq.poll();
                canAttend++;
            }

        }
        return canAttend;
    }

    public int maxValue(int[][] events, int k) {
        int n = events.length;
        Arrays.sort(events, (e1, e2) -> e1[0] - e2[0]);
        int[][] dp = new int[n + 1][k + 1];
        for (int i = n - 1; i >= 0; i--) {
            int nextEvent = searchCeiling(events, events[i][1] + 1 , i + 1);
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], events[i][2] + dp[nextEvent][j - 1]);
            }
        }
        return dp[0][k];
    }

    private int searchCeiling(int[][] events, int time, int l) {
        int r = events.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int val = events[mid][0];
            if (val < time) l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        sortByStartTime(startTime, endTime, profit, 0, n - 1);

        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int next = findNext(startTime, endTime[i], i + 1);
            dp[i] = Math.max(dp[i + 1], profit[i] + dp[next]);
        }
        return dp[0];
    }

    private int findNext(int[] time, int target, int l) {
        int r = time.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (time[mid] <target) l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }

    private void sortByStartTime(int[] start, int[] end, int[] p, int l, int r) {
        if (l >= r) return;
        // System.out.println(l + " : " + r);
        int par = partition(start, end, p, l, r);
        // System.out.println(par);
        sortByStartTime(start, end, p, l, par - 1);
        sortByStartTime(start, end, p, par + 1, r);
    }

    private int partition(int[] s, int[] e, int[] p, int low, int high) {
        int target = s[low];
        int l = low + 1, r = high;
        while (true) {
            while (l <= high && s[l] < target) l++;
            while (r >= low && s[r] > target) r--;
            if (l >= r) break;

            swap(s, e, p, l, r);
            l++;
            r--;
        }
        swap(s, e, p, low, r);
        return r;
    }

    private void swap(int[] s, int[] e, int[] p, int i, int j) {
        helper(s, i, j);
        helper(e, i, j);
        helper(p, i, j);

    }
    private void helper(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

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

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) {
                pq.offer(slot);
            }
        }
        for (int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration) {
                pq.offer(slot);
            }
        }

        int[] prev = pq.poll();
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (prev[1] - curr[0] >= duration) {
                return new ArrayList<>(Arrays.asList(curr[0], curr[0] + duration));
            }
            prev = curr;
        }
        return new ArrayList<>();

    }
}
