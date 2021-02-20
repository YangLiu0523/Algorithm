package matieral.search;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/permutations/
 * Test: https://leetcode.com/problems/permutations-ii/
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
}
