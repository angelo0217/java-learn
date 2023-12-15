package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeeCode17 {
    public static void main(String[] args) {
//        char a = '1';
////        char z = 'z';
//        System.out.println((int) a);
//        System.out.println(Integer.valueOf("23".charAt(0)));
        List<String> list = new LeeCode17().letterCombinations("234");
        list.forEach(s -> System.out.println(s));
    }

    static String[] info[] = {{""}, {""}, {"a","b","c"}, {"d","e","f"}, {"g","h","i"}, {"j","k","l"}, {"m","n","o"}, {"p","q","r","s"}, {"t","u","v"}, {"w","x","y","z"}};

    private static void append(String digits, String prefix, int idx, List<String> list) {
        String[] strs = info[digits.charAt(idx) - '0'];
        if ((digits.length() - 1) == idx) {
            for (int i = 0; i < strs.length; i++) {
                list.add(prefix + strs[i]);
            }
        } else {
            for (int i = 0; i < strs.length; i++) {
                append(digits, prefix + strs[i], idx + 1, list);
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() < 1){
            return new ArrayList<>();
        }else if (digits.length() == 1) {
            return Arrays.asList(info[Integer.valueOf(digits)]);
        } else {
            List<String> list = new ArrayList<>();
            append(digits, "", 0, list);
            return list;
        }
    }
}
