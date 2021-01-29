package matieral.graph.traversal;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/word-ladder/
 * Test: https://leetcode.com/problems/jump-game-iv/
 * Reference: https://leetcode.com/problems/word-ladder/discuss/986098/Java-12ms-98-solution-bi-directional-BFS-with-layers-and-pruning
 */

public class biDirectionalBFS {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> allWords = new HashSet<>(wordList);
        if (!allWords.contains(endWord)) {
            return 0;
        }

        Set<String> set1 = new HashSet<>();
        set1.add(beginWord);
        Set<String> set2 = new HashSet<>();
        set2.add(endWord);

        int level = 0;
        while (!set1.isEmpty() && !set2.isEmpty()) {
            level++;

            if (set1.size() > set2.size()) {
                Set<String> tmp = set1;
                set1 = set2;
                set2 = tmp;
            }

            allWords.removeAll(set1);
            allWords.removeAll(set2);

            Set<String> newSet = new HashSet<>();
            for (String word : set1) {
                for (int i = 0; i < word.length(); i++) {
                    char[] chs = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chs[i] = ch;
                        String newWord = String.valueOf(chs);
                        if (set2.contains(newWord)) return level + 1; // Layers connect, we can return
                        if (allWords.contains(newWord)) newSet.add(newWord);
                    }
                }
            }

            set1 = newSet;
        }
        return 0;
    }

    public int minJumps(int[] arr) {
        if (arr.length < 2) return 0;
        // else if (arr[0] == arr[arr.length - 1]) return 1;

        Map<Integer, List<Integer>> equalVals = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            equalVals.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
        }

        Set<Integer> set1 = new HashSet<>();
        set1.add(0);
        Set<Integer> set2 = new HashSet<>();
        set2.add(arr.length - 1);

        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        visited.add(arr.length - 1);

        int steps = 0;
        while (!set1.isEmpty() && !set2.isEmpty()) {
            if (set1.size() > set2.size()) {
                Set<Integer> tmp = set1;
                set1 = set2;
                set2 = tmp;
            }

            Set<Integer> newSet = new HashSet<>();
            for (int node : set1) {
                for (int nei : equalVals.get(arr[node])) {
                    if (set2.contains(nei)) {
                        return steps + 1;
                    }
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        newSet.add(nei);
                    }
                }

                equalVals.get(arr[node]).clear();
                if (set2.contains(node + 1) || set2.contains(node - 1)) {
                    return steps + 1;
                }
                if (node + 1 < arr.length && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    newSet.add(node + 1);
                }
                if (node - 1 >= 9 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    newSet.add(node - 1);
                }

            }

            set1 = newSet;
            steps++;
        }
        return steps;
    }
}
