package matieral.topical_program;

import java.util.*;
/**
 * Test: https://leetcode.com/problems/strobogrammatic-number/
 * Test: https://leetcode.com/problems/strobogrammatic-number-ii/
 * Test: https://leetcode.com/problems/strobogrammatic-number-iii/ => Like
 */

public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        int l = 0, r = num.length() - 1;
        while (l < r) {
            int charL = num.charAt(l), charR = num.charAt(r);
            if (charL == charR && (charL == '0' || charL == '1' || charR == '8')) {
                l++;
                r--;
            }
            else if ((charL == '6' && charR == '9') || (charL == '9' && charR == '6')) {
                l++;
                r--;
            }
            else return false;
        }

        if (l == r) {
            char c = num.charAt(l);
            return c == '0' || c == '1' || c == '8';
        }
        return true;
    }

    public List<String> findStrobogrammatic(int n) {
        int[] choose = {0, 1, 8, 6, 9};
        List<String> res = new ArrayList<>();
        int[] tmp = new int[n];
        Arrays.fill(tmp, -1);

        backtrack(choose, tmp, 0, res, n);
        return res;
    }

    private void backtrack(int[] choose, int[] tmp, int idx, List<String> res, int len) {
        for (int num : choose) {
            if (idx == 0 && num == 0 && len != 1) continue;
            if (len - 1 - idx == idx && (num == 6 || num == 9)) continue;
            tmp[idx] = num;
            if (len - 1 - idx != idx) {
                if (num == 0 || num == 1 || num == 8) {
                    tmp[len - 1 - idx] = num;
                }
                else if (num == 6) {
                    tmp[len - 1 - idx] = 9;
                }
                else {
                    tmp[len - 1 - idx] = 6;
                }
            }

            if (idx + 1 < tmp.length && tmp[idx + 1] == -1) {
                backtrack(choose, tmp, idx + 1, res, len);
            }
            else {
                StringBuilder sb = new StringBuilder();
                for (int n : tmp) sb.append(n);
                res.add(sb.toString());
            }


            tmp[idx] = -1;
            tmp[len - 1 - idx] = -1;
        }
    }
    public int strobogrammaticInRange(String low, String high) {
        List<String> baseList = new ArrayList<>(Arrays.asList("", "1", "0", "8"));
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');

        int ret = 0;
        for (String s : baseList) {
            ret += backtrack(map, s, low, high);
        }
        return ret;
    }

    private int backtrack(Map<Character, Character> map, String s, String lo, String hi) {
        int ret = 0;
        if (compare(s, hi) > 0) return 0;
        if (compare(s, lo) >= 0 && (lo.equals("0") && s.length() == 1 || s.charAt(0) != '0')) {
            ret++;
        }
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            String next = entry.getKey() + s + entry.getValue();
            ret += backtrack(map, next, lo, hi);
        }
        return ret;
    }

    private int compare(String a, String b) {
        return a.length() == b.length() ? a.compareTo(b) : a.length() - b.length();
    }
}
