package matieral.range_query;

/**
 * Test: https://leetcode.com/problems/range-sum-query-2d-mutable/
 */

public class FenwickTree2D {
    int[][] sums;
    public FenwickTree2D(int m, int n) {
        sums = new int[m + 1][n + 1];
    }

    private int lsb(int n) {
        return n & (-n);
    }

    public void update(int r, int c, int val) {
        for (int i = r; i < sums.length; i += lsb(i)) {
            for (int j = c; j < sums[0].length; j += lsb(j)) {
                sums[i][j] += val;
            }
        }
    }

    public int query(int r, int c) {
        int sum = 0;
        for(int i = r; i > 0; i -= lsb(i)) {
            for(int j = c; j > 0; j -= lsb(j)) {
                sum += sums[i][j];
            }
        }
        return sum;
    }
}