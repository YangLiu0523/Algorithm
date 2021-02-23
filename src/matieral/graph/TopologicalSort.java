package matieral.graph;

/**
 * Test: https://leetcode.com/problems/course-schedule-ii/
 *
 * Test: https://leetcode.com/problems/parallel-courses/
 * Test: https://leetcode.com/problems/parallel-courses-ii/
 *
 * During the BFS trimming process, we will trim out almost all the nodes v and edges v - 1 from the edges.
 * Therefore, it would take us around O(v)operations to reach the centroids.
 *
 * Space: O(v)
 */

import java.util.*;

public class TopologicalSort {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] p : prerequisites) {
            adj[p[1]].add(p[0]);
            inDegree[p[0]]++;
        }

        Queue<Integer> roots= new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                roots.offer(i);
            }
        }

        int finished = 0;
        int[] order = new int[numCourses];
        while (!roots.isEmpty()) {
            int c = roots.poll();
            order[finished++] = c;
            for (int sub : adj[c]) {
                inDegree[sub]--;
                if (inDegree[sub] == 0) {
                    roots.offer(sub);
                }
            }
        }
        return finished == numCourses ? order : new int[0];
    }

    public int minimumSemesters(int N, int[][] relations) {
        int[] inDegree = new int[N];
        List<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] r : relations) {
            int p = r[0] - 1, c = r[1] - 1;
            adj[p].add(c);
            inDegree[c]++;
        }

        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        int finished = 0, semester = 0;
        while (!queue.isEmpty()) {
            semester++;
            int size = queue.size();
            finished += size;
            for (int i = 0; i < size; i++) {
                int p = queue.poll();
                for (int nei : adj[p]) {
                    if (visited[nei]) {
                        return -1;
                    }
                    inDegree[nei]--;
                    if (inDegree[nei] == 0) {
                        queue.offer(nei);
                        visited[nei] = true;
                    }
                }
            }
        }

        return finished == N ? semester : -1;
    }
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] prerequisites = new int[n];
        for (int[] d : dependencies) {
            prerequisites[d[1] - 1] |= 1 << (d[0] - 1);
        }

        int[] dp = new int[1 << n];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;

        for (int i = 0; i < (1 << n); i++) {
            int availableCourses = 0;
            for (int course = 0; course < n; course++) {
                if ((prerequisites[course] & i) == prerequisites[course]) {
                    availableCourses |= (1 << course);
                }
            }
            availableCourses &= ~i;

            for (int nextSemester = availableCourses; nextSemester > 0; nextSemester = (nextSemester - 1) & availableCourses) {
                if (Integer.bitCount(nextSemester) <= k) {
                    dp[i | nextSemester] = Math.min(dp[i | nextSemester], 1 + dp[i]);
                }
            }
        }
        return dp[(1 << n)- 1];
    }
}
