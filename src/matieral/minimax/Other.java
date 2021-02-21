package matieral.minimax;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/escape-the-ghosts/
 * Test: https://leetcode.com/problems/guess-the-word/
 */

public class Other {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int dist = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] g : ghosts) {
            int d = Math.abs(g[0] - target[0]) + Math.abs(g[1] - target[1]);
            if (d <= dist) return false;
        }
        return true;
    }

    public void findSecretWord(String[] wordlist, Master master) {
        for (int t = 0; t < 10; t++) {
            int[][] count = new int[6][26];
            for (String word : wordlist) {
                for (int i = 0; i < 6; i++) {
                    count[i][word.charAt(i) - 'a']++;
                }
            }
            String guess = wordlist[0];
            int best = 0;
            for (String word : wordlist) {
                int score = 0;
                for (int i = 0; i < 6; i++) {
                    score += count[i][word.charAt(i) - 'a'];
                }
                if (score > best) {
                    best = score;
                    guess = word;
                }
            }

            int x = master.guess(guess);
            List<String> list2 = new ArrayList<>();
            for (String word : wordlist) {
                if (match(word, guess) == x) { // Important
                    list2.add(word);
                }
            }
            wordlist = list2.toArray(new String[0]);
        }
    }
    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i)
            if (a.charAt(i) == b.charAt(i))
                matches ++;
        return matches;
    }
}
class Master {
    public int guess(String word) {return -1;}
}