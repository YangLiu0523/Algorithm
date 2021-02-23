package matieral.topical_program;

/**
 * Test: https://leetcode.com/problems/ugly-number/
 * Test: https://leetcode.com/problems/ugly-number-ii/ => Like
 * Test: https://leetcode.com/problems/ugly-number-iii/
 */

public class UglyNumber {
    public boolean isUgly(int num) {
        if (num == 0) return false;
        int[] candidates = {2, 3, 5};
        for (int can : candidates) {
            while (num % can == 0) num /= can;
        }
        return num == 1;
    }

    public int nthUglyNumber(int n, int a, int b, int c) {
        long left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long val = count(mid, a, b, c);
            // System.out.println(left + "-" + right + ": " + mid + " => " + val);
            if (val < n) {
                left = mid + 1;
            }
            else if (val >= n) {
                right = mid ;
            }
        }
        return (int)(right);
    }

    public long count(long n, int a, int b, int c) {
        return n / a + n / b + n / c - n / lcm(a, b) - n / lcm(a, c) - n / lcm(b, c) + n / lcm(a, lcm(c, b));
    }

    public long gcd(long a, long b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}

class Ugly {
    public int[] nums = new int[1690];
    Ugly() {
        nums[0] = 1;
        int ugly, i2 = 0, i3 = 0, i5 = 0;

        for(int i = 1; i < 1690; ++i) {
            ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = ugly;

            if (ugly == nums[i2] * 2) ++i2;
            if (ugly == nums[i3] * 3) ++i3;
            if (ugly == nums[i5] * 5) ++i5;
        }
    }
}