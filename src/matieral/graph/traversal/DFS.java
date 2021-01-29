package matieral.graph.traversal;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/course-schedule/
 * Test: https://leetcode.com/problems/all-paths-from-source-to-target/
 */

public class DFS {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] p : prerequisites) {
            adj[p[1]].add(p[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] onStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (!dfs1(adj, visited, onStack, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs1(List<Integer>[] adj, boolean[] visited, boolean[] onStack, int course) {
        if (visited[course]) {
            return true;
        }
        if (onStack[course]) {
            return false;
        }

        onStack[course] = true;
        for (int neighbor : adj[course]) {
            if (!dfs1(adj, visited, onStack, neighbor)) {
                return false;
            }
        }

        visited[course] = true;
        return true;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> res = new ArrayList<>();
        dfs2(graph, 0, n - 1, new boolean[n], new ArrayList<>(), res);
        return res;
    }

    public void dfs2(int[][] adj, int src, int dest, boolean[] visited, List<Integer> sub, List<List<Integer>> res) {
        if (visited[src]) {
            return;
        }
        visited[src] = true;
        sub.add(src);

        if (src == dest) {
            res.add(new ArrayList<>(sub));
        }
        else {
            for (int nei : adj[src]) {
                dfs2(adj, nei, dest, visited, sub, res);
            }
        }

        sub.remove(sub.size() - 1);
        visited[src] = false;
    }
}
