package matieral.string;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/repeated-substring-pattern/
 * Reference : https://www.youtube.com/watch?v=uKr9qIZMtzw
 * Test: https://leetcode.com/problems/implement-strstr/
 *
 *
 * KMP is typically used for the single pattern search.
 * The key to KMP is the partial match table, often called lookup table, or failure function table. It stores the length of the longest prefix that is also a suffix.
 */

public class KMP {
    public List<Integer> match(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] next = build(p);

        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = next[j];
            }
            if (s.charAt(i) == p.charAt(j)) {
                j++;
            }
            if (j == p.length()) {
                ans.add(i - p.length() + 1);
                j = next[j];
            }
        }
        return ans;
    }

    public int[] build(String p) {
        int[] next = new int[p.length() + 1];
        int nextIdx = 2;
        for (int i =1, j = 0; i < p.length(); i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = next[j];
            }
            if (p.charAt(i) == p.charAt(j)) j++;
            next[nextIdx++] = j;
        }
        return next;
    }

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] nxt = new int[n + 1];
        int nxtIdx = 2;
        for (int i = 1, j = 0; i < n; i++) {
            while(j > 0 && s.charAt(i) != s.charAt(j)) {
                j = nxt[j];
            }
            if (s.charAt(i) == s.charAt(j)) j++;
            nxt[nxtIdx++] = j;
        }

        int l = nxt[n];
        return l != 0 && n % (n - l) == 0;
    }

    public static void main(String[] args) {
        KMP test = new KMP();
        String p = "AAAA";
        int[] nxt = test.build(p);
    }

}
