package matieral.patterns.backtracking;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/
 */

public class KGroups {
    int res = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int[] workers = new int[k];
        backtrack(jobs, jobs.length - 1, workers);
        return res;
    }

    private void backtrack(int[] jobs, int idx, int[] workers) {
        int currMax = Arrays.stream(workers).max().getAsInt();
        if (idx == -1) {
            res = Math.min(res, currMax);
            return;
        }
        if (currMax > res) return;

        for (int i = 0; i < workers.length; i++) {
            if (i > 0 && workers[i] == workers[i - 1]) {
                continue;
            }

            workers[i] += jobs[idx];
            backtrack(jobs, idx - 1, workers);
            workers[i] -= jobs[idx];
        }

    }
}
