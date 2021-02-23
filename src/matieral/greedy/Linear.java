package matieral.greedy;

import java.util.*;

/**
 * Test: https://leetcode.com/problems/gas-station/ => 需要加深理解
 * Test: https://leetcode.com/problems/super-washing-machines/
 * Test: https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/
 */

public class Linear {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, currGas = 0, ret = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i] - cost[i];
            currGas += gas[i] - cost[i];
            if (currGas < 0) {
                currGas = 0;
                ret = i + 1;
            }
        }
        return totalGas >= 0 ? ret : -1 ;
    }

    public int findMinMoves(int[] machines) {
        int sum = Arrays.stream(machines).sum();
        int n = machines.length;
        if (sum % n != 0) return -1;

        int avg = sum / n;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];
        for (int i = 1; i < n; i++) {
            leftSum[i] = leftSum[i - 1] + machines[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + machines[i + 1];
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int leftExp = i * avg, rightExp = avg * (n - 1 - i);
            int left = Math.max(0, leftExp - leftSum[i]);
            int right = Math.max(0, rightExp - rightSum[i]);
            max =  Math.max(max, left + right);
        }
        return max;
    }

    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> b[1] - b[0] - (a[1] - a[0]));
        int curr = 0, add = 0;
        for (int[] task : tasks) {
            if (curr < task[1]) {
                add += task[1] - curr;
                curr = task[1];
            }
            curr -= task[0];
        }

        return add;
    }
}
