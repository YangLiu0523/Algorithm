package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/k-th-smallest-prime-fraction/
 * Test: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * Test: https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 */
public class KthSmallest {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(arr[a[0]] * arr[b[1]], arr[a[1]] * arr[b[0]]));

        // 1/2, 1/3, 1/5
        for (int i = 1; i < arr.length; i++) {
            pq.offer(new int[]{0, i});
        }

        for (int i = 0; i < k - 1; i++) {
            int[] frac = pq.poll();
            frac[0]++;
            if (frac[0] < frac[1]) {
                pq.offer(frac);
            }
        }

        int[] ans = pq.poll();
        return new int[]{arr[ans[0]], arr[ans[1]]};
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(matrix[a[0]][a[1]], matrix[b[0]][b[1]]));
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{i, 0});
        }

        for (int i = 0; i < k - 1; i++) {
            int[] info = pq.poll();
            int gid = info[0], id = info[1];
            if (id + 1 <= n - 1) {
                pq.offer(new int[]{gid, id + 1});
            }
        }

        int[] arr = pq.poll();
        return matrix[arr[0]][arr[1]];
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
