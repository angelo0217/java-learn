package leecode;

import java.util.Stack;

public class LeeCode20 {
    public static void main(String[] args) {
        String str = "(([]){})";
        System.out.println(new LeeCode20().isValid(str));
    }

    public boolean isValid(String s) {
        Stack<Character> parentheses = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') parentheses.push(s.charAt(i));
            else {
                if (parentheses.empty()) return false;
                Character character = parentheses.pop();
                if (s.charAt(i) == ')' && character != '(') return false;
                if (s.charAt(i) == ']' && character != '[') return false;
                if (s.charAt(i) == '}' && character != '{') return false;

            }
        }
        return parentheses.empty();
    }

}
