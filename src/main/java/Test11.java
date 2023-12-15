public class Test11 {

    public static void main(String[] args) {
        var nums = new int[] {1, 2, 0, 4, 0, 0, 5, 6};

        moveZero(nums);

        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void moveZero(int[] n) {
        int base = 0;
        for(int i = 0; i < n.length; i++){
            if(n[i] != 0){
                n[base] = n[i];
                base ++;
            }
        }
        for(int i = (base +1); i < n.length; i++){
            n[i] = 0;
        }
    }

}

