package matieral.range_query;
import java.util.*;
/**
 * Reference: https://www.youtube.com/watch?v=WbafSgetDDk
 *
 * Test: https://leetcode.com/problems/range-sum-query-mutable/
 * Test: https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *
 * Build : O(n * log(n))
 * Others : O(log(n))
 * Space : O(n)
 */

public class FenwickTree {
    int[] sums;
    public FenwickTree(int n) {
        sums = new int[n + 1];
    }

    public void update(int i, int delta) {
        while (i < sums.length) {
            sums[i] += delta;
            i += (i & (-i)); // 注意这里是-不是～
        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += sums[i];
            i -= (i & (-i));
        }
        return sum;
    }
}


class NumArray {
    FenwickTree tree;
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
        tree = new FenwickTree(nums.length);
        for (int i = 0; i < nums.length; i++) {
            tree.update(i + 1, nums[i]);
        }

    }

    public void update(int index, int val) {
        tree.update(index + 1, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return tree.query(right + 1) - tree.query(left);
    }
}


class CountOfSmallerAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int rank = 1; // Important
        Map<Integer, Integer> ranks = new HashMap<>();
        for (int num : set) {
            ranks.put(num, rank++);
        }

        FenwickTree tree = new FenwickTree(set.size());
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int cnt = tree.query(ranks.get(nums[i]) - 1);
            res.add(0, cnt);
            tree.update(ranks.get(nums[i]), 1);
        }
        return res;
    }
}