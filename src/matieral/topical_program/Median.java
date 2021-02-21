package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/sliding-window-median/
 */
public class Median {
    PriorityQueue<Integer> lo = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    PriorityQueue<Integer> hi = new PriorityQueue<>();
    Map<Integer, Integer> invalid = new HashMap<>();

    int loCnt = 0, hiCnt = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] ans = new double[n - k + 1];
        for (int i = 0; i < k; i++) {
            addNum(nums[i]);
        }
        for (int i = k; i < n; i++) {
            ans[i - k] = median();
            removePrev(nums[i - k]);
            addNum(nums[i]);
        }
        ans[n - k] = median();

        return ans;
    }

    private double median(){
        while (!lo.isEmpty() && invalid.containsKey(lo.peek())) {
            int num = lo.poll();
            invalid.put(num, invalid.get(num) - 1);
            if (invalid.get(num) == 0) {
                invalid.remove((Integer)num);
            }
        }
        while (!hi.isEmpty() && invalid.containsKey(hi.peek())) {
            int num = hi.poll();
            invalid.put(num, invalid.get(num) - 1);
            if (invalid.get(num) == 0) {
                invalid.remove((Integer)num);
            }
        }

        balance();


        return loCnt == hiCnt ? ((long)lo.peek() + hi.peek()) / 2.0 : lo.peek();
    }

    private void removePrev(int num) {
        invalid.put(num, invalid.getOrDefault(num, 0) + 1);
        if (num <= lo.peek()) {
            loCnt--;
        }
        else {
            hiCnt--;
        }
    }

    private void addNum(int num) {
        if (lo.isEmpty() && hi.isEmpty()) {
            lo.offer(num);
            loCnt++;
        }
        else if (!lo.isEmpty() && num <= lo.peek()) {
            lo.offer(num);
            loCnt++;
        }
        else {
            hi.offer(num);
            hiCnt++;
        }

        balance();
    }

    private void balance(){
        while (loCnt > hiCnt + 1) {
            loCnt--;
            hiCnt++;
            hi.offer(lo.poll());
        }
        while (hiCnt > loCnt) {
            loCnt++;
            hiCnt--;
            lo.offer(hi.poll());
        }
    }

}
