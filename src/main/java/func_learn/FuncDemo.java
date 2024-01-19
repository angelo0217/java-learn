package func_learn;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FuncDemo {
    Function<String, String> compString = word -> word + "Hello";

    public void runFuncDemo1() {
        System.out.println(compString.apply("Dean"));
    }


    public void allDemo() {
        Supplier<String> supplier = () -> "33";
        Predicate<String> isNumber = str -> str.matches("-?\\d+(\\.\\d+)?");
        Function<String, Integer> change = Integer::parseInt;

        Consumer<Integer> consumer = integer -> {
            if (integer % 2 == 0) {
                System.out.println("偶數");
            } else {
                System.out.println("基數");
            }
        };

        if (isNumber.test(supplier.get())) {
            String s = supplier.get();
            consumer.accept(change.apply(s));
        }
    }

    private static void myMethod(
            Supplier<String> supplier,
            Predicate<String> isNumber,
            Function<String, Integer> change,
            Consumer<Integer> consumer
    ) {
        if (isNumber.test(supplier.get())) {
            String s = supplier.get();
            consumer.accept(change.apply(s));
        }
    }

    public static void main(String[] args) {
        new FuncDemo().allDemo();
        myMethod(
                () -> "444",
                str -> str.matches("-?\\d+(\\.\\d+)?"),
                Integer::parseInt,
                System.out::println
        );
    }
}
