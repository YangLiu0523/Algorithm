package matieral.patterns;

import java.util.*;
/**
 * Test: https://leetcode.com/problems/falling-squares/
 */

class SegmentTreeMax {
    int[] tree;
    int[] lazy;
    int n;
    public SegmentTreeMax(int n) {
        this.n = n;
        tree = new int[4 * n];
        lazy = new int[4 * n];
    }

    public void update(int i, int j, int val){
        updateHelper(i, j, 0, n - 1, val, 0);
    }

    private void updateHelper(int i, int j, int lo, int hi, int val, int treeIdx) {
        if (j < lo || i > hi) return;

        if (lazy[treeIdx] != 0) {
            tree[treeIdx] = Math.max(lazy[treeIdx], val); //错了
            if (lo != hi) {
                lazy[2 * treeIdx + 1] = Math.max(lazy[treeIdx], lazy[2 * treeIdx + 1]);
                lazy[2 * treeIdx + 2] = Math.max(lazy[treeIdx], lazy[2 * treeIdx + 2]);
            }
            lazy[treeIdx] = 0;
        }

        if (i == lo && j == hi) {
            tree[treeIdx] = Math.max(val, tree[treeIdx]);
            if (lo != hi) { // 错了一次
                lazy[treeIdx * 2 + 1] = Math.max(lazy[treeIdx * 2 + 1], val);
                lazy[treeIdx * 2 + 2] = Math.max(lazy[treeIdx * 2 + 2], val);
            }
        }
        else {
            int mid = lo + (hi - lo) / 2; //出错点
            if (j <= mid) updateHelper(i, j, lo, mid, val, 2 * treeIdx + 1);
            else if (i > mid) updateHelper(i, j, mid + 1, hi, val, 2 * treeIdx + 2);
            else {
                updateHelper(i, mid, lo, mid, val, 2 * treeIdx + 1);
                updateHelper(mid + 1, j, mid + 1, hi, val, 2 * treeIdx + 2);
            }
            tree[treeIdx] = Math.max(tree[2 * treeIdx + 1], tree[2 * treeIdx + 2]);
        }
    }

    public int query(int i, int j) {
        return queryHelper(i, j, 0, n - 1, 0);
    }

    private int queryHelper(int i, int j, int lo, int hi, int treeIdx) {
        if (i > hi || j < lo) return 0;

        if (lazy[treeIdx] != 0) {
            tree[treeIdx] = Math.max(lazy[treeIdx], tree[treeIdx]);
            if (lo != hi) {
                lazy[2 * treeIdx + 1] = Math.max(lazy[treeIdx], lazy[2 * treeIdx + 1]);
                lazy[2 * treeIdx + 2] = Math.max(lazy[treeIdx], lazy[2 * treeIdx + 2]);
            }
            lazy[treeIdx] = 0;
        }

        if (i == lo && j == hi) {
            return tree[treeIdx];
        }
        else {
            int mid = lo + (hi - lo) / 2;
            if (j <= mid) return queryHelper(i, j, lo, mid, 2 * treeIdx + 1);
            else if (i > mid) return queryHelper(i, j, mid + 1, hi, 2 * treeIdx + 2);
            else return Math.max( queryHelper(i, mid, lo, mid, 2 * treeIdx + 1),
                        queryHelper(mid + 1, j, mid + 1, hi,2 * treeIdx + 2));
        }
    }
}

class CoordinateCompression {
    public List<Integer> fallingSquares(int[][] positions) {
        Set<Integer> set = new HashSet<>();
        for (int[] p : positions) {
            set.add(p[0]);
            set.add(p[0] + p[1] - 1);
        }
        List<Integer> ordered = new ArrayList<>(set);
        Collections.sort(ordered);

        int idx = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : ordered) {
            map.put(num, idx++);
        }

        // int largest = map.get(set.size()); // 错了
        SegmentTreeMax tree = new SegmentTreeMax(set.size());

        List<Integer> res = new ArrayList<>();
        int maxHeight = 0;
        for(int[] p : positions) {
            int left = map.get(p[0]), right = map.get(p[0] + p[1] - 1);
            int h = tree.query(left, right) + p[1];
            tree.update(left, right, h);
            maxHeight = Math.max(maxHeight, h);
            res.add(maxHeight);
        }
        return res;
    }
}
