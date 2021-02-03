package matieral.graph.traversal;
import java.util.*;
import matieral.common_use.*;

/**
 * Test: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Test: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 */

public class ConstructFromTwoOrder {
    int postOrderIdx;
    // The only information one needs from inorder - if the current subtree is empty (= return None) or not (= continue to construct the subtree).
    public TreeNode buildTreeFromInorderAndPostorder(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        int n = inorder.length;
        Map<Integer, Integer> inorderLoc = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inorderLoc.put(inorder[i], i);
        }
        postOrderIdx = n - 1;
        return build(0, n - 1, postorder, inorderLoc);
    }

    private TreeNode build(int inorderLeft, int inorderRight, int[] postorder, Map<Integer, Integer> inorderLoc) {
        if (inorderLeft > inorderRight) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postOrderIdx]);
        int inorderRootLoc = inorderLoc.get(postorder[postOrderIdx]);
        postOrderIdx--;
        root.right = build(inorderRootLoc + 1, inorderRight, postorder, inorderLoc);
        root.left = build(inorderLeft, inorderRootLoc - 1, postorder, inorderLoc);
        return root;
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return construct(pre, 0, post, 0, pre.length);
    }
    private TreeNode construct(int[] pre, int preStart, int[] post, int postStart, int N) {
        if (N == 0) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);
        if (N == 1) {
            return root;
        }

        int l = 1;
        for (; l < N; l++) {
            if (post[postStart + l - 1] == pre[preStart + 1]) {
                break;
            }
        }

        root.left = construct(pre, preStart + 1, post, postStart, l);
        root.right = construct(pre, preStart + l + 1, post, postStart + l, N - 1 - l);
        return root;
    }
}
