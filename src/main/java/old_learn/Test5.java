package old_learn;

import model.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test5 {
    public static void main(String[] args){
        System.out.println(removeParentheses("((dd)f)gg)"));
    }
    public static String removeParentheses(String s) {
        int left = 0;
        int right = 0;
        List<Integer> leftIdx = new ArrayList<>();
        List<Integer> rightIdx = new ArrayList<>();
        for (int i = 1; i <= s.length(); i++) {
            String tmp = s.substring(i - 1, i);
            if (tmp.equals("(")) {
                left = left + 1;
                leftIdx.add(i);
            } else if (tmp.equals(")")) {
                right = right + 1;
                rightIdx.add(i);
            }
        }

        if (left > 0 && right > 0) {
            int remove = 0;
            if (left > right) {
                if (right == 0) {
                    return "";
                }
                remove = new Random().nextInt(leftIdx.size() - 1);
                remove = leftIdx.get(remove);
            } else if (right > left) {
                if (left == 0) {
                    return "";
                }
                remove = new Random().nextInt(rightIdx.size() - 1);
                remove = rightIdx.get(remove);
            }
            return s.substring(0, remove - 1) + s.substring(remove);
        }
        return "";
    }
}
