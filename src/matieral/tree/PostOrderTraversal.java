package matieral.tree;
import matieral.common_use.TreeNode;

/**
 * Test: https://leetcode.com/problems/find-distance-in-a-binary-tree/
 */

public class PostOrderTraversal {
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
}

