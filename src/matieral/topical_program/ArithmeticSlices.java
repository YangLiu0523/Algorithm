package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/arithmetic-slices/
 * Test: https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
 * Test: https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/
 */

public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] A) {
        int tmp = 0, slices = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                tmp++;
                slices += tmp;
            }
            else {
                tmp = 0;
            }
        }
        return slices;
    }

    public int numberOfArithmeticSlicesSubSeq(int[] A) {
        int n = A.length;
        Map<Long, Integer>[] diffCnt = new HashMap[n];
        for (int i = 0; i < n; i++) {
            diffCnt[i] = new HashMap<>();
        }

        int slices = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                long diff = (long)A[i] - A[j];
                if (diffCnt[j].containsKey(diff)) {
                    slices += diffCnt[j].get(diff);
                }

                int count = diffCnt[i].getOrDefault(diff, 0) + diffCnt[j].getOrDefault(diff, 0) + 1;
                diffCnt[i].put(diff, count);
            }
        }
        return slices;
    }

    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> valToCnt = new HashMap();
        valToCnt.put(arr[0], 1);

        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            int currVal = valToCnt.getOrDefault(arr[i] - difference, 0) + 1;
            int newVal = Math.max(currVal, valToCnt.getOrDefault(arr[i], 0));
            max = Math.max(max, newVal);
            valToCnt.put(arr[i], newVal);
        }
        return max;
    }
}
