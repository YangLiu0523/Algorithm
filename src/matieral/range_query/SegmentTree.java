package matieral.range_query;

/**
 * Ref: https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/
 * Test: https://leetcode.com/problems/range-sum-query-mutable/ => sum
 * Test: https://leetcode.com/problems/falling-squares/ => max
 *
 * Merge操作不仅在最后一步
 *
 * Both the read and update queries now take O(log(n))
 * Build: O(n)
 * Space: O(4*n)
 *
 * Reference: https://leetcode.com/problems/falling-squares/discuss/762418/The-Easiest-Java-Segmentation-Tree-with-Lazy-Propagation.
 * 以tree的形式完成
 */

class SegmentTree {
    int[] tree;
    int[] nums;
    int[] lazy;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        if (nums == null || nums.length == 0) return;
        tree = new int[4 * nums.length];
        lazy = new int[4 * nums.length];

        build(0, nums.length - 1, 0);
    }

    private void build(int lo, int hi, int treeIdx) {
        if (lo == hi) {
            tree[treeIdx] = nums[lo];
            return;
        }

        int mid = lo + (hi - lo) / 2;
        build(lo, mid, 2 * treeIdx + 1);
        build(mid + 1, hi, 2 * treeIdx + 2);

        tree[treeIdx] = tree[treeIdx * 2 + 1] + tree[treeIdx * 2 + 2];
    }

    public void update(int i, int val) {
//        updateSingleElement(i, val, 0, nums.length -1, 0);
        updateRangeLazy(i, i, val - nums[i], 0, nums.length -1, 0);   nums[i] = val;
    }

    private void updateRangeLazy (int i, int j, int diff, int lo, int hi, int treeIdx) {
        if (lazy[treeIdx] != 0) {
            tree[treeIdx] += (hi - lo + 1) * lazy[treeIdx];

            if (lo != hi) {
                lazy[2 * treeIdx + 1] += lazy[treeIdx];
                lazy[2 * treeIdx + 2] += lazy[treeIdx];
            }

            lazy[treeIdx] = 0;
        }

        if(i == lo && j == hi) { // Segment is fully within the update range
            tree[treeIdx] += (hi - lo + 1) * diff;
            if (lo != hi) {
                lazy[2 * treeIdx + 1] += diff;
                lazy[2 * treeIdx + 2] += diff;
            }
        }
        else {
            int mid = lo + (hi - lo) / 2;
            if (i > mid) updateRangeLazy(i, j, diff, mid + 1, hi, 2 * treeIdx +2);
            else if (j <= mid) updateRangeLazy(i, j, diff, lo, mid, 2 * treeIdx + 1);
            else {
                updateRangeLazy(i, mid,diff, lo, mid, 2 * treeIdx + 1);
                updateRangeLazy(mid + 1, j, diff, mid + 1, hi, 2 * treeIdx + 2);
            }

            tree[treeIdx] = tree[2 * treeIdx + 1] + tree[2 * treeIdx + 2];
        }
    }

    private void updateSingleElement(int i, int val, int lo, int hi, int treeIdx) {
        if (lo == hi) {
            tree[treeIdx] = val;
            return;
        }
        int mid = lo + (hi - lo)/ 2;
        if (i <= mid) updateSingleElement(i, val, lo, mid, treeIdx * 2 + 1);
        else updateSingleElement(i, val, mid + 1, hi, treeIdx * 2 + 2);

        tree[treeIdx] = tree[treeIdx * 2 + 1] + tree[treeIdx * 2 + 2];
    }

    public int sumRange(int i, int j) {
//        return sumRangeHelper(i, j, 0, nums.length - 1, 0);
        return queryLazySegTree(i, j,0,  nums.length - 1, 0);
    }

    private int sumRangeHelper(int i, int j, int lo, int hi, int treeIdx) {
        if (j < lo || i > hi || j < i) return 0;
        if (i == lo && j == hi) return tree[treeIdx];

        int mid = lo + (hi - lo) / 2;
        if(i > mid) return sumRangeHelper(i, j, mid + 1, hi, treeIdx * 2 + 2);
        else if (j <= mid) return sumRangeHelper(i, j, lo, mid, treeIdx * 2 + 1);
        else return sumRangeHelper(i, mid, lo, mid, 2 * treeIdx + 1) + sumRangeHelper(mid + 1, j, mid + 1, hi, 2 * treeIdx + 2);
    }

    private int queryLazySegTree(int i, int j, int lo, int hi, int treeIdx) {
        if (lo > j || hi < i) return 0;
        if (lazy[treeIdx] != 0) {
            tree[treeIdx] += (hi - lo + 1) * lazy[treeIdx];
            if (lo != hi) {
                lazy[2 * treeIdx + 1] += lazy[treeIdx];
                lazy[2 * treeIdx + 2] += lazy[treeIdx];
            }
            lazy[treeIdx] = 0;
        }

        if (i == lo && j == hi) {
            return tree[treeIdx];
        }
        else {
            int mid = lo + (hi - lo) / 2;
            if (i > mid) return queryLazySegTree(i, j, mid + 1, hi, 2 * treeIdx + 2);
            else if (j <= mid) return queryLazySegTree(i, j, lo, mid, 2 * treeIdx + 1);
            else return queryLazySegTree(i, mid, lo, mid, 2 * treeIdx + 1) + queryLazySegTree(mid + 1, j, mid + 1, hi, 2 * treeIdx + 2);
        }
    }
}