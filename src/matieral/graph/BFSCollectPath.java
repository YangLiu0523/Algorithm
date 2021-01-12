package matieral.graph;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/word-ladder-ii/
 */

public class BFSCollectPath {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }

        Map<String, List<String>> dict = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                dict.computeIfAbsent(key, v -> new ArrayList<>()).add(word);
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        Map<String, List<String>> childToParents = new HashMap<>();
        while (!queue.isEmpty()) {
            int size = queue.size();

            Set<String> curr = new HashSet<>();
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                for (int j = 0; j < s.length(); j++) {
                    String key = s.substring(0, j) + "*" + s.substring(j + 1);
                    if (dict.containsKey(key)) {
                        for (String nei : dict.get(key)) {
                            if (!visited.contains(nei)) {
                                childToParents.computeIfAbsent(nei, v -> new ArrayList<>()).add(s);
                                curr.add(nei);
                            }
                        }
                    }
                }
            }

            visited.addAll(curr);
            queue.addAll(curr);
            if(childToParents.containsKey(endWord)) {
                break;
            }
        }

        List<List<String>> paths = new ArrayList<>();
        if (childToParents.containsKey(endWord)) {
            construct(paths, new LinkedList<>(), endWord, childToParents);
        }
        return paths;
    }

    private void construct(List<List<String>> paths, LinkedList<String> path, String word, Map<String, List<String>> childToParent) {
        path.addFirst(word);

        if (childToParent.containsKey(word)) {
            for (String parent : childToParent.get(word)) {
                construct(paths, path, parent, childToParent);
            }
        }
        else {
            paths.add(new ArrayList<>(path));
        }

        path.removeFirst();
    }
}