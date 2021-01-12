package matieral.string;

/**
 * Test: https://leetcode.com/problems/longest-duplicate-substring/
 * Reference: https://www.geeksforgeeks.org/%C2%AD%C2%ADkasais-algorithm-for-construction-of-lcp-array-from-suffix-array/
 *
 * LCP array construction is done two ways:
 * 1) Compute the LCP array as a byproduct to the suffix array (Manber & Myers Algorithm)
 * 2) Use an already constructed suffix array in order to compute the LCP values. (Kasai Algorithm).
 *
 * For example : s = "banana"
 * sa is [5, 3, 1, 0, 4, 2] => sa[0] = 5 means, string's index 5 is in the first place in suffix array
 * invSa[sa[i]] = i;
 * invSa is [3, 2, 5, 1, 4, 0] => invSa[5] = 0 meas, String's index is 5 and the position in sa array is 0
 * So if we start at 0, we want to find the next string to compare, what we should do is invSa[5] + 1, find the next sa string which is exactly sa[invSa[0] + 1] =sa[4]
 * banana's next is na as we need
 */

public class LCPKasai {
    public int[] kasai (String text, int[] sa) {
        int n = sa.length;
        int[] lcp = new int[n];

        int[] invSa = new int[n]; // index 在 sa中的位置
        for (int i = 0; i < n; i++) {
            invSa[sa[i]] = i;
        }

        int k = 0;
        for (int i = 0; i < n; i++) {
            if (invSa[i] == n - 1) {
                k = 0;
                continue;
            }

            int j = sa[invSa[i] + 1];
            while (i + k < n && j + k < n && text.charAt(i + k) == text.charAt(j + k)) k++;
            lcp[invSa[i]] = k;

            if (k > 0) k--;
        }
        return lcp;
    }

    public static void main(String[] args) {
        LCPKasai test = new LCPKasai();
        String s = "banana";
        int[] sa = {5, 3, 1, 0, 4, 2};
        int[] lcp = test.kasai(s, sa); //1, 3, 0, 0, 2, 0
        System.out.println("hello");
    }
}
