import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeeCode125 {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(new LeeCode125().isPalindrome(s));
    }


    public boolean isPalindrome(String s) {
        Pattern pattern = Pattern.compile("[\\W|_]");
        Matcher matcher = pattern.matcher(s);
        s = matcher.replaceAll("").toLowerCase();

        int i = 0, j = s.length() - 1;

        while(i < j){
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }



}

