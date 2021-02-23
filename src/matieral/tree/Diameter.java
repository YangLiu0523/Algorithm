package matieral.tree;
import matieral.common_use.*;
/**
 * Test: https://leetcode.com/problems/diameter-of-n-ary-tree/
 * Test: https://leetcode.com/problems/minimum-height-trees/
 * Test: https://leetcode.com/problems/tree-diameter/
 *
 * Two method:
 * 1. find first two longest pathes
 * 2. find the farthest node from a(any node), and use it as root rind next farthest
 */

public class Diameter {
    int max = 0;
    public int diameter(Node root) {
        if (root == null) return 0;
        depth(root);
        return max;
    }

    private int depth(Node root) {
        if (root.children.isEmpty()) return 0;

        int first = 0, second = 0;
        int dep = 0;
        for (Node child : root.children) {
            int d = depth(child) + 1;
            dep = Math.max(d, dep);
            if (d > first) {
                second = first;
                first = d;
            }
            else if (d > second) {
                second = d;
            }
        }
        max = Math.max(max, first + second);
        return dep;
    }
}
