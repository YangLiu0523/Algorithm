package matieral.tree;
import matieral.common_use.*;

/**
 * Test: https://leetcode.com/problems/closest-binary-search-tree-value/
 * Test: https://leetcode.com/problems/count-complete-tree-nodes/ => Like
 */

public class TreeBinarySearch {
    public int closestValue(TreeNode root, double target) {
        double diff = Math.abs(root.val - target);
        if (root.val < target) {
            if (root.right != null) {
                int r = closestValue(root.right, target);
                double diff1 = Math.abs(r - target);
                if (diff < diff1) return root.val;
                else return r;
            }
            else return root.val;
        }
        else {
            if (root.left != null) {
                int l = closestValue(root.left, target);
                double diff2 = Math.abs(l - target);
                if (diff < diff2) return root.val;
                else return l;
            }
            else return root.val;
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int d = depth(root);
        if (d == 0) return 1;

        int left = 1, right = (int)Math.pow(2, d) - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (exists(mid, d, root)) left = mid + 1;
            else right = mid - 1;
        }

        return (int)Math.pow(2, d) - 1 + left;
    }

    private boolean exists(int idx, int d, TreeNode root) {
        int left = 0, right = (int)Math.pow(2, d) -1;
        for (int i = 0; i < d; i++) {
            int mid = left + (right - left) /2 ;
            if (idx <= mid) {
                right = mid;
                root = root.left;
            }
            else {
                left = mid + 1;
                root = root.right;
            }
        }
        return root != null;
    }
    public int depth(TreeNode root) {
        int d = 0;
        while (root.left != null) {
            root = root.left;
            d++;
        }
        return d;
    }
}
