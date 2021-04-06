package matieral.math;

/**
 * Test: https://leetcode.com/problems/calculate-money-in-leetcode-bank/
 */
public class Iterate {
    public int totalMoney(int n) {
        int start = 1, week = 0, day = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (week + start + day);
            day++;
            if (day == 7) {
                day = 0;
                week++;
            }
        }
        return sum;
    }
}
