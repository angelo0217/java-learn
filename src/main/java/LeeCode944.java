public class LeeCode944 {

    public static void main(String[] args) {
        var strings = new String[]{"zyx","wvu","tsr"};
        System.out.println(new LeeCode944().deleteNoSortSize(strings));
//        isNoSort("abc");
    }


    public  int deleteNoSortSize(String[] strings) {
        if (strings == null) {
            return 0;
        }
        int count =0 ;
        int len = strings[0].length();

        for (int i = 0; i < len; i++) {
            if(isNoSort(strings[i])) count++;
        }
        return count;
    }

    public static boolean isNoSort(String str) {
        if(str == null || str.length() <= 1){
            return false;
        }

        int now;
        int next;

        for(int i = 0; i < str.length() - 1; i++) {
            now = str.charAt(i);
            next = str.charAt(i +1);
            if(now > next){
                return true;
            }
        }
        return false;
    }

}

