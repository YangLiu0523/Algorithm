package matieral.tree;
import java.util.*;
import matieral.common_use.TreeNode;
/**
 * Test: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Test:
 */
public class ConstructTree {
    int postOrderIdx;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
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


    Map<Integer, Integer> preLoc = new HashMap<>();
    int postIdx;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        for (int i = 0; i < pre.length; i++) {
            preLoc.put(pre[i], i);
        }
        postIdx = post.length - 1;
        return build(pre, post, 0, pre.length - 1);
    }
    private TreeNode build(int[] pre, int[] post, int left, int right) {
        if (left > right) return null;
        TreeNode root = new TreeNode(post[postIdx]);

        postIdx--;
        if (postIdx < 0) return root;

        root.right = build(pre, post, preLoc.get(post[postIdx]), right);
        if (postIdx < 0) return root;

        root.left = build(pre, post, left + 1, preLoc.get(post[postIdx]) - 1);
        return root;
    }
}
