package matieral.graph;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/cracking-the-safe/
 * Reference: https://www.youtube.com/watch?v=kRdlLahVZDc
 */

public class Halmiton {

    String chain = "";
    public String crackSafe(int n, int k) {
        int totalSecret = (int)Math.pow(k, n);

        Set<String> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("0");
        }
        String start = sb.toString();
        visited.add(start);
        chain += start;

        search(k, totalSecret, visited, start);
        return chain;
    }

    private boolean search(int k, int totalSecret, Set<String> visited, String secret) {
        for (int i = 0; i < k; i++) {
            String newSecret = secret.substring(1) + Integer.toString(i);
            if (!visited.contains(newSecret)) {
                chain += i;
                visited.add(newSecret);
                if (visited.size() == totalSecret) {
                    return true;
                }
                else {
                    if (search(k, totalSecret, visited, newSecret)) {
                        return true;
                    }
                }
                chain = chain.substring(0, chain.length() - 1);
                visited.remove(newSecret);
            }
        }
        return false;
    }
}