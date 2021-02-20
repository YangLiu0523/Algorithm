package matieral.search.topical_program;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/two-sum/
 * Test: https://leetcode.com/problems/two-sum-less-than-k/
 * Test: https://leetcode.com/problems/3sum/
 * Test: https://leetcode.com/problems/3sum-smaller/
 * Test: https://leetcode.com/problems/3sum-closest/
 * Test: https://leetcode.com/problems/4sum/
 * Test: https://leetcode.com/problems/4sum-ii/
 *
 * Test: KSum => Like
 * Test: KSumCount => Like
 */
public class KSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (cache.containsKey(target - nums[i])) {
                return new int[]{cache.get(target - nums[i]), i};
            }
            else {
                cache.put(nums[i], i);
            }
        }
        return new int[0];
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1] ) { // Error prone
                continue;
            }

            int l = i + 1, r = n - 1;
            while (l < r) {
                int val = nums[l] + nums[r];
                if (val + nums[i] < 0) {
                    l++;
                }
                else if (val + nums[i] > 0) {
                    r--;
                }
                else {
                    List<Integer> subRes = new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r]));
                    res.add(subRes);
                    l++;
                    while (l < r && nums[l] == nums[l - 1]) l++;
                }
            }

        }
        return res;
    }

    public int threeSumSmaller(int[] nums, int target) {
        int sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int s = nums[l] + nums[r] + nums[i];
                if (s < target) {   // Error prone
                    sum += (r - l);
                    l++;
                } else {
                    r--;
                }
            }
        }
        return sum;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closed = Integer.MAX_VALUE, n = nums.length;
        for (int i = 0; i < n; i++) {
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (closed == Integer.MAX_VALUE || Math.abs(sum - target) < Math.abs(closed - target)) {
                    closed = sum;
                }

                if (sum == target) {
                    return target;  // Return error once
                }
                else if (sum < target) {
                    l++;
                }
                else {
                    r--;
                }
            }

        }
        return closed;
    }

    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1, max = -1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum < k) {
                max = Math.max(max, sum);
                l++;
            }
            else {
                r--;
            }
        }
        return max;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int s = A[i] + B[j];
                map1.put(s, map1.getOrDefault(s, 0) + 1);
            }
        }

        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int s = C[i] + D[j];
                map2.put(s, map2.getOrDefault(s, 0) + 1);
            }
        }


        int ans = 0;
        for (int s1 : map1.keySet()) {
            if (map2.containsKey(-s1)) {
                ans += map1.get(s1) * map2.get(-s1);
            }
        }
        return ans;
    }

    // Time complexity O(n^(k-1))
    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
            return res;
        if (k == 2) {
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                for (List<Integer> set : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(set);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for (int i = start; i < nums.length; ++i) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i]) { // For duplicate
                if (s.contains(target - nums[i])) {
                    res.add(Arrays.asList(target - nums[i], nums[i]));
                }
            }
            s.add(nums[i]);
        }
        return res;
    }


    // divided 4 arrays into two equal groups, and processed each group independently.
    public int kSumCount(int[][] lists) {
        Map<Integer, Integer> m = new HashMap<>();
        addToHash(lists, m, 0, 0);
        return countComplements(lists, m, lists.length / 2, 0);
    }

    private void addToHash(int[][] lists, Map<Integer, Integer> map, int currList, int sum) {
        if (currList == lists.length / 2) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        else {
            for (int a : lists[currList]) {
                addToHash(lists, map, currList + 1, sum + a);
            }
        }
    }

    private int countComplements(int[][] lists, Map<Integer, Integer> m, int currList, int complement) {
        if (currList == lists.length) {
            return m.getOrDefault(complement, 0);
        }

        int cnt = 0;
        for (int a : lists[currList]) {
            cnt += countComplements(lists, m, currList + 1, complement - a);
        }
        return cnt;
    }
}
