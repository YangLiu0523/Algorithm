package matieral.graph;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/is-graph-bipartite/
 * Test: https://leetcode.com/problems/possible-bipartition/
 *
 * it is not a bipartite is there is a circle of odd edges
 */

public class Bipartite {
    int RED = 1, BLACK = -1, BLANK = 0;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (color[i] == BLANK) {
                stack.push(i);
                color[i] = RED;
                while (!stack.isEmpty()) {
                    int node = stack.pop();
                    for (int neighbor : graph[node]) {
                        if (color[neighbor] == BLANK) {
                            color[neighbor] = color[node] == RED ? BLACK : RED;
                            stack.push(neighbor);
                        }
                        else if (color[neighbor] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] dis : dislikes) {
            adj[dis[0]].add(dis[1]);
            adj[dis[1]].add(dis[0]);
        }

        int[] set = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if(set[i] == 0 && !dfs(adj, i, true, set)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(List<Integer>[] adj, int src, boolean isFirst, int[] set) {
        if(set[src] != 0) {
            return isFirst? set[src] == 1 : set[src] == -1;
        }

        set[src] = isFirst ? 1 : -1;

        for (int nei : adj[src]) {
            if (!dfs(adj, nei, !isFirst, set)) {
                return false;
            }
        }
        return true;
    }
}
