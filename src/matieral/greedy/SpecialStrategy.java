package matieral.greedy;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/find-the-most-competitive-subsequence/
 * Test: https://leetcode.com/problems/create-maximum-number/ => Very hard
 * Test: https://leetcode.com/problems/maximum-swap/
 *
 * Test: https://codeforces.com/contest/1480/problem/D1
 * Test: https://codeforces.com/contest/1480/problem/D2
 *
 * Test: https://leetcode.com/problems/1-bit-and-2-bit-characters/
 */

public class SpecialStrategy {
    //choose elements for our subsequence such that every element is the smallest possible value for that array position.
    public int[] mostCompetitive(int[] nums, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        int addition = nums.length - k;
        for (int i = 0 ; i < nums.length; i++) {
            while (!list.isEmpty() && list.getLast() > nums[i] && addition > 0) {
                addition--;
                list.removeLast();
            }
            list.add(nums[i]);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.removeFirst();
        }
        return res;
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] ret = new int[k];
        for (int i = 0; i <= k; i++) {
            int j = k - i;
            if (i > m || j > n) continue;
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, j), k);
            if (greaterThan(candidate, 0, ret, 0)) ret = candidate;
        }
        return ret;
    }

    private int[] merge(int[] nums1, int[] nums2, int len) {
        int[] ret = new int[len];
        for (int i = 0, j = 0, k = 0; i < len; i++) {
            ret[i] = greaterThan(nums1, j, nums2, k) ? nums1[j++] : nums2[k++];
        }
        return ret;
    }

    private boolean greaterThan(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || i < nums1.length && nums1[i] > nums2[j];
    }

    private int[] maxArray(int[] nums, int len) {
        int n = nums.length;
        int[] ret = new int[len];
        for (int i = 0, j = 0; i < n; i++) {
            while (n - i + j > len && j - 1 >= 0 && ret[j - 1] < nums[i]) j--;
            if (j < len) ret[j++] = nums[i];
        }
        return ret;
    }

    public int maximumSwap(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;
        }

        for (int i = 0; i < A.length; i++) {
            for (int d = 9; d > A[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }


    public int findMax(int n, int[] arr) { // maximum
        int ret = 0;
        int[] pos = new int[n + 1], next = new int[n]; // Good trick
        Arrays.fill(pos, n + 1);
        for (int i = n - 1; i >= 0; i--) {
            next[i] = pos[arr[i]];
            pos[arr[i]] = i;
        }

        int x = -1, y = -1;
        for (int i = 0; i < arr.length; i++) {
            if (x >= 0 && arr[i] == arr[x]) {
                ret = y >= 0 && arr[y] == arr[i] ? ret : ret + 1;
                y = i;
            }
            else if (y >= 0 && arr[i] == arr[y]) {
                ret = x >= 0 && arr[x] == arr[i] ? ret : ret + 1;
                x = i;
            }
            else {
                if (x == -1 || y == -1) { // error prone
                    ret++;
                    if (x == -1) y = i;
                    else x = i;
                }
                else if (next[x] < next[y]) {
                    ret++;
                    x = i;
                }
                else {
                    ret++;
                    y = i;
                }
            }
        }
        System.out.println(ret);
        return ret;
    }

    public int findMin(int n, int[] arr) {  // 1 indexed
        int[] next = new int[n + 1];
        int[] pos = new int[n + 1];
        Arrays.fill(pos, n + 1);
        for (int i = n; i >= 0; i--) {
            next[i] = pos[arr[i]];
            pos[arr[i]] = i;
        }

        int x = 0, y = 0, res = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[i] == arr[x]) {
                x = i;
            }
            else if (arr[i] == arr[y]) {
                y = i;
            }
            else if (next[x] > next[y]) {
                res += 1;
                x = i;
            }
            else {
                res += 1;
                y = i;
            }
        }
        return res;
    }

    public boolean isOneBitCharacter(int[] bits) {
        int i = bits.length - 2;
        while (i >= 0 && bits[i] > 0) i--;
        return (bits.length - i) % 2 == 0;
    }
}
