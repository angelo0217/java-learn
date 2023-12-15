package leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 無重複字串長度
 */
public class LeeCode10 {

    public static void main(String[] args) {
        var s = "aa";
        var condition = "a*";
        System.out.println("ans:" + new LeeCode10().isMatch(s, condition));
    }



    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i <= p.length(); i++){
            for(int j = 0; j <= s.length(); j++){
                if(p.charAt(i - 1) == '*'){
                    if(i > 1){
                        System.out.println("i :" + i + ", j: " + j + "," + dp[i - 2][j]);
                        dp[i][j] |= dp[i - 2][j];//初始值
                        if(j > 0 && (p.charAt(i - 2) == '.' || p.charAt(i - 2) == s.charAt(j - 1))){
                            System.out.println("i :" + i + ", j: " + j + ",dp[i][j - 1] : " + dp[i][j - 1] + ", dp[i - 1][j - 1]:"+ dp[i - 1][j - 1]);
                            //如果為true會一直往後，但中間一個不符，就會是false
                            dp[i][j] |= dp[i][j - 1] | dp[i - 1][j - 1];
                        }
                    }
                } else {
                    if(j > 0 && (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j - 1))){
                        System.out.println("**i :" + i + ", j: " + j + ",dp[i - 1][j - 1]" + dp[i - 1][j - 1]);
                        dp[i][j] |= dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[p.length()][s.length()];
    }
}
