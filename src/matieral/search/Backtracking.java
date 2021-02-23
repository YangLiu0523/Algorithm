package matieral.search;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/n-queens/
 * Test: https://leetcode.com/problems/sudoku-solver/
 * Test: https://leetcode.com/problems/valid-sudoku/
 * Test: https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
 * Test: https://leetcode.com/problems/gray-code/
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
    int n = 9;
    Map<Integer, Set<Integer>> row;
    Map<Integer, Set<Integer>> col;
    Map<Integer, Set<Integer>> grid;

    public int group1(int i, int j) {
        int k = (int)Math.sqrt(n);
        return (i / k) * k + (j / k);
    }

    private void init(char[][] board) {
        row = new HashMap<>();
        col = new HashMap<>();
        grid = new HashMap<>();
        for (int i = 0; i < n; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            grid.put(i, new HashSet<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    int val = board[i][j] - '0';
                    row.get(i).add(val);
                    col.get(j).add(val);
                    grid.get(group(i, j)).add(val);
                }
            }
        }
    }
    private boolean backtrack(char[][] board, int id) {
        if (id == n * n) {
            return true;
        }

        int r = id / n, c = id % n;
        if (board[r][c] != '.') {
            return backtrack(board, id + 1);
        }

        for (int i = 1; i <= n; i++) {
            if (isValid(i, r, c)) {
                set(board, r, c, i);
                if (backtrack(board, id + 1)) {
                    return true;
                }
                reset(board, r, c, i);
            }
        }
        return false;
    }

    private void set(char[][] board, int r, int c, int val) {
        board[r][c] = (char)('0' + val);
        row.get(r).add(val);
        col.get(c).add(val);
        grid.get(group(r, c)).add(val);
    }

    private void reset(char[][] board, int r, int c, Integer val) {
        board[r][c] = '.';
        row.get(r).remove(val);
        col.get(c).remove(val);
        grid.get(group(r, c)).remove(val);
    }

    private boolean isValid(int i, int r, int c) {
        return !row.get(r).contains(i) && !col.get(c).contains(i) && !grid.get(group(r, c)).contains(i);
    }

    public void solveSudoku(char[][] board) {
        init(board);
        backtrack(board, 0);
        return;
    }


    public int group(int i, int j) {
        int k = (int)Math.sqrt(9);
        return (i / k) * k + (j / k);
    }

    public boolean isValidSudoku(char[][] board) {
        int n = 9;
        Map<Integer, Set<Integer>> row = new HashMap<>();
        Map<Integer, Set<Integer>> col = new HashMap<>();
        Map<Integer, Set<Integer>> grid = new HashMap<>();

        for (int i = 0; i < n; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            grid.put(i, new HashSet<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    int val = board[i][j] - '0';
                    if (row.get(i).contains(val)) return false;
                    row.get(i).add(val);
                    if (col.get(j).contains(val)) return false;
                    col.get(j).add(val);
                    if (grid.get(group(i, j)).contains(val)) return false;
                    grid.get(group(i, j)).add(val);
                }
            }
        }

        return true;
    }

    public List<Integer> grayCode(int n) {
        List<Integer>[] adj = new ArrayList[1 << n];
        for (int i = 0; i < 1 << n; i++) {
            adj[i] = new ArrayList<>();
            for (int j = 1; j < 1 << n; j <<= 1) {
                adj[i].add(i ^ j);
            }
        }

        boolean[] onStack = new boolean[1 << n];
        onStack[0] = true;

        List<Integer> res = new ArrayList<>();
        res.add(0);
        backtrack(adj, 0, onStack, res);
        return res;
    }

    private void backtrack(List<Integer>[] adj, int i, boolean[] onStack, List<Integer> res) {
        if (res.size() == adj.length) {
            return;
        }
        for (int nei : adj[i]) {
            if (!onStack[nei]) {
                res.add(nei);
                onStack[nei] = true;
                backtrack(adj, nei, onStack, res);
            }
        }
    }

}
