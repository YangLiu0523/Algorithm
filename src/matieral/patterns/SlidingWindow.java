package matieral.patterns;

import java.util.*;


/**
 * Test: https://leetcode.com/problems/longest-turbulent-subarray/
 * Test: https://leetcode.com/problems/minimum-window-subsequence/
 * Test: https://leetcode.com/problems/minimum-window-substring/
 * Test: https://leetcode.com/problems/sliding-window-median/
 *
 */

public class SlidingWindow {

    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ans = 1, anchor = 0;
        for (int i = 1; i < n; i++) {
            int c = Integer.compare(arr[i - 1], arr[i]);
            if (c == 0) {
                ans = Math.max(ans, i - anchor);
                anchor = i;
            }
            else if (i == n - 1 || c * Integer.compare(arr[i], arr[i + 1]) != -1) {
                ans = Math.max(ans, i - anchor + 1);
                anchor = i;
            }
        }
        return ans;
    }

    public String minWindow(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sIdx = 0, tIdx = 0, sLen = s.length, tLen = t.length;
        int minLen = sLen, start = -1;

        while (sIdx < sLen) {
            if (s[sIdx] == t[tIdx]) {
                tIdx++;
                if (tIdx == tLen) {
                    tIdx--;
                    int end = sIdx + 1;
                    while (tIdx >= 0) {
                        while (s[sIdx] != t[tIdx]) sIdx--;
                        sIdx--;
                        tIdx--;
                    }

                    sIdx++;
                    tIdx++;

                    if (end - sIdx < minLen) {
                        minLen = end - sIdx;
                        start = sIdx;
                    }
                }
            }
            sIdx++;
        }
        return start == -1 ? "" : S.substring(start, start + minLen);
    }

    public String minWindow2(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        int findCharacters = 0;
        String ret = "";

        Map<Character, Integer> sMap = new HashMap<>();
        int slow = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            char c = s.charAt(fast);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
            if (tMap.containsKey(c) && tMap.get(c).equals(sMap.get(c))) {
                findCharacters++;
            }
            if (findCharacters == tMap.size()) {
                char slowChar = s.charAt(slow);
                while (!tMap.containsKey(slowChar) ||
                        sMap.get(slowChar) > tMap.get(slowChar)) {
                    sMap.put(slowChar, sMap.get(slowChar) - 1);
                    slowChar = s.charAt(++slow);
                }

                if (ret.isEmpty() || fast - slow + 1 < ret.length()) {
                    ret = s.substring(slow, fast + 1);
                }

                char tmpC = s.charAt(slow++);
                sMap.put(tmpC, sMap.get(tmpC) - 1);
                findCharacters--;
            }

        }
        return ret;
    }

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
