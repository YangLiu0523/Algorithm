package matieral;

/**
 * Test: https://leetcode.com/problems/pour-water/
 */

public class Simulation {
    public int[] pourWater(int[] heights, int V, int K) {
        int n = heights.length;

        for (int i = 0; i < V; i++) {
            int choosed = K, j = K;
            while (j - 1 >= 0 && heights[j - 1] <= heights[j]) {
                if (heights[j - 1] < heights[j]) {
                    choosed = j - 1;
                }
                j--;
            }
            if (heights[choosed] < heights[K]) {
                heights[choosed]++;
                continue;
            }

            j = K;
            while (j + 1 < n && heights[j + 1] <= heights[j]) {
                if (heights[j + 1] < heights[j]) {
                    choosed = j + 1;
                }
                j++;
            }
            if (heights[choosed] < heights[K]) {
                heights[choosed]++;
                continue;
            }

//            System.out.println(choosed);
            heights[K]++;
//            System.out.println(Arrays.toString(heights));
        }
        return heights;
    }
}
