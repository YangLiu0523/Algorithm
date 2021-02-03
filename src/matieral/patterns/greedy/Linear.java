package matieral.patterns.greedy;

/**
 * Test: https://leetcode.com/problems/gas-station/
 * Test: https://leetcode.com/problems/super-washing-machines/
 */

public class Linear {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] diff = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = gas[i] - cost[i];
            sum += diff[i];
        }

        if (sum < 0) return -1;

        int ret = n - 1, max = diff[n - 1], tmp = 0;
        for (int j = n - 1; j >= 0; j--) {
            tmp += diff[j];
            if (tmp >= 0 && tmp > max) {
                ret = j;
                max = tmp;
            }
        }
        return ret;
    }
}
