package matieral.minimax;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/jump-game/
 * Test: https://leetcode.com/problems/jump-game-ii/ => Like
 * Test: https://leetcode.com/problems/jump-game-iii/
 * Test: https://leetcode.com/problems/jump-game-iv/
 * Test: https://leetcode.com/problems/jump-game-vi/ => Like
 */

public class JumpGame {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j <= i + nums[i] && j < n; j++) {
                dp[i] |= dp[ j];
            }

        }
        return dp[0];
    }

    public int jump(int[] nums) {
        if (nums.length < 2) return 0;

        int maxPos = nums[0];
        int maxStep = nums[0];
        int jump = 1;
        for (int i = 1; i < nums.length; i++) {
            if (maxStep < i) {
                jump++;
                maxStep = maxPos;
                if (maxStep >= nums.length - 1) {
                    return jump;
                }
            }
            maxPos = Math.max(maxPos, i + nums[i]);
        }
        return jump;
    }

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        visited[start] = true;
        return backtrack(arr, visited, start);
    }

    private boolean backtrack(int[] arr, boolean[] visited, int start) {
        if (arr[start] == 0) {
            return true;
        }
        boolean res1 = false;
        int next1 = arr[start] + start;
        if (next1 >= 0 && next1 < visited.length && !visited[next1]) {
            visited[next1] = true;
            res1 = backtrack(arr, visited, next1);
        }

        if (res1) return true;

        int next2 = start - arr[start];
        if (next2 >= 0 && next2 < visited.length && !visited[next2]) {
            visited[next2] = true;
            return backtrack(arr, visited, next2);
        }
        return false;
    }

    public int minJumps(int[] arr) {
        if (arr.length < 2) return 0;

        Map<Integer, List<Integer>> equalValues = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            equalValues.computeIfAbsent(arr[i], v-> new ArrayList<>()).add(i);
        }

        Set<Integer> set1 = new HashSet<>();
        set1.add(0);
        Set<Integer> set2 = new HashSet<>();
        set2.add(arr.length - 1);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        visited.add(arr.length - 1);

        int steps = 0;
        while (!set1.isEmpty() && !set2.isEmpty()) {
            if (set1.size() > set2.size()) {
                Set<Integer> tmp = set1;
                set1 = set2;
                set2 = tmp;
            }

            Set<Integer> newSet = new HashSet<>();
            for (int idx : set1) {
                for (int i : equalValues.get(arr[idx])) {
                    if (set2.contains(i)) {
                        return steps + 1;
                    }
                    if (!visited.contains(i)) {
                        visited.add(i);
                        newSet.add(i);
                    }
                }

                equalValues.get(arr[idx]).clear();
                if (set2.contains(idx + 1) || set2.contains(idx - 1)) {
                    return steps + 1;
                }
                if (idx + 1 < arr.length && !visited.contains(idx + 1)) {
                    visited.add(idx + 1);
                    newSet.add(idx + 1);
                }
                if (idx - 1 >= 0 && !visited.contains(idx - 1)) {
                    visited.add(idx - 1);
                    newSet.add(idx - 1);
                }
            }

            set1 = newSet;
            steps++;
        }
        return steps;
    }

    public int maxJumps(int[] arr, int d) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] maxSteps = new int[arr.length];
        Arrays.fill(maxSteps, 1);

        for (int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int idx = stack.pop();
                int m = 0;
                for (int j = idx + 1; j <= idx + d && j < i; j++) {
                    if (arr[j] == arr[idx]) break;
                    m = Math.max(m, maxSteps[j]);
                }
                maxSteps[idx] = Math.max(maxSteps[idx], m + 1);
                if (idx + d >= i) {
                    maxSteps[i] = Math.max(maxSteps[i], maxSteps[idx] + 1);
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int m = 0;
            for (int j = idx + 1; j <= idx + d && j < arr.length; j++) {
                if (arr[j] == arr[idx]) break;
                m = Math.max(m, maxSteps[j]);
            }
            maxSteps[idx] = Math.max(maxSteps[idx], m + 1);
        }

        int ret = 1;
        for (int i = 0; i < arr.length; i++) {
            ret = Math.max(ret, maxSteps[i]);
        }
        return ret;
    }

    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        Deque<int[]> dq = new LinkedList<>();
        int val = nums[n - 1];
        dq.offerLast(new int[]{val, n - 1});

        for (int i = n - 2; i >= 0; i--) {
            while (dq.peekFirst() != null && dq.peekFirst()[1] > i + k) {
                dq.pollFirst();
            }
            val = dq.peekFirst()[0] + nums[i];
            while (dq.peekLast() != null && dq.peekLast()[0] <= val){
                dq.pollLast();
            }
            dq.offerLast(new int[]{val, i});
        }
        return val;
    }
}
