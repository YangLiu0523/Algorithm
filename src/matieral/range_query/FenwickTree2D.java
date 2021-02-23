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

class NumMatrix {
    FenwickTree2D tree;
    int[][] matrix;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        this.matrix = matrix;
        int m = matrix.length, n = matrix[0].length;
        tree = new FenwickTree2D(m + 1, n + 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tree.update(i + 1, j + 1, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];

        tree.update(row + 1, col + 1, diff);
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // System.out.println(tree.query(row1, col1));
        return tree.query(row2 + 1, col2 + 1) + tree.query(row1, col1) - tree.query(row2 + 1 , col1) - tree.query(row1, col2 + 1);
    }
}