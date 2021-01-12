package matieral.string.concret_problem;

import java.util.Arrays;

class Suffix implements Comparable<Suffix> {
    int index;
    int rank;
    int nextRank;

    public Suffix(int index, int rank, int nextRank){
        this.index = index;
        this.rank = rank;
        this.nextRank = nextRank;
    }

    @Override
    public int compareTo(Suffix o) {
        return this.rank == o.rank ? this.nextRank - o.nextRank : this.rank - o.rank;
    }
}

public class LongestDuplicateSubstring {
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

    public int[] suffixArray(String s) {
        int n = s.length();
        Suffix[] suArr = new Suffix[n];
        for (int i = 0; i < n; i++) {
            suArr[i] = new Suffix(i, s.charAt(i) - '$', 0);
        }
        for (int i = 0; i < n ; i++) {
            suArr[i].nextRank = i + 1 < n ? suArr[i + 1].rank : -1;
        }
        Arrays.sort(suArr);

        int[] posInSu = new int[n];
        for (int len = 4; len < n * 2; len <<= 1) {
            int newRank = 0, prev = suArr[0].rank;
            suArr[0].rank = 0;
            posInSu[suArr[0].index] = 0;
            for (int i = 1; i < n; i++) {
                if (suArr[i].rank == prev && suArr[i].nextRank == suArr[i - 1].nextRank) {
                    prev = suArr[i].rank;
                    suArr[i].rank = newRank;
                }
                else {
                    prev = suArr[i].rank;
                    suArr[i].rank = ++newRank;
                }
                posInSu[suArr[i].index] = i;
            }
            for (int i = 0; i < n; i++) {
                int nxt = suArr[i].index + len / 2;
//                int suArrIdx = posInSu[nxt];
                suArr[i].nextRank = nxt < n ? suArr[posInSu[nxt]].rank : -1;
            }
            Arrays.sort(suArr);
        }

        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = suArr[i].index;
        }
        return ret;
    }

    public String longestDupSubstring(String s) {
        int[] sa = suffixArray(s);
        int[] lcs = kasai(s, sa);
        String lrs = "";
        for (int i = 0; i < s.length(); i++) {
            if (lcs[i] > lrs.length()) {
                lrs = s.substring(sa[i], sa[i] + lcs[i]);
            }
        }
        return lrs;
    }
}