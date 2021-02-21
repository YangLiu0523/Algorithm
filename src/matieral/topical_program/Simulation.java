package matieral.topical_program;

/**
 * Test: https://leetcode.com/problems/pour-water/
 * Test: https://leetcode.com/problems/squirrel-simulation/
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

    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int ans = 0, minDelta = height + width;
        for (int i = 0; i < nuts.length; i++) {
            int distTree = Math.abs(nuts[i][0] - tree[0]) + Math.abs(nuts[i][1] - tree[1]);
            int distSq = Math.abs(nuts[i][0] - squirrel[0]) + Math.abs(nuts[i][1] - squirrel[1]);
            minDelta = Math.min(minDelta, distSq - distTree);
            ans +=  2 * distTree;
        }
        return ans + minDelta;
    }
}
