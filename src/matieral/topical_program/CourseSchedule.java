package matieral.topical_program;

import java.util.*;
/**
 * Test: https://leetcode.com/problems/course-schedule/ => Like
 * Test: https://leetcode.com/problems/course-schedule-ii/
 * Test: https://leetcode.com/problems/course-schedule-iii/
 * Test: https://leetcode.com/problems/course-schedule-iv/ => Failed many times
 */

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] p : prerequisites) {
            adj[p[1]].add(p[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] onStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (!dfs(adj, visited, onStack, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(List<Integer>[] adj, boolean[] visited, boolean[] onStack, int idx) {
        if (visited[idx]) {
            return true;
        }
        if (onStack[idx]) {
            return false;
        }

        onStack[idx] = true;
        for (int nei : adj[idx]) {
            if (!dfs(adj, visited, onStack, nei)) {
                return false;
            }
        }
        visited[idx] = true;
        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] p : prerequisites) {
            adj[p[1]].add(p[0]);
            inDegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] res = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                res[idx++] = course;
                for (int nei : adj[course]) {
                    inDegree[nei]--;
                    if (inDegree[nei] == 0) {
                        queue.offer(nei);
                    }
                }
            }
        }

        return idx == numCourses ? res : new int[0];
    }

    public int scheduleCourse(int[][] courses) {
        int n = courses.length;
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        int takenCourses = 0, time = 0;
        for (int i = 0; i < n; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                courses[takenCourses++] = courses[i];
            }
            else {
                int maxDuration = i;
                for (int j = 0; j < takenCourses; j++) {
                    if (courses[j][0] > courses[maxDuration][0]) {
                        maxDuration = j;
                    }
                }
                if (courses[maxDuration][0] > courses[i][0]) {
                    time -= (courses[maxDuration][0] - courses[i][0]);
                    courses[maxDuration] = courses[i];
                }
            }
        }
        return takenCourses;
    }

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] isReachable = new boolean[n][n];

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int[] p : prerequisites) {
            adj[p[0]].add(p[1]);
        }

        for (int i = 0; i < n; i++) {
            connect(adj, i, i, isReachable);
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(isReachable[q[0]][q[1]]);
        }
        return res;
    }
    private void connect(List<Integer>[] adj, int u, int v, boolean[][] isReachable) {
        if (isReachable[u][v]) {
            return;
        }

        isReachable[u][v] = true;
        for (int w : adj[v]) {
            connect(adj, u, w, isReachable);
        }
    }
}
