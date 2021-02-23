package matieral.patterns;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/meeting-scheduler/
 * Test: https://leetcode.com/problems/minimum-window-subsequence/
 * Test: https://leetcode.com/problems/minimum-window-substring/
 * Test: https://leetcode.com/problems/increasing-triplet-subsequence/
 *
 * Test: https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/
 * Test: https://leetcode.com/problems/boats-to-save-people/
 *
 * Test: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/ => Like
 */

public class TwoPointers {

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(slots2, (a, b) -> Integer.compare(a[0], b[0]));

        int idx1 = 0, idx2 = 0;
        while (idx1 < slots1.length && idx2 < slots2.length) {
            int l = Math.max(slots1[idx1][0], slots2[idx2][0]);
            int r = Math.min(slots1[idx1][1], slots2[idx2][1]);
            if (r - l >= duration) {
                return new ArrayList<>(Arrays.asList(l, l + duration));
            }

            // always move the one that ends earlier => Important
            if (slots1[idx1][1] < slots2[idx2][1]) {
                idx1++;
            }
            else {
                idx2++;
            }
        }
        return new ArrayList<Integer>();
    }

    public String minWindow(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sIdx = 0, tIdx = 0, sLen = s.length, tLen = t.length;
        int minLen = sLen, start = -1;

        while (sIdx < sLen) {
            if (s[sIdx] == t[tIdx]) {
                tIdx++;
                if (tIdx == tLen) {
                    int end = sIdx + 1;
                    tIdx--;
                    while (tIdx >= 0) { // Interesting, Love this part
                        while (s[sIdx] != t[tIdx]){
                            sIdx--;
                        }
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

    public boolean increasingTriplet(int[] nums) {
        int first_num = Integer.MAX_VALUE;
        int second_num = Integer.MAX_VALUE;
        for (int n: nums) {
            if (n <= first_num) {
                first_num = n;
            } else if (n <= second_num) {
                second_num = n;
            } else {
                return true;
            }
        }
        return false;
    }

    public int boxDelivering(int[][] A, int portsCount, int B, int W) {
        int n = A.length, trips = 0, r = 0, prevR = 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 200000);
        dp[0] = 0;

        for (int l = 0; l < n; l++) {
            while (r < n && B > 0 && W >= A[r][1]) {
                B--;
                W -= A[r][1];
                if (r == 0 || A[r][0] != A[r - 1][0]) {
                    trips++;
                    prevR = r;
                }
                r++;
            }

            dp[r] = Math.min(dp[r], dp[l] + trips + 1);
            dp[prevR] = Math.min(dp[prevR], dp[l] + trips);

            B += 1;
            W += A[l][1];
            if (l == n - 1 || A[l][0] != A[l + 1][0]) {
                trips--;
            }
        }
        return dp[n];
    }

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int l = 0, r = people.length - 1;
        int boats = 0;
        while (l <= r) {
            boats++;
            int curr = people[r--];
            if (l <= r && curr + people[l] <= limit) {
                curr += people[l++];
            }
        }
        return boats;
    }

    public int minOperations(int[] nums, int x) {
        int current = 0;
        for (int num : nums) {
            current += num;
        }
        int n = nums.length;
        int mini = Integer.MAX_VALUE;
        int left = 0;
        for (int right = 0; right < n; right++) {
            current -= nums[right];
            while (current < x && left <= right) {
                current += nums[left];
                left++;
            }
            if (current == x) {
                mini = Math.min(mini, (n - 1 - right) + left);
            }
        }
        return mini != Integer.MAX_VALUE ? mini : -1;
    }
}
