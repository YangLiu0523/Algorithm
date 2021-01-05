package matieral.graph;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/cracking-the-safe/
 * Test: https://www.educative.io/courses/coderust-hacking-the-coding-interview/k27X
 * (word chaining: eve, eat, ripe, tear)
 *
 * Reference: https://www.youtube.com/watch?v=iPLQgXUiU14&feature=emb_logo
 * https://www.youtube.com/watch?v=xR4sGgwtR2I
 * https://www.youtube.com/watch?v=8MpoO2zA2l4&t=218s
 *
 * Any connected directed graph where all nodes have equal in-degree and out-degree has an Euler circuit (an Euler path ending where it started.)
 * Hierholzer's algorithm: whenever there is an Euler cycle, we can construct it greedily.
 * Starting from a vertex u, we walk through (unwalked) edges until we get stuck. Because the in-degrees and out-degrees of each node are equal, we can only get stuck at u, which forms a cycle.
 * Post order is important
 *
 * To find the Euler circuit of the graph, we need to ensure these two points:
 * 1. The in-degree of every vertex is equal to its out-degree.
 * 2. There exists a cycle connecting all the vertices of the graph which starts and ends at the same vertex.
 */

class EulerCircuit {
    Set<String> seen;
    StringBuilder ans;

    public String crackSafe(int n, int k) {
        seen = new HashSet<>();
        ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb.append("0");
        }
        String start = sb.toString();

        dfs(start, k);
        ans.append(start);
        return new String(ans);
    }

    public void dfs(String node, int k) {
        for (int x = 0; x < k; x++){
            String nei = node + x;
            if (!seen.contains(nei)) {
                seen.add(nei);
                dfs(nei.substring(1), k);
                ans.append(x);
            }
        }
    }
}