package matieral.graph;

/**
 * Test using : https://leetcode.com/problems/redundant-connection/
 *
 *  Union operation takes essentially constant time[1] when UnionFind is implemented with both path compression and union by rank.
 *
 *  写unionfind的时候一定要注意，不可以调用uf.parent[i]去查询一个值, 应该使用uf.find(i)
 */

public class UnionFind {
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
