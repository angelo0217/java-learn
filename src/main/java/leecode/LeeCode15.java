package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeeCode15 {
    public static void main(String[] args){
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> listNum = threeSum(nums);
        for (List<Integer> integers : listNum) {
            integers.forEach(num -> System.out.print(num + ","));
            System.out.println();
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 2; i++){
            if(i == 0 || (i > 0 && nums[i] != nums[i - 1])){
                int s = i + 1, e = nums.length - 1, target = 0 - nums[i];
                while (s < e) {
                    if(nums[s] + nums[e] == target){
                        res.add(Arrays.asList(nums[i], nums[s], nums[e]));
                        while(s < e && nums[s] == nums[s + 1]) s++;
                        while(s < e && nums[e] == nums[e - 1]) e--;
                        s++;
                        e--;
                    } else if(nums[s] + nums[e] < target){
                        s++;
                    } else {
                        e--;
                    }
                }
            }
        }
        return res;
    }
}
