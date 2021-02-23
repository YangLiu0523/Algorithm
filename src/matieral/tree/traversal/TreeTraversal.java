package matieral.tree.traversal;
import java.util.*;
import matieral.common_use.TreeNode;
/**
 * Test: https://leetcode.com/problems/course-schedule-iv/
 * Test: https://leetcode.com/problems/convert-bst-to-greater-tree/ => tricky
 */

public class TreeTraversal {
    boolean[][] isReachable;
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        isReachable = new boolean[n][n];

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int[] p : prerequisites) {
            adj[p[0]].add(p[1]);
        }

        for (int i = 0; i < n; i++) {
            connect(adj, i, i);
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(isReachable[q[0]][q[1]]);
        }
        return res;
    }
    private void connect(List<Integer>[] adj, int u, int v) {
        if (isReachable[u][v]) {
            return;
        }

        isReachable[u][v] = true;
        for (int w : adj[v]) {
            connect(adj, u, w);
        }
    }

    public TreeNode convertBST(TreeNode root) {
        traverse(root, 0);
        return root;

    }
    private int traverse(TreeNode root, int prev) {
        if (root == null) {
            return prev; // Error-prone
        }

        int right = traverse(root.right, prev);
        root.val += right;
        int left = traverse(root.left, root.val);
        return left; //Error prone
    }
}
