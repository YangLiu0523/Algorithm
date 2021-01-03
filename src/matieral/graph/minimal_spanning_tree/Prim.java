package matieral.graph.minimal_spanning_tree;
import java.util.*;
/**
 * Start at 0, greedy grow tree, add to T the min weight edge one vertex in T until V - 1 edges
 * O(VlogV + ElogV) = O(ElogV)
 *
 * Test: https://leetcode.com/problems/min-cost-to-connect-all-points/
 *
 */
public class Prim {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean[] inCut = new boolean[n];
        inCut[0] = true;
        int comp = 1;

        int mst = 0;
        int lastUpdatedIdx = 0;
        while (comp != n) {
            int nextRelaxIdx = -1;
            for (int i = 0; i < n; i++) {
                if (inCut[i]) continue;

                int d = dist(points, i, lastUpdatedIdx);
                dist[i] = Math.min(d, dist[i]);

                if (nextRelaxIdx == -1 || dist[i] < dist[nextRelaxIdx]) {
                    nextRelaxIdx = i;
                }

            }

            inCut[nextRelaxIdx] = true;
            comp++;

            mst += dist[nextRelaxIdx];
            lastUpdatedIdx = nextRelaxIdx;
        }
        return mst;
    }

    private int dist(int[][] points, int i, int j) {
        return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }
}
