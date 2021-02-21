package matieral.dp;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/word-break/
 * Test: https://leetcode.com/problems/word-break-ii/
 */
public class WorkBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        List<String> res = backtrack(s, 0, dict, memo);
        return res;
    }

    private List<String> backtrack(String s, int idx, Set<String> dict, Map<Integer, List<String>> memo) {
        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }

        List<String> ret = new ArrayList<>();

        for (int i = idx + 1; i <= s.length(); i++) {
            String sub = s.substring(idx, i);
            if (dict.contains(sub)) {
                if (i < s.length()) {
                    List<String> back = backtrack(s, i, dict, memo);
                    for (String l : back) {
                        String newStr = sub + " " + l;
                        ret.add(newStr);
                    }
                }
                else {
                    ret.add(sub);
                }

            }
        }
        memo.put(idx, ret);
        return memo.get(idx);
    }
}
