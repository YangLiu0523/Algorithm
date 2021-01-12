package matieral.string;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/repeated-substring-pattern/
 * Test: https://leetcode.com/problems/longest-repeating-substring/
 *
 * the best fit for the multiple pattern search
 *
 * Rabin-Karp is a linear-time O(N) string searching algorithm:
 * Move a sliding window of length LL along the string of length NN.
 * Check hash of the string in the sliding window.
 *
 * the simplest one, polynomial rolling hash. Beware that's polynomial rolling hash is NOT the Rabin fingerprint.
 * in a real life, when not all testcases are known in advance, one has to check if the strings with equal hashes are truly equal.
 */

public class RabinKarp {
    public int search(int l, String S, long MOD) {
        int n = S.length();
        long sum = 0, base = 1;
        for (int i = 0; i < l; i++) {
            sum = (sum * 26 + (S.charAt(i) - 'a')) % MOD;
            base = (base * 26) % MOD;
        }

        Set<Long> seen = new HashSet<>();
        seen.add(sum);

        for (int i = 1; i + l - 1 < n; i++) {
            sum = (sum * 26 - (S.charAt(i - 1) - 'a') * base + MOD) % MOD;
            sum = (sum + (S.charAt(i + l - 1) - 'a')) % MOD;
            if (seen.contains(sum)) {
                return i;
            }
            else seen.add(sum);
        }
        return -1;
    }

    public int longestRepeatingSubstring(String S) {
        int n = S.length();
        long MOD = (long)Math.pow(2, 32);
        int l = 1, r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (search(mid, S, MOD) != -1) l = mid + 1;
            else r = mid - 1;
        }
        return r;
    }

    // Not typical
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int l = 1; l <= n / 2; l++) {
            if (n % l == 0) {
                int firstHash = s.substring(0, l).hashCode();
                int currHash = firstHash;
                int start = l;
                while (start != n && currHash == firstHash) {
                    currHash = s.substring(start, start + l).hashCode();
                    start += l;
                }
                if (start == n && currHash == firstHash) {
                    return true;
                }
            }
        }
        return false;
    }
}
