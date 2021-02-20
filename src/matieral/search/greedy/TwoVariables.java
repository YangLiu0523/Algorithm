package matieral.search.greedy;
import java.util.*;

/**
 * In these four problems, you have two variables to find a solution. Control one and move one to get the solution
 * First two is Greedy, last two is not greedy but intuitive
 *
 * Test: https://arena.topcoder.com/#/u/practiceCode/1419/2751/2977/1/1419
 * Test: https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
 * Test: https://leetcode.com/problems/minimize-deviation-in-array/
 * Test: https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 *
 */

public class TwoVariables {
    public int maxCredit(int[] a, int[] b, int[] c, int[] d, int[] e) {
        int[][] hits = {a, b, c, d, e};

        int[] board = new int[5];
        Arrays.fill(board, -1);
        int[] pnt = new int[5];

        int ret = 0;
        for (int i = 0; i <= 180000; i++) {
            for (int j = 0; j < 5; j++) {
                if (pnt[j] == hits[j].length) continue;
                if (i >= hits[j][pnt[j]]) {
                    board[j] = hits[j][pnt[j]];
                    pnt[j]++;
                }
            }

            int selectedNum = 0;
            for (int j = 0; j < 5; j++) {
                if (board[j] != -1) selectedNum++;
            }

            if (selectedNum >= 3) {
                ret++;
                Arrays.fill(board, -1);
            }
        }
        return ret;
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        Worker[] workers = new Worker[n];
        for (int i = 0; i < n; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(workers, (w1, w2) -> Double.compare(w1.ratio, w2.ratio));

        PriorityQueue<Integer> maxQuatity = new PriorityQueue<>((a, b) -> b - a);
        int currQuality = 0;
        double minCost = 1e9;

        for (int i = 0; i < K; i++) {
            currQuality += workers[i].quality;
            maxQuatity.offer(workers[i].quality);
            minCost = workers[i].ratio * currQuality;
        }

        for (int i = K; i < n; i++) {
            maxQuatity.offer(workers[i].quality);
            int removedQuatity = maxQuatity.poll();
            currQuality += workers[i].quality - removedQuatity;
            minCost = Math.min(minCost, workers[i].ratio * currQuality);
        }
        return minCost;
    }

    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            if (num % 2 == 1) set.add(num * 2);
            else set.add(num);
        }

        int gap = Integer.MAX_VALUE;
        while (set.last() % 2 == 0) {
            gap = Math.min(gap, set.last() - set.first());
            Integer last = set.last();
            set.remove(last);
            set.add(last / 2);
        }

        gap = Math.min(gap, set.last() - set.first());
        return gap;
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        int start = 0, end = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] pnt = new int[nums.size()];

        PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>((i, j) -> nums.get(i).get(pnt[i]) - nums.get(j).get(pnt[j]));
        for (int i = 0; i < nums.size(); i++) {
            minQueue.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }

        boolean flag = true;
        for (int i = 0; i < nums.size() && flag; i++) {
            for (int j = 0; j < nums.get(i).size() && flag; j++) {
                int minIdx = minQueue.poll();
                if (end - start > max - nums.get(minIdx).get(pnt[minIdx])) {
                    start = nums.get(minIdx).get(pnt[minIdx]);
                    end = max;
                }
                pnt[minIdx]++;
                if (pnt[minIdx] == nums.get(minIdx).size()) {
                    flag = false;
                    break;
                }
                minQueue.offer(minIdx);
                max = Math.max(max, nums.get(minIdx).get(pnt[minIdx]));
            }
        }

        return new int[]{start, end};
    }

}
class Worker {
    int quality;
    int wage;
    double ratio;
    public Worker(int quality, int wage) {
        this.quality = quality;
        this.wage = wage;
        this.ratio = (double) wage / quality;
    }
}