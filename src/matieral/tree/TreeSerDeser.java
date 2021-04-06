package matieral.tree;
import matieral.common_use.TreeNode;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/construct-binary-tree-from-string/
 * Test: https://leetcode.com/problems/construct-string-from-binary-tree/
 * Test: https://leetcode.com/problems/find-duplicate-subtrees/
 */

public class TreeSerDeser {
    public TreeNode str2tree(String s) {
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == ')') {
                if (sb.length() != 0) {
                    TreeNode node = new TreeNode(Integer.valueOf(sb.toString()));
                    sb = new StringBuilder();

                    if (!stack.isEmpty()) {
                        TreeNode parent = stack.peek();
                        if (parent.left == null) parent.left = node;
                        else parent.right = node;
                    }
                    stack.push(node);
                }

                if (c == ')') {
                    stack.pop();
                }
            }
            else {
                sb.append(c);
            }
        }

        if (sb.length() != 0) {
            return new TreeNode(Integer.valueOf(sb.toString()));
        }
        else {
            return stack.isEmpty() ? null : stack.pop();
        }
    }

    public String tree2str(TreeNode t) {
        if (t == null) return "";
        if (t.left == null && t.right == null) return t.val + "";
        if (t.right == null) return t.val + "(" + tree2str(t.left) + ")";
        return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans= new ArrayList<>();
        collect(root, new HashMap<>(), ans);
        return ans;
    }

    private String collect(TreeNode root, Map<String, Integer> map, List<TreeNode> ans) {
        if (root == null) {
            return "null ";
        }

        String s = "";
        if (root.left == null && root.right == null) {
            s = root.val + " ";
        }
        else{
            String l = collect(root.left, map, ans), r = collect(root.right, map, ans);
            s = root.val + " " + l + " "+ r + " ";
        }

        map.put(s, map.getOrDefault(s, 0) + 1);
        if (map.get(s) == 2) { // => Like
            ans.add(root);
        }
        return s;
    }
}
