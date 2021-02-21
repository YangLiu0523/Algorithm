package matieral.patterns.merging_intervals;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/remove-covered-intervals/
 * Test: https://leetcode.com/problems/remove-interval/
 * Test: https://leetcode.com/problems/data-stream-as-disjoint-intervals/
 * Test: https://leetcode.com/problems/missing-ranges/
 */

public class Interval {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int cnt = 1;
        int prevEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][1] > prevEnd) {
                cnt++;
                prevEnd = intervals[i][1];
            }
        }
        return cnt;
    }

    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            if (end <= toBeRemoved[0] || start >= toBeRemoved[1]) {
                res.add(new ArrayList<>(Arrays.asList(start, end)));
            }
            else {
                if (start < toBeRemoved[0] && end <= toBeRemoved[1]) {
                    res.add(new ArrayList<>(Arrays.asList(start, toBeRemoved[0])));
                }
                else if (end > toBeRemoved[1] && start >= toBeRemoved[0]) {
                    res.add(new ArrayList<>(Arrays.asList(toBeRemoved[1], end)));
                }
                else if (start >= toBeRemoved[0] && end <= toBeRemoved[1]) {
                }
                else {
                    res.add(new ArrayList<>(Arrays.asList(start, toBeRemoved[0])));
                    res.add(new ArrayList<>(Arrays.asList(toBeRemoved[1], end)));
                }

            }
        }
        return res;
    }
}
class SummaryRanges {

    TreeSet<Integer> set;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        set = new TreeSet<>();
    }

    public void addNum(int val) {
        set.add(val);

    }

    public int[][] getIntervals() {
        List<int[]> list = new ArrayList<>();
        int prev = -1, curr = 0;
        for (int num : set) {
            if (prev == -1) {
                prev = num;
            }
            else if (num != curr + 1) {
                list.add(new int[]{prev, curr});
                prev = num;
            }
            curr = num;
        }

        list.add(new int[]{prev, curr});

        int[][] ret = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int curr = (i < nums.length) ? nums[i] : upper + 1;
            if (prev + 1 <= curr - 1) {
                result.add(formatRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return result;
    }

    // formats range in the requested format
    private String formatRange(int lower, int upper) {
        if (lower == upper) {
            return String.valueOf(lower);
        }
        return lower + "->" + upper;
    }
}