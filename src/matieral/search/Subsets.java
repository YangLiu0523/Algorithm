package matieral.search;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/subsets/
 * Test: https://leetcode.com/problems/factor-combinations/
 *
 */

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        subSetsSolve(ans, new ArrayList<>(), 0, nums);
        return ans;
    }
    private void subSetsSolve(List<List<Integer>> ans, List<Integer> path, int start, int[] nums) {
        if (start == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            subSetsSolve(ans, path, i + 1, nums);
            path.remove(path.size() - 1);
        }
        subSetsSolve(ans, path, nums.length, nums); // Important
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        getFactorsSolve(res, new ArrayList<>(), 2, n);
        return res;

    }
    private void getFactorsSolve(List<List<Integer>> res, List<Integer> tmp, int start, int n) {
        if (n == 1) {
            if (tmp.size() > 1) { // Important
                res.add(new ArrayList<>(tmp));
            }
            return;
        }

        for (int i = start; i <= n; i++) { // Important
            if (n % i == 0) {
                tmp.add(i);
                getFactorsSolve(res, tmp, i, n / i);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupSolve(ans, new ArrayList<>(), 0, nums);
        return ans;
    }

    private void subsetsWithDupSolve(List<List<Integer>> ans, List<Integer> path, int start, int[] nums) {
        if (start == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i - 1 >= start && nums[i - 1] == nums[i]) {
                continue;
            }
            path.add(nums[i]);
            subsetsWithDupSolve(ans, path, i + 1, nums);
            path.remove(path.size() - 1);
        }
        subsetsWithDupSolve(ans, path, nums.length, nums);
    }
}
