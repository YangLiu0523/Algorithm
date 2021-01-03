package matieral.patterns.merging_intervals;
import  java.util.*;

/**
 * Test: https://leetcode.com/problems/merge-intervals/
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

class MergingInterval {
    public int[][] mergeStream(int[][] intervals) {
        IntervalTreeNode root = new IntervalTreeNode(intervals[0][0], intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            root.add(intervals[i][0], intervals[i][1]);
        }

        List<int[]> res = root.query();
        return res.toArray(new int[res.size()][2]);
    }


    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);

        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] > list.get(list.size() - 1)[1]) {
                list.add(intervals[i]);
            }
            else {
                int[] inter = list.get(list.size() - 1);
                inter[0] = Math.min(inter[0], intervals[i][0]);
                inter[1] = Math.max(inter[1], intervals[i][1]);
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}