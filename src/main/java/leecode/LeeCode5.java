package leecode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 無重複字串長度
 */
public class LeeCode5 {

    public static void main(String[] args) {
        var s = "sabas";//cbbd,

//        for(int i = 0; i < test.length; i++){
//            System.out.println(test[i]);
//        }


        System.out.println("ans:" + new LeeCode5().longestPalindrome3(s));
    }

    public String longestPalindrome3(String s) {
        if (s.length() <= 1) return s;
        char in = "#".charAt(0);
        char[] conditionStr = manacherStrings(s, in);
        int start = 0;
        int end = -1;
        int x, y;

        for (int i = 1; i < conditionStr.length; i++) {
            x = i;
            y = i;
            while (x > 0 && y < conditionStr.length) {
                if (conditionStr[x] == conditionStr[y]) {

                    System.out.println("x" + x + ",y" + y);
                    if ((y - x) > (end - start)) {
                        start = x;
                        end = y;
                    }
                } else {
                    System.out.println("break==" + "x" + x + ",y" + y);
                    break;
                }
                x--;
                y++;

            }
            System.out.println("1s:" + start + ", e:" + end);
            System.out.println("---------------------");
        }


        start = start / 2;
        end = end / 2 + end % 2;

        System.out.println("s:" + start + ", e:" + end);
        return s.substring(start, end);
    }

    public char[] manacherStrings(String s, char in) {
        char[] res = new char[s.length() * 2 + 1];
        for (int i = 0; i < s.length(); i++) {

            res[i * 2] = in;
            res[i * 2 + 1] = s.charAt(i);
        }
        res[s.length() * 2] = in;
        return res;
    }

    public String longestPalindrome2(String s) {
        if (s.length() <= 1) return s;

        String ans = "";
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            if ((end - start) > (s.length() - 1 - i)) break;

            for (int j = s.length() - 1; j > i; j--) {
                System.out.println("--");
                if (s.charAt(i) != s.charAt(j)) continue;
                if (isPalindrome(s, i, j)) {
                    if ((j - i) > (end - start)) {
                        start = i;
                        end = j;
                    }
                    break;
                }
            }
        }


        ans = s.substring(start, end + 1);
        if (ans.length() == 0 && s.length() > 0) {
            return String.valueOf(s.charAt(0));
        }
        return ans;
    }

    private boolean isPalindrome(String s, int x, int y) {
        while (x < y) {
            if (s.charAt(x) == s.charAt(y)) {
                x++;
                y--;
            } else {
                return false;
            }
        }
        return true;
    }


    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String ans = "";

        int x;
        int y;
        int start = 0;
        int end = 0;
        boolean same = false;
        for (int i = 0; i < s.length(); i++) {

            if ((end - start) > (s.length() - 1 - i)) {
                break;
            }

            for (int j = s.length() - 1; j > i; j--) {
                if (s.charAt(i) != s.charAt(j)) continue;

                same = false;
                x = i;
                y = j;
                while (x < y) {
                    if (s.charAt(x) == s.charAt(y)) {
                        same = true;
                        x++;
                        y--;
                    } else {
                        same = false;
                        break;
                    }
                }
                if (same) {
                    if ((j + 1 - i) > (end - start)) {
                        start = i;
                        end = j;
                    }
                }
                if ((end - start) == s.length() - 1)
                    break;
            }

        }

        ans = s.substring(start, end + 1);
        if (ans.length() == 0 && s.length() > 0) {
            return String.valueOf(s.charAt(0));
        }
        return ans;
    }

}
