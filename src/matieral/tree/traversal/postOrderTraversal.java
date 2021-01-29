package matieral.tree.traversal;
import matieral.common_use.TreeNode;

/**
 * Test: https://leetcode.com/problems/find-distance-in-a-binary-tree/
 * Test: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
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
}

class Result{
    TreeNode node;
    int dist;
    public Result(TreeNode node, int depth) {
        this.node = node;
        this.dist = depth;
    }
}
