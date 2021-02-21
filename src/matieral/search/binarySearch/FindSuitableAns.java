package matieral.search.binarySearch;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/
 * Test: https://leetcode.com/problems/split-array-largest-sum/
 */

public class FindSuitableAns {
    public int minimumSize(int[] nums, int maxOperations) {
        int finalLen = nums.length + maxOperations, n = nums.length;
        Arrays.sort(nums);
        int r = nums[n - 1];

        int sum = Arrays.stream(nums).sum();
        int l  = sum % finalLen == 0 ? sum / finalLen : sum / finalLen + 1;

        while (l <= r) {  // find minimal
            int mid = l + (r - l) / 2;
            if (possible(mid, maxOperations, nums)) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean possible(int val, int maxOperations, int[] nums) {
        int operations = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            if (num <= val) return true;

            operations += num % val == 0 ? num / val - 1 : num / val; // 易错
            if (operations > maxOperations) return false;
        }
        return true;
    }
}
