package matieral.tree.traversal;
import matieral.common_use.TreeNode;

/**
 * Ref: https://leetcode.com/problems/find-distance-in-a-binary-tree/discuss/1039737/Detailed-explanation-of-how-to-solve-in-ONE-pass-based-on-LCA-approach
 * Test: https://leetcode.com/problems/find-distance-in-a-binary-tree/
 * Test: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 * Test: https://leetcode.com/problems/distribute-coins-in-binary-tree/
 * Test: https://leetcode.com/problems/binary-tree-cameras/
 *
 * Test: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 * 
 */

public class postOrderTraversal {
    int dist = 0;
    public int findDistance(TreeNode root, int p, int q) {
        search(root, p, q);
        return dist;
    }

    private int search(TreeNode root, int p, int q) {
        if (root == null) {
            return -1;
        }

        int l = search(root.left, p, q);
        int r = search(root.right, p, q);
        if (l >= 0 && r >= 0) {
            dist = l + r + 2;
            return -1;
        }
        else if (root.val == p || root.val == q) {
            if (l >= 0 || r >= 0) {
                dist = Math.max(l, r) + 1;
                return -1;
            }
            else return 0;
        }
        else if (l >= 0 || r >= 0) {
            return Math.max(l, r) + 1;
        }

        return -1;
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

    int sum = 0;
    public int distributeCoins(TreeNode root) {
        excessNum(root);
        return sum;
    }

    private int excessNum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = excessNum(root.left);
        int r = excessNum(root.right);
        sum += Math.abs(l) + Math.abs(r); // Move to/from children
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

    int cnt = 0;
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = search(root, p, q);
        return cnt == 2 ? res : null;
    }

    private TreeNode search(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        TreeNode l = search(root.left, p, q);
        TreeNode r = search(root.right, p, q);

        if (root.equals(p) || root.equals(q)) {
            cnt++;
            return root;
        }
        else if (l != null && r != null) {
            return root;
        }
        return l != null ? l : r; //Important
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
