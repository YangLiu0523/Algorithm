package matieral.tree;
import java.util.*;
import matieral.common_use.TreeNode;
/**
 *  Test: https://leetcode.com/problems/tree-diameter/
 *  Test: https://leetcode.com/problems/distribute-coins-in-binary-tree/
 *  Test: https://leetcode.com/problems/find-distance-in-a-binary-tree/
 */

public class CalOneRetAnother {
    int dia = 0;
    public int treeDiameter(int[][] edges) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for(int[] e : edges) {
            tree.computeIfAbsent(e[0], v -> new ArrayList<>()).add(e[1]);
            tree.computeIfAbsent(e[1], v -> new ArrayList<>()).add(e[0]);
        }

        findMaxDep(0, -1, tree);
        return dia;
    }

    private int findMaxDep(int root, int parent, Map<Integer, List<Integer>> tree) {
        int maxD = 0;
        int first = 0, second = 0;
        if (!tree.containsKey(root)) return 0;

        for (int child : tree.get(root)) {
            if (child == parent) {
                continue;
            }

            int d = 1 + findMaxDep(child, root, tree);
            maxD = Math.max(maxD, d);
            if (d > first) {
                second = first;
                first = d;
            }
            else {
                second = d;
            }

            dia = Math.max(dia, second + first);
        }
        return maxD;
    }

    int sum = 0;
    public int distributeCoins(TreeNode root) {
        extra(root);
        return sum;
    }

    private int extra(TreeNode root) {
        if(root == null) return 0;
        int l = extra(root.left), r = extra(root.right);
        sum += Math.abs(l) + Math.abs(r);
        return l + r + root.val - 1;
    }


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
}
