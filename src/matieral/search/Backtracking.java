package matieral.search;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/n-queens/
 */

public class Backtracking {
    boolean[] cols;
    boolean[] dia1;
    boolean[] dia2;
    int N;
    public List<List<String>> solveNQueens(int n) {
        N = n;
        cols = new boolean[n];
        dia1 = new boolean[2 * n];
        dia2 = new boolean[2 * n];

        List<List<String>> res = new ArrayList<>();
        char[] arr = new char[n];
        Arrays.fill(arr, '.');
        dfs(0, res, new ArrayList<String>(), arr);
        return res;
    }

    private void dfs(int r, List<List<String>> res, List<String> curr, char[] arr) {
        for (int c = 0; c < N; c++) {
            if (!cols[c] && !dia1[r - c + N - 1] && !dia2[r + c]) {
                visit(r, c);
                arr[c] = 'Q';
                curr.add(new String(arr));
                arr[c] = '.';
                if (r == N - 1) {
                    res.add(new ArrayList<>(curr));
                }else {
                    dfs(r + 1, res, curr, arr);
                }
                curr.remove(curr.size() - 1);
                back(r, c);
            }
        }
    }

    private void visit(int r, int c) {
        cols[c] = true;
        dia1[r - c + N - 1] = true;
        dia2[r + c] = true;
    }

    private void back(int r, int c) {
        cols[c] = false;
        dia1[r - c + N - 1] = false;
        dia2[r + c] = false;
    }
}
