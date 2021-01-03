package matieral.graph.minimal_spanning_tree;

import java.util.*;
/**
 * add edge unless cycle. O(ElogE)
 *
 * Test: https://leetcode.com/problems/min-cost-to-connect-all-points/
 */

class UnionFind {
    int[] parent;
    int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int i) {
        int root = i;
        while (root != parent[root]) {
            root = parent[root];
        }

        while (i != root) {
            int tmp = parent[i];
            parent[i] = root;
            i = tmp;
        }
        return root;
    }

    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }

    public boolean union(int i, int j) {
        int rootI = find(i), rootJ = find(j);
        if (rootI == rootJ) return false;

        if (size[rootI] > size[rootJ]) {
            size[rootI] += size[rootJ];
            parent[rootJ] = rootI;
        }
        else {
            size[rootJ] += size[rootI];
            parent[rootI] = rootJ;
        }
        return true;
    }
}

class Edge{
    int u;
    int v;
    int w;
    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class Kruskal {
    private int dist(int[][] points, int i, int j) {
        return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        UnionFind uf = new UnionFind(n);
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pq.offer(new Edge(i, j, dist(points, i, j)));
            }
        }

        int mst = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (uf.isConnected(edge.u, edge.v)) continue;
            else {
                uf.union(edge.u, edge.v);
                mst += edge.w;
            }

        }
        return mst;
    }
}
