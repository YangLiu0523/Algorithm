package matieral.range_query;

/**
 * Reference: https://www.youtube.com/watch?v=WbafSgetDDk
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
