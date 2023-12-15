package leecode;

import model.ListNode;

import java.util.ArrayList;
import java.util.List;

public class LeeCode22 {
    public static void main(String[] args) {
        String aa = "aaa.xx";
        var d = aa.split("\\.");
        System.out.println(d.length);
//        List<String> result = new LeeCode22().generateParenthesis(2);
//        result.forEach(s -> System.out.println(s));
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        test(n, n, "", res, 0);
        return res;
    }

    private void test(int left, int right, String out, List<String> res, int i){
        System.out.println(i+ "    left: "+ left+ ", right:" + right + ", out:" + out);
        if(left < 0 || right < 0 || left > right) return;
        if(left == 0 && right == 0) {
            res.add(out);
            return;
        }
        test(left - 1, right, out + "(" , res, i + 1);
        test(left, right -1, out + ")" , res, i + 1);
    }
}
