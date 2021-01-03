package matieral.graph.shortest_path;

/**
 * Test: https://leetcode.com/problems/network-delay-time/
 *
 * 	Consider vertices in increasing order of distance from s (non- tree vertex with the lowest distTo[] value)
 */

import java.util.*;

public class Dijkstra {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            adj.put(i, new ArrayList());
        }

        for (int[] edge : times) {
            int src = edge[0], dest = edge[1], weight = edge[2];
            adj.get(src).add(new int[]{dest, weight});
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<int[]> queue = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]);
        queue.offer(new int[]{K, 0});
        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int node =info[0], time = info[1];
            if (dist[node] != Integer.MAX_VALUE) continue;

            dist[node] = time;
            List<int[]> neibors = adj.get(node);
            for (int[] neiInfo : neibors) {
                int nei = neiInfo[0], neiTime = neiInfo[1];
                if (neiTime + time < dist[nei]) {
                    queue.offer(new int[]{nei, neiTime + time});
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dist[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }
}
