package matieral.tree;
import matieral.common_use.TreeNode;

/**
 * Test: https://leetcode.com/problems/flip-equivalent-binary-trees/
 * Test: https://leetcode.com/problems/validate-binary-search-tree/
 */
public class Recursive {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        else if (root1 == null || root2 == null || root1.val != root2.val) return false;

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);

    }
    private boolean validate(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;
        if (lower != null && root.val <= lower || upper != null && root.val >= upper) {
            return false;
        }

        return validate(root.left, lower, root.val) && validate(root.right, root.val, upper);
    }
}
