package matieral.tree;

import matieral.common_use.TreeNode;

import java.util.*;

/**
 * Test: https://leetcode.com/problems/binary-tree-cameras/
 * Test: https://leetcode.com/problems/sum-of-distances-in-tree/
 * Test: https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
 */

public class TreeDP {
    public int minCameraCover(TreeNode root) {
        int[] ans = solve(root);
        return Math.min(ans[1], ans[2]);
    }

    private int[] solve(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0, 99999}; // Important
        }
        int[] l = solve(node.left);
        int[] r = solve(node.right);
        int ml12 = Math.min(l[1], l[2]);
        int mr12 = Math.min(r[1], r[2]);

        int d0 = l[1] + r[1];
        int d1 = Math.min(l[2] + mr12, r[2] + ml12);
        int d2 = 1 + Math.min(l[0], ml12) + Math.min(r[0], mr12);
        return new int[]{d0, d1, d2};
    }

    int[] ans, count;
    List<Set<Integer>> graph;
    int n;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        this.n = N;
        ans = new int[n];
        count = new int[n];
        Arrays.fill(count, 1);
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        dfs(0, -1);
        dfs2(0, -1);

        return ans;
    }
    private void dfs(int node, int parent) {
        for (int child : graph.get(node)) {
            if (child != parent) {
                dfs(child, node);
                count[node] += count[child];
                ans[node] += ans[child] + count[child];
            }
        }
    }

    private void dfs2(int node, int parent) {
        for (int child: graph.get(node) ) {
            if (child != parent) {
                ans[child] = ans[node] - count[child] + n - count[child];
                dfs2(child, node);
            }
        }
    }

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        int[][] max = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = arr[i];
            max[i][i] = arr[i];
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0, j = i + l - 1; j < n; i++, j++) {
                max[i][j] = Math.max(max[i][j - 1], arr[j]);
                if (l == 2) dp[i][j] = arr[i] * arr[j];
                else {
                    for (int k = i; k < j; k++) {
                        int curr = dp[i][k] + dp[k + 1][j] + max[i][k] * max[k + 1][j];
                        if (k == i) curr -= arr[i];
                        if (k + 1 == j) curr -= arr[j];

                        dp[i][j] = Math.min(dp[i][j], curr);
                    }
                }

            }
        }
        return dp[0][n - 1];
    }
}
