package matieral.math;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/broken-calculator/
 * Test: https://leetcode.com/problems/2-keys-keyboard/
 * Test: https://leetcode.com/problems/4-keys-keyboard/
 * Test: https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/
 */

public class Special {
    public int brokenCalc(int X, int Y) {
        int ans = 0;
        while (Y > X) {
            ans++;
            if(Y % 2 == 1) {
                Y++;
            } else {
                Y /= 2;
            }
        }
        return ans + X - Y;
    }

    public int minSteps(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }

    public int maxA(int N) {
        int[] best = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            best[i] = best[i - 1] + 1;
            for (int x = 0; x <= i - 3; x++) {
                best[i] = Math.max(best[i], best[x] + best[x] * (i - x - 2));
            }
        }
        return best[N];
    }

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
