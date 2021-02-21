package matieral.search;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/n-queens/
 * Test: https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
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

    public int[] constructDistancedSequence(int n) {
        int[] ans = new int[2 * n - 1];
        boolean[] visited = new boolean[n + 1];
        backtrack(ans, 0, n, visited);
        return ans;
    }

    private boolean backtrack(int[] ans, int idx, int n, boolean[] visited) { // Error prone
        if (idx == ans.length) {
            return true;
        }
        if (ans[idx] != 0) {
            return backtrack(ans, idx + 1, n, visited);
        }
        for (int i = n; i >= 1; i--) {
            if (!visited[i]) {
                visited[i] = true;
                if (i != 1 && idx + i < ans.length && ans[idx + i] == 0) {
                    ans[idx] = ans[idx + i] = i;
                    if (backtrack(ans, idx + 1, n, visited)) {
                        return true;
                    }
                    ans[idx] = ans[idx + i] = 0;

                }
                else if (i == 1) {
                    ans[idx] = 1;
                    if (backtrack(ans, idx + 1, n, visited)) {
                        return true;
                    }
                    ans[idx] = 0;
                }
                visited[i] = false;
            }

        }
        return false;
    }
}
