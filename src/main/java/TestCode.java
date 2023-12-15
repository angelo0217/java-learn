import java.util.*;

public class TestCode {

    //input
    //[ [3,5,6] , [8,3,9,10,2] , [1,3] ]
    //output
    //[3]
    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(new Integer[]{3,5,6}));
        input.add(Arrays.asList(new Integer[]{8,3,9,10,2}));
        input.add(Arrays.asList(new Integer[]{1,3}));

        List<Integer> output = new TestCode().getSameNumLists(input);
        output.forEach(num-> System.out.println("match num: " + num));
    }

    public List<Integer> getSameNumLists(List<List<Integer>> input){
        List<Integer> output = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        input.forEach(lists->{
            lists.forEach(num ->{
                if(map.get(num) == null)
                    map.put(num, 1);
                else {
                    map.put(num, map.get(num) + 1);
                    if(map.get(num) == input.size())
                        output.add(num);
                }
            });
        });
        return output;
    }
}
