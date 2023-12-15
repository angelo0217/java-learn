package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 無重複字串長度
 */
public class LeeCode3 {

    public static void main(String[] args) {
        var s = "abcadefz";
        System.out.println(new LeeCode3().lengthOfLongestSubstring2(s));
    }
    public int lengthOfLongestSubstring2(String s) {
        int[] last = new int[128];
        Arrays.fill(last, -1);

        int start = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {

            if (last[s.charAt(i)] != -1)
                start = Math.max(start, last[s.charAt(i)] + 1);
            last[s.charAt(i)] = i;

            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) return 0;

        var map = new HashMap<Integer, Integer>();
        int start = 0;
        int ans = 0;
        int num;
        for (int i = 0; i < s.length(); i++) {
            num = s.charAt(i);
            if(map.get(num) != null)
                start = Math.max(start, map.get(num) +1);

            map.put(num, i);
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 0;
        var list = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i);
            if(list.contains(num)){
                if(list.size() > max) max = list.size();
                var newL = new ArrayList<Integer>();
                for (int j = list.indexOf(num) + 1; j < list.size(); j ++) {
                    newL.add(list.get(j));
                }

                list = newL;
            }
            list.add(num);
        }

        if(list.size() > max) max = list.size();

        return max;
    }
}
