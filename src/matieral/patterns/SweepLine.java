package matieral.patterns;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/meeting-scheduler/
 *
 */
public class SweepLine {
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
