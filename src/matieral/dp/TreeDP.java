package matieral.dp;
import matieral.common_use.TreeNode;
/**
 * Test: https://leetcode.com/problems/binary-tree-cameras/
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
}
