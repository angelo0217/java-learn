import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test12 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 4, 2, 5, 6, 8, 9, 3};
        Arrays.sort(nums);
        print(nums);

        List<List<Integer>> listNum = threeSum(nums, 10);
        for (List<Integer> integers : listNum) {
            integers.forEach(num -> System.out.print(num + ","));
            System.out.println();
        }
    }

    public static void print(int[] nums){
        Arrays.stream(nums).forEach(num ->{
            System.out.print(num + ",");
        });
        System.out.println();
    }

    public static List<List<Integer>> threeSum(int[] nums, int target) {
        var mainList = new ArrayList<List<Integer>>();

        for(int i = 0; i < nums.length; i++){
            if(nums[i] + nums[i + 1] > target){
                System.out.println("~~~~~~~~~~~~~~");
                return mainList;
            }
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int j = i + 1, k = nums.length - 1;

                while (j < k){
                    if((nums[i] + nums[j] + nums[k]) == target){
                        mainList.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        while(j < k && nums[j] == nums[j + 1]) j ++;
                        while(j < k && nums[k] == nums[k - 1]) k --;
                        j ++;
                        k --;
                    } else if((nums[i] + nums[j] + nums[k]) < target){
                        j ++;
                    } else {
                        k --;
                    }
                }
            }
        }

        return mainList;
    }

}

