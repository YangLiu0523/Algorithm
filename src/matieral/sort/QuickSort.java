package matieral.sort;

public class QuickSort {
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length -1);
    }

    public void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int par = partition(nums, lo, hi);
        quickSort(nums, lo, par - 1);
        quickSort(nums, par + 1, hi);
    }

    public int partition(int[] nums, int lo, int hi) {
        int i = lo + 1, j = hi;
        int target = nums[lo];

        while (true) {
            while (i <= hi && nums[i] < target) i++;
            while (j >= lo && nums[j] > target) j--;

            if (i >= j) break;

            exchange(nums, i++, j--);
        }
        exchange(nums, lo, j);
        return j;
    }

    private void exchange(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        QuickSort sol = new QuickSort();
        int[] nums = {1, 2, 3, 4, 6};
        sol.quickSort(nums);
        sol.printArr(nums);
    }

    private void printArr(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
