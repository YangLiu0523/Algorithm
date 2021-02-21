package matieral.search.binarySearch;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/peak-index-in-a-mountain-array/ => Like the boundary
 * Test: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Test: https://leetcode.com/problems/k-th-smallest-prime-fraction/
 * Test: https://leetcode.com/problems/median-of-two-sorted-arrays/
 * Test: https://leetcode.com/problems/create-maximum-number/
 * Test: https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
 *
 *
 */

public class BinarySearch {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < arr[mid + 1]) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    public int[] kthSmallestPrimeFraction(int[] primes, int k) {
        double lo = 0, hi = 1;
        int[] ans = new int[]{0, 1};

        while (hi - lo > 1e-9) {
            double mid = lo + (hi - lo) /2.0;
            int[] res = under(mid, primes);
            if (res[0] < k) {
                lo = mid;
            }
            else {
                ans[0] = res[1];
                ans[1] = res[2];
                hi = mid;
            }
        }
        return ans;
    }

    public int[] under(double target, int[] primes) {
        int numer = 0, denom = 1, cnt = 0;
        int i = -1;
        for (int j = 1; j < primes.length; j++) {
            while (primes[i + 1] < primes[j] * target) i++;

            cnt += (i + 1);
            if (i >= 0 && numer * primes[j] < primes[i] * denom) {
                numer = primes[i];
                denom = primes[j];
            }
        }
        return new int[]{cnt, numer, denom};
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return res;
        }

        // floor
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left ) / 2;
            if (nums[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }
        if (nums[right + 1] != target) return res;
        res[0] = right + 1;


        left = 0; right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) /2;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }
        res[1] = left - 1;

        return res;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int halfLen = (n1 + n2 + 1) / 2;

        int left = 0, right = n1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int m1 = mid, m2 = halfLen - mid;
            if (m2 > n2 || (m2 - 1 >= 0 && m1 < n1 && nums1[m1] < nums2[m2 - 1])) left = mid + 1;
            else if (m2 < n2 && m1 - 1 >= 0 && nums2[m2] < nums1[m1 - 1]) right = mid - 1;
            else {
                int maxLeft = 0;
                if (m1 == 0) maxLeft = nums2[m2 - 1];
                else if (m2 == 0) maxLeft = nums1[m1 - 1];
                else maxLeft = Math.max(nums1[m1 - 1], nums2[m2 - 1]);
                if ((n1 + n2) % 2 == 1) return maxLeft;

                int minRight = 0;
                if (m1 == n1) minRight = nums2[m2];
                else if (m2 == n2) minRight = nums1[m1];
                else minRight = Math.min(nums1[m1], nums2[m2]);

                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0.0;
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
    public int[] kWeakestRows(int[][] mat, int k) {
        int n = mat.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? b[0] - a[0]: b[1] - a[1]);
        for (int i = 0; i < n; i++) {
            int val = floor(mat[i], 1);
            pq.offer(new int[]{i, val});
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }

    private int floor(int[] arr, int key) {
        int l = 0, r = arr.length - 1;
        while (l <= r ) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == 0) r = mid - 1;
            else l = mid + 1;
        }
        return r;
    }

}
