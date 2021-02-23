package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/word-ladder/
 * Test: https://leetcode.com/problems/word-ladder-ii/
 * Test: https://leetcode.com/problems/word-search/
 *
 * Test: https://leetcode.com/problems/word-break/
 * Test: https://leetcode.com/problems/word-break-ii/
 *
 */

public class Word {
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

            set1= newSet;
        }
        return 0;
    }

    int l;
    Map<String, List<String>> dict;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }
        l = beginWord.length();
        dict = new HashMap<>();
        wordList.forEach(word -> {
            for (int i = 0; i < l; i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1, l);
                dict.putIfAbsent(key, new ArrayList<>());
                dict.get(key).add(word);
            }
        });

        Map<String,List<String>> parent = new HashMap<>();

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);

        while (!queue.isEmpty()){
            int size = queue.size();

            Set<String> thisLevel = new HashSet<>();

            for(int i = 0; i < size; i++) {
                String word = queue.poll();
                for (int j = 0; j < l; j++) {
                    String key = word.substring(0, j) + "*" + word.substring(j + 1, l);
                    if(dict.containsKey(key)){
                        for (String s : dict.get(key)) {
                            if (!visited.contains(s)) {//不重复处理前几层的数据（成环），但是会为当前的这一层找全父节点
                                parent.putIfAbsent(s, new ArrayList<>());
                                parent.get(s).add(word);
                                thisLevel.add(s);
                            }

                        }
                    }
                }
            }

            for (String s : thisLevel) {
                visited.add(s);
                queue.offer(s);
            }

            if (parent.get(endWord) != null) break;
        }


        List<List<String>> res = new ArrayList<>();
        if (parent.containsKey(endWord)) {
            construct(parent, res, new ArrayList<>(), endWord);
        }
        return res;
    }

    private void construct(Map<String, List<String>> map, List<List<String>> res, List<String> tmp, String word) {
        tmp.add(0, word);

        if (map.containsKey(word)) {
            for (String s : map.get(word)) {
                construct(map, res, tmp, s);
            }
        }
        else {
            res.add(new ArrayList<>(tmp));
        }

        tmp.remove(0);
    }

    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (backtrack1(board, i, j, word, 1)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }

        return false;
    }

    private boolean backtrack1(char[][] board, int i, int j, String word, int idx) {
        if (idx == word.length()) {
            return true;
        }

        for (int[] dir : dirs) {
            int r = dir[0] + i, c = dir[1] + j;
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && !visited[r][c]) {
                visited[r][c] = true;
                if (board[r][c] == word.charAt(idx)) {
                    if (backtrack1(board, r, c, word, idx + 1)) {
                        return true;
                    }
                }
                visited[r][c] = false;
            }
        }
        return false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.links[c - 'a'] == null) {
                    node.links[c - 'a'] = new TrieNode();
                }
                node = node.links[c - 'a'];
            }
            node.idx = i;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.links[board[i][j] - 'a'] != null) {
                    visited[i][j] = true;
                    if (root.links[board[i][j] - 'a'].idx != -1) {
                        res.add(words[root.links[board[i][j] - 'a'].idx]);
                        root.links[board[i][j] - 'a'].idx = -1;
                    }
                    backtrack1(board, words, root.links[board[i][j] - 'a'], visited, i, j, res);
                    visited[i][j] = false;
                }
            }
        }
        return res;
    }

    private void backtrack1(char[][] board, String[] words, TrieNode root, boolean[][] visited, int r, int c, List<String> res) {
        for (int[] dir : dirs) {
            int i = r + dir[0], j = c + dir[1];
            if (i >= 0 && i < board.length && j >= 0 && j < board[0].length && !visited[i][j]) {
                visited[i][j] = true;
                TrieNode node = root.links[board[i][j] - 'a'];
                if (node != null) {
                    backtrack1(board, words, node, visited, i, j, res);
                    if (node.idx != -1) {
                        res.add(words[node.idx]);
                        node.idx = -1;
                    }
                }
                visited[i][j] = false;
            }
        }
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        List<String> res = backtrack(s, 0, dict, memo);
        return res;
    }

    private List<String> backtrack(String s, int idx, Set<String> dict, Map<Integer, List<String>> memo) {
        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }

        List<String> ret = new ArrayList<>();

        for (int i = idx + 1; i <= s.length(); i++) {
            String sub = s.substring(idx, i);
            if (dict.contains(sub)) {
                if (i < s.length()) {
                    List<String> back = backtrack(s, i, dict, memo);
                    for (String l : back) {
                        String newStr = sub + " " + l;
                        ret.add(newStr);
                    }
                }
                else {
                    ret.add(sub);
                }

            }
        }
        memo.put(idx, ret);
        return memo.get(idx);
    }

}

class TrieNode {
    TrieNode[] links;
    int idx;
    public TrieNode() {
        links = new TrieNode[26];
        idx = -1;
    }
}