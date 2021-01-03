package matieral.tree;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/merge-intervals/
 *
 * 变体： https://leetcode.com/problems/falling-squares/
 * sum 变 max, update delta 变 update value
 *
 * Reference: https://leetcode.com/problems/falling-squares/discuss/762418/The-Easiest-Java-Segmentation-Tree-with-Lazy-Propagation.
 * 以tree的形式完成
 */

class IntervalTreeNode {
    int start;
    int end;
    int mid;
    IntervalTreeNode left;
    IntervalTreeNode right;

    public IntervalTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.mid = start + (end - start) / 2;
        this.left = null;
        this.right = null;
    }

    public void add(int s, int e) {
        if (e < this.mid) {
            if (this.left == null) this.left = new IntervalTreeNode(s, e);
            else this.left.add(s, e);
        }
        else if (s > this.mid) {
            if (this.right == null) this.right = new IntervalTreeNode(s, e);
            else this.right.add(s, e);
        }
        else {
            this.start = Math.min(s, this.start);
            this.end = Math.max(e, this.end);
        }
    }

    public List<int[]> query() {
        List<int[]> leftIntervals = this.left == null ? new ArrayList<>() : this.left.query();
        List<int[]> rightIntervals = this.right == null ? new ArrayList<>() : this.right.query();

        List<int[]> res = new ArrayList<>();
        boolean insertRoot = false;

        for (int[] lres : leftIntervals) {
            if (lres[1] < this.start) {
                res.add(lres);
            }
            else {
                this.start = Math.min(this.start, lres[0]);
            }
        }

        for (int[] rres : rightIntervals) {
            if (rres[0] > this.end) {
                if (!insertRoot) {
                    res.add(new int[]{this.start, this.end});
                    insertRoot = true;
                }
                res.add(rres);
            }
            else {
                this.end = Math.max(this.end, rres[1]);
            }
        }

        if (!insertRoot) res.add(new int[]{this.start, this.end});
        return res;
    }
}
