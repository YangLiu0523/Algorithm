package matieral.graph.shortest_path;

import java.util.*;
/**
 * Test: https://leetcode.com/problems/network-delay-time/
 * Test: https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * Reference: https://leetcode.com/problems/path-with-minimum-effort/discuss/921512/Java-Accepted-(232ms)-Optimized-Bellman-Ford-with-comments
 * Reference: https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
 *
 * DP
 * no negative cycles, shortest paths from src to all vertices
 *
 * Dijkstra's algorithm uses a priority queue to greedily select the closest vertex that has not yet been processed, and performs this relaxation process on all of its outgoing edges;
 * Bellman-Ford algorithm simply relaxes all the edges and does this ∣V∣−1 times, where ∣V∣ is the number of vertices in the graph.
 *
 * Initialize distTo[s] = 0 and distTo[v] = ∞ for all other vertices. Repeat V times: - Relax each edge.
 *
 * Detect negative cycle: If we iterate through all edges one more time and get a shorter path for any vertex, then there is a negative weight cycle
 *
 * Bellman-Ford is about relaxing each edge Total Nodes -1 times (relaxing edges in random order).
 * After Total Nodes -1 passes through all edges you will end up with shortest paths from source node to all the other nodes.
 *
 * O(VE)
 * In practice, the final distances can usually be found faster than in n−1 rounds.
 * Thus, a possible way to make the algorithm more efficient is to stop the algorithm if no distance can be reduced during a round.
 */

public class BellmanFord {
    public int networkDelayTime(int[][] times, int N, int K) {
        long[] dist = new long[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K - 1] = 0;

        for (int i = 0; i < N; i++) { // Node
            for (int[] edge : times) { // Edge
                int s = edge[0] - 1, d = edge[1] - 1;
                long w = edge[2];
                if (w + dist[s] < dist[d]) {
                    dist[d] = dist[s] + w;
                }
            }
        }

        long max = 0;
        for (int i = 0; i < dist.length; i++) {
            max = Math.max(max, dist[i]);
        }
        return (int)max == Integer.MAX_VALUE ? -1 : (int)max;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        long[][] distances = new long[2][n];
        Arrays.fill(distances[0], Integer.MAX_VALUE);
        Arrays.fill(distances[1], Integer.MAX_VALUE);
        distances[0][src] = distances[1][src] = 0;

        for (int i = 0; i < K + 1; i++) { // TIMES
            for (int[] edge: flights) {
                int s = edge[0], d = edge[1], wUV = edge[2];
                long dU = distances[1 - i&1][s];

                long dV = distances[i&1][d];
                if (dU + wUV < dV) {
                    distances[i&1][d] = dU + wUV;
                }
            }
        }
        return distances[K&1][dst] < Integer.MAX_VALUE ? (int)distances[K&1][dst] : -1;
    }
}
