package matieral.patterns;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/minimum-window-subsequence/
 * Test: https://leetcode.com/problems/minimum-window-substring/
 * Test: https://leetcode.com/problems/increasing-triplet-subsequence/
 */

public class TwoPointers {

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
                    while (tIdx >= 0) { // Interesting
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
}
