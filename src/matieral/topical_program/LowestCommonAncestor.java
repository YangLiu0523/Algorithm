package matieral.topical_program;
import matieral.common_use.TreeNode;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 * Test: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Test: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 * Test: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 * Test: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
 */

public class LowestCommonAncestor {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }

    public Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(null, 0);
        }
        Result l = dfs(root.left), r = dfs(root.right);

        if (l.dist > r.dist) return new Result(l.node, l.dist + 1);
        else if (r.dist > l.dist) return new Result(r.node, r.dist + 1);
        else return new Result(root, l.dist + 1);
    }

    int cnt = 0;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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

    public NodeP lowestCommonAncestor(NodeP p, NodeP q) {
        Set<NodeP> path = new HashSet<>();
        while (p != null) {
            path.add(p);
            p = p.parent;
        }

        while (q != null) {
            if (path.contains(q)) return q;
            q = q.parent;
        }
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root== null) return null;

        for (TreeNode node : nodes) {
            if (root.val == node.val) {
                return root;
            }
        }

        TreeNode left = lowestCommonAncestor(root.left ,nodes);
        TreeNode right = lowestCommonAncestor(root.right ,nodes);

        // These two line important
        if (left != null && right != null) return root;
        return left == null ? right : left;
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

class NodeP {
    public int val;
    public NodeP left;
    public NodeP right;
    public NodeP parent;
}