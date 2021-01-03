package matieral.graph;

/**
 * Test: https://leetcode.com/problems/course-schedule-ii/
 *
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
}
