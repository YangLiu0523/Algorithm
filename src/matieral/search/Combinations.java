package matieral.search;

import java.util.*;

/**
 * Test: https://leetcode.com/problems/combinations/
 * Test: https://leetcode.com/problems/combination-sum/
 * Test: https://leetcode.com/problems/combination-sum-ii/
 * Test: https://leetcode.com/problems/combination-sum-iii/
 * Test: https://leetcode.com/problems/combination-sum-iv/ => Like
 */

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        collect(n, k, 1, res, new ArrayList<>());
        return res;
    }

    private void collect(int n, int k, int start, List<List<Integer>> res, List<Integer> path) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (start > n) return ;

        for (int i = start; i <= n; i++) {
            path.add(i);
            collect(n, k, i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        solveComSum(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void solveComSum(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0 || start == candidates.length) return;

        for (int i = start; i < candidates.length; i++) {
            target -= candidates[i];
            path.add(candidates[i]);
            solveComSum(candidates, target, i, path, res);
            path.remove(path.size() - 1);
            target += candidates[i];
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        solveComSum2(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void solveComSum2(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0 || start == candidates.length) return;

        for (int i = start; i < candidates.length; i++) {
            if (i - 1 >= start && candidates[i - 1] == candidates[i]) continue;
            target -= candidates[i];
            path.add(candidates[i]);
            solveComSum2(candidates, target, i + 1, path, res);
            path.remove(path.size() - 1);
            target += candidates[i];
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        solve3(k, n, 1, new ArrayList<>(), res);
        return res;
    }

    private void solve3(int k, int n, int start, List<Integer> tmp, List<List<Integer>> res) {
        if (tmp.size() == k && n == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        if (tmp.size() >= k || n <= 0) return;
        for (int i = start; i<= 9; i++) {
            tmp.add(i);
            solve3(k, n - i, i + 1, tmp, res);
            tmp.remove(tmp.size() - 1);
        }
    }

    public int combinationSum4(int[] nums, int target) {
        // Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length && nums[j] <= i; j++) {
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
}
