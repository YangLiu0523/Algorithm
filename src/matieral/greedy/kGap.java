package matieral.greedy;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/task-scheduler/
 * Test: https://leetcode.com/problems/reorganize-string/
 * Test: https://leetcode.com/problems/rearrange-string-k-distance-apart/
 */

public class kGap {
    public int leastInterval(char[] tasks, int n) {
        int[] freqs = new int[26];
        for (char task : tasks) {
            freqs[task - 'A']++;
        }
        Arrays.sort(freqs);

        int maxIdleTime = (freqs[25] - 1) * n;
        for (int i = 24; i >= 0 && maxIdleTime > 0; i--) {
            maxIdleTime -= Math.min(freqs[i], freqs[25] - 1);
        }

        return tasks.length + Math.max(0, maxIdleTime);
    }

    public String rearrangeString(String s, int k) {
        int[] freqs = new int[26];
        for (char c : s.toCharArray()) {
            freqs[c - 'a']++;
        }

        Queue<Integer> prev = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> freqs[b] - freqs[a]);
        for (int i = 0; i < 26; i++) {
            if (freqs[i] != 0) {
                pq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int idx = pq.poll();
            sb.append((char)('a' + idx));
            freqs[idx]--;
            prev.offer(idx);

            if (prev.size() >= k) {
                int prevIdx = prev.poll();
                if (freqs[prevIdx] != 0) {
                    pq.offer(prevIdx);
                }
            }
        }
        return sb.length() == s.length()? sb.toString() : "";
    }
}
