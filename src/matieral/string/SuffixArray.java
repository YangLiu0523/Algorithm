package matieral.string;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Reference: https://www.geeksforgeeks.org/suffix-array-set-2-a-nlognlogn-algorithm/
 *  O(nLognLogn)
 */

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
public class SuffixArray {
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

    public static void main(String[] args) {
        SuffixArray test = new SuffixArray();
        int[] ans = test.suffixArray("banana"); //5 3 1 0 4 2
        System.out.println("hello");
    }
}
