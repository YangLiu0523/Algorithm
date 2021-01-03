package matieral.sort;

/**
 * Very special case for me in Binary Serach
 * Test link: https://leetcode.com/problems/peak-index-in-a-mountain-array/
 * 常见错误： https://leetcode.com/submissions/detail/436501211/
 * 千万不要做 l = 0, r = 1, mid = 0,  l = mid 这样的事。 mid 本来就偏小， l = mid 很可能就不变了
 * r = mid 可以!!!!!， l 不要 = mid
 * https://leetcode.com/problems/median-of-two-sorted-arrays/, 这个的边界也很有趣
 * 我觉得只有不确定当前的具体情况的时候， 是不可以 mid + 1 / mid - 1 的, 同时也要使用 < 而不是 <=
 */
public class BitonicArrayMaximum {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {  //这里真的很有趣， 因为根据mid的取法（偏向小的），mid + 1不会越界， 但是mid 很可能是0， 这样mid - 1 就会越界
                left = mid + 1;
            }
            else if (arr[mid] > arr[mid + 1]) {
                right = mid;
            }
        }
        return left;
    }
}
