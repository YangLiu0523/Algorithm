package matieral.search;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/permutations/
 * Test: https://leetcode.com/problems/permutations-ii/
 * Test: https://leetcode.com/problems/next-permutation/
 * Test: https://leetcode.com/problems/permutation-sequence/
 */

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        List<List<Integer>> res= new ArrayList<>();
        permuteSolve(list, new LinkedList<>(), res);
        return res;
    }

    private void permuteSolve(List<Integer> nums, LinkedList<Integer> tmp, List<List<Integer>> res) {
        if (nums.size() == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.remove(i);
            tmp.add(num);
            permuteSolve(nums, tmp, res);
            tmp.removeLast();
            nums.add(i, num);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int num : nums) list.add(num);

        List<List<Integer>> res= new ArrayList<>();
        permuteUniqueSolve(list, new LinkedList<>(), res);
        return res;
    }
    private void permuteUniqueSolve(List<Integer> nums, LinkedList<Integer> tmp, List<List<Integer>> res) {
        if (nums.size() == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            if (i - 1 >= 0 && nums.get(i) == nums.get(i - 1)) {
                continue;
            }
            int num = nums.remove(i);
            tmp.add(num);
            permuteUniqueSolve(nums, tmp, res);
            tmp.removeLast();
            nums.add(i, num);
        }
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    int[] factorial;
    public String getPermutation(int n, int k) {
        k--;
        factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        backtrack(list, k, sb, n - 1);
        return sb.toString();
    }

    private void backtrack(List<Integer> list, int k, StringBuilder sb, int i) {
        if (list.isEmpty()) {
            return;
        }

        int group = k / factorial[i], rem = k % factorial[i];
        sb.append(list.get(group));

        list.remove(group);
        backtrack(list, rem, sb, i - 1);
    }
}
