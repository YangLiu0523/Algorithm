package matieral.sort;

/**
 * Test: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */

public class BinarySearch {
    class Solution {
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
    }
}
