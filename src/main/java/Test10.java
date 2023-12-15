public class Test10 {

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }

    public static int climbStairs(int n) {
        if(n <= 2) return n;
        int[] res = new int[n];
        res[1-1] = 1;
        res[2-1] = 2;
        for(int i = 3; i <= n; i++) {
            res[i-1] = res[i-1-1] + res[i-2-1];
            System.out.println((i-1) + ":" + res[i-1]);
        }
        return res[n-1];
    }

}

