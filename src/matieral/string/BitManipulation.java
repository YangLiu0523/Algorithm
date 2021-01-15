package matieral.string;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/repeated-dna-sequences/
 *
 * Bit Manipulation approach in Java is more suitable for the short strings or strings with very limited number of characters
 */

public class BitManipulation {
    class Solution {
        public List<String> findRepeatedDnaSequences(String s) {
            int L = 10, n = s.length();
            if (n < L) return new ArrayList<>();
            int a = 4, aL = (int)Math.pow(a, L);
            Map<Character, Integer> map = new HashMap<>();
            map.put('A', 0);
            map.put('C', 1);
            map.put('G', 2);
            map.put('T', 3);

            int[] nums = new int[n];
            for (int i = 0; i < n; i++){
                nums[i] = map.get(s.charAt(i));
            }

            int bitmask = 0;
            Set<Integer> seen = new HashSet<>();
            Set<String> output = new HashSet<>();
            for (int i = 0; i < L; i++) {
                bitmask <<= 2;
                bitmask |= nums[i];
            }
            seen.add(bitmask);
            for (int i = 1; i < n - L + 1; i++) {
                bitmask <<= 2;
                bitmask |= nums[i + L - 1];
                bitmask &= ~(3 << 2 * L);

                if (seen.contains(bitmask)) {
                    output.add(s.substring(i, i + L));
                }
                seen.add(bitmask);

            }
            return new ArrayList<String>(output);

        }
    }
}
