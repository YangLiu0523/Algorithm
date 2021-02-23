package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * Test: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * Test: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * Test: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * Test: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * Test: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/986281/DP-or-O(N)-Time-O-(N)-Space-or-95-Faster-than-all-or-Well-Explained
 */

public class StockPrice {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int maxProfit = 0;
        int prev = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prev;
            maxProfit = Math.max(maxProfit, profit);
            prev = Math.min(prev, prices[i]);
        }

        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        int profit = 0;
        int prev = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prev) {
                prev = prices[i];
            }
            else {
                profit += (prices[i] - prev);
                prev = prices[i];
            }
        }
        return profit;
    }

    public int maxProfit3(int[] prices) {
        int t1Cost = Integer.MAX_VALUE,
                t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0,
                t2Profit = 0;

        for (int price : prices) {
            // the maximum profit if only one transaction is allowed
            t1Cost = Math.min(t1Cost, price);
            t1Profit = Math.max(t1Profit, price - t1Cost);
            // reinvest the gained profit in the second transaction
            t2Cost = Math.min(t2Cost, price - t1Profit);
            t2Profit = Math.max(t2Profit, price - t2Cost);
        }

        return t2Profit;
    }

    public int maxProfit4(int k, int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = prices[0];

        int[] profit = new int[n];
        for (int j = 0; j < k; j++) {
            for (int i = 1; i < n; i++) {
                cost[i] = Math.min(cost[i - 1], prices[i] - profit[i]);
                profit[i] = Math.max(profit[i - 1],  prices[i] - cost[i]);
            }
        }

        return profit[n - 1];
    }

    public int maxProfitCoolDown(int[] prices) {
        int sold = Integer.MIN_VALUE, hold = Integer.MIN_VALUE, reset = 0;
        for (int price : prices) {
            int preSold = sold;
            sold = hold + price;
            hold = Math.max(hold, reset - price);
            reset = Math.max(reset, preSold);
        }

        return Math.max(reset, sold);
    }

    public int maxProfit(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}
