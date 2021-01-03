package matieral.graph;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/word-ladder/
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
}
