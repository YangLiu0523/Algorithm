package matieral.tree;

/**
 * Test: https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 */

class TrieNode {
    TrieNode[] links;
    boolean isEnd;

    public TrieNode() {
        links = new TrieNode[26];
        isEnd = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.links[c - 'a'] == null) {
                node.links[c - 'a'] = new TrieNode();
            }
            node = node.links[c - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.links[c - 'a'] == null) return false;
            node = node.links[c - 'a'];
        }
        return node.isEnd;
    }

    public boolean startsWith(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.links[c - 'a'] == null) return false;
            node = node.links[c - 'a'];
        }
        return true;
    }
}