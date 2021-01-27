package matieral.graph.shortest_path;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
 *
 * Finds all shortest paths between the nodes in a single run
 *
 * First distances are calculated only using direct edges between the nodes
 * after this, the algorithm reduces distances by using intermediate nodes in paths
 *
 * On each round, the algorithm selects a new node that can act as an intermediate node in paths from now on
 *
 * O(n^3)
 */

public class FloydWarshall {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dists = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dists[i], Integer.MAX_VALUE);
            dists[i][i] = 0;
        }
        for (int[] edge : edges) {
            dists[edge[0]][edge[1]] = edge[2];
            dists[edge[1]][edge[0]] = edge[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int d = dists[i][k] == Integer.MAX_VALUE || dists[k][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dists[i][k] + dists[k][j];
                    dists[i][j] = Math.min(dists[i][j], d);
                }
            }
        }

        int node = -1, neighbors = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int tmp = 0;
            for (int j = 0; j < n; j++) {
                if (dists[i][j] <= distanceThreshold) {
                    tmp++;
                }
            }

            if (node == -1 || tmp <= neighbors) {
                neighbors = tmp;
                node = i;
            }
        }
        return node;
    }
}
