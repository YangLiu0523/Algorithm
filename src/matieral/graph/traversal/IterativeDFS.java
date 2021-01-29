package matieral.graph.traversal;
import matieral.common_use.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Test: https://leetcode.com/problems/recover-binary-search-tree/
 */
public class IterativeDFS {
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
    }
}

