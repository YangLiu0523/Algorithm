package matieral.tree.traversal;
import matieral.common_use.TreeNode;

/**
 * Ref: https://leetcode.com/problems/find-distance-in-a-binary-tree/discuss/1039737/Detailed-explanation-of-how-to-solve-in-ONE-pass-based-on-LCA-approach
 * Test: https://leetcode.com/problems/find-distance-in-a-binary-tree/
 * Test: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 * Test: https://leetcode.com/problems/distribute-coins-in-binary-tree/
 * Test: https://leetcode.com/problems/binary-tree-cameras/
 * 
 */

public class postOrderTraversal {
    int ret;
    public int findDistance(TreeNode root, int p, int q) {
        dfs(root, p, q);
        return ret;
    }

    private int dfs(TreeNode root, int p, int q) {
        if (root == null) {
            return -1;
        }

        int left = dfs(root.left, p, q);
        int right = dfs(root.right, p, q);

        if (left >= 0 && right >= 0) {
            ret = left + right + 2;
            return -1;
        }
        else if (left < 0 && right < 0) {
            return root.val == p || root.val == q ? 0 : -1;
        }
        else if (root.val == p || root.val == q) {
            ret = right < 0 ? left + 1 : right + 1;
            return -1;
        }
        else {
            return right < 0 ? left + 1 : right + 1;
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }

    public Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(null, 0);
        }
        Result l = dfs(root.left), r = dfs(root.right);

        if (l.dist > r.dist) return new Result(l.node, l.dist + 1);
        if (r.dist > l.dist) return new Result(r.node, r.dist + 1);
        else return new Result(root, l.dist + 1);
    }

    int max = 0;
    public int distributeCoins(TreeNode root) {
        moves(root);
        return max;
    }

    private int moves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = moves(root.left);
        int r = moves(root.right);
        max += Math.abs(l) + Math.abs(r);
        return root.val + l + r - 1;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        if (left != null) return left;
        if (right != null) return right;

        return null;
    }

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

class Result{
    TreeNode node;
    int dist;
    public Result(TreeNode node, int depth) {
        this.node = node;
        this.dist = depth;
    }
}
