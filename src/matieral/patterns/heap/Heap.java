package matieral.patterns.heap;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 * Test: https://leetcode.com/problems/minimize-deviation-in-array/
 * Test: https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 */

public class Heap {
    public int[] smallestRange(List<List<Integer>> nums) {
        int start = 0, end = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] next = new int[nums.size()];

        PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>((i, j) -> nums.get(i).get(next[i]) - nums.get(j).get(next[j]));
        for (int i = 0; i < nums.size(); i++) {
            minQueue.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }

        boolean flag = true;
        for (int i = 0; i < nums.size() && flag; i++) {
            for (int j = 0; j < nums.get(i).size() && flag; j++) {
                int minIdx = minQueue.poll();
                if (end - start > max - nums.get(minIdx).get(next[minIdx])) {
                    start = nums.get(minIdx).get(next[minIdx]);
                    end = max;
                }
                next[minIdx]++;
                if (next[minIdx] == nums.get(minIdx).size()) {
                    flag = false;
                    break;
                }
                minQueue.offer(minIdx);
                max = Math.max(max, nums.get(minIdx).get(next[minIdx]));
            }
        }

        return new int[]{start, end};
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


}
