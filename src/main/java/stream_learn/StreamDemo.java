package stream_learn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void createStream() {
//        1、創建流
        Stream<Integer> stream = Stream.of(1, 2, 3);
        Stream<Integer> contact = Stream.concat(Stream.of(2, 3, 4), stream);
        Stream<Object> build = Stream.builder().add("11").add("22").build();
//       2、從集合中創建流
        List<Integer> integers = List.of(1, 2);
        Stream<Integer> stream1 = integers.stream();

        Set<Integer> integers1 = Set.of(1, 2);
        integers1.stream();

        Map<Object, Object> map = Map.of("A", 1, "B", 2);
        map.keySet().stream().forEach(System.out::println);
        map.values().stream().forEach(v -> System.out.println("value:" + v));
        map.entrySet().stream().forEach(entry -> {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("鍵：" + key + "，值：" + value);
        });

//        流的三部分
//        1、數據流   2、N個中間操作   3、一個終止操作
//        從線程可以看到也是for循環，單線程
//        使用 parallel 則變成併發，但需自行解決多線程安全
//        流本身的操作應該維持無狀態
        System.out.println("main thread: " + Thread.currentThread());
//        https://blog.csdn.net/qq_37687594/article/details/119860341
//        此篇文章有說，使用並行流要非常小心
        ForkJoinPool pool = new ForkJoinPool(2);
        long count = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .parallel()// intermediate operation. 變成併發，就不會是同線程，默認不併發
                .filter(i -> { // intermediate operation.
                    System.out.println("thread: " + Thread.currentThread());
                    System.out.println("run filter" + i);
                    return i % 2 == 0;
                }).count(); //一個終止操作  terminal operation.
        pool.shutdown();
        System.out.println("count: " + count);
    }

    /**
     * 不建議使用，但真要用可以這樣解決
     */
    public static void multiThreadSave() {
        List tmp = new ArrayList();
//        亦可使用安全的list,這樣就不用加鎖
//        List<Object> tmp2 = Collections.synchronizedList(new ArrayList<>());
        System.out.println("main thread: " + Thread.currentThread());
        long count = Stream.of(1, 2, 3, 4, 5)
                .parallel()
                .filter(i -> {
                    synchronized (Object.class) {
                        tmp.add(i);
                        return i % 2 == 0;
                    }
                }).count();
        System.out.println("count: " + count);
        tmp.stream().forEach(System.out::println);
    }

    public static void parallelStream() {
        List<String> dataList = Arrays.asList("apple", "banana", "orange", "grape", "melon");
//         可設定 parallel 併發數
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(parallelism));
        dataList.parallelStream()
                .forEach(v -> {
                    System.out.println("main thread: " + Thread.currentThread());
                    System.out.println(v);
                });
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Person {
        private String name;
        private String gender;
        private Integer age;
    }

    public static void streamIntermediateOperationLearn() {
//        filter: 過濾資料
//        map: 映射 a -> b
//        flatMap: 一對多映射，打散 散列
        List<Person> personList = List.of(
                new Person("Dean Wu", "男", 18),
                new Person("Mary Li", "女", 23),
                new Person("Jason Tsao", "男", 15),
                new Person("Mark Li", "男", 55),
                new Person("Gary Yeh", "男", 12)
        );

        personList.stream()
//                .limit(3) 放前面會先過濾，後面會全部跑完
                 .filter(p -> p.age > 18)
                .peek(p -> System.out.println("peek:"+ p))// 僅看不操作
                .map(p -> p.getName())
                .peek(System.out::println)
                .flatMap(ele -> {
                    String[] s = ele.split(" ");
                    return Arrays.stream(s);
                })
                .distinct()
                .sorted(String::compareTo)
                .forEach(System.out::println);

        List<Integer> tmp = List.of(4, 2, 3, 4, 5, 6)
                .stream()
//                .filter(i -> i > 2)
                .takeWhile(i -> i > 2)// 當滿足條件繼續，不滿足則中斷
                .collect(Collectors.toList());
        System.out.println(tmp);
    }

    public static void streamGroupByLearn() {
        List<Person> personList = List.of(
                new Person("Dean Wu", "男", 18),
                new Person("Mary Li", "女", 23),
                new Person("Jason Tsao", "男", 15),
                new Person("Mark Li", "男", 55),
                new Person("Gary Yeh", "男", 12)
        );

        Map<String, List<Person>> collect = personList.stream()
                .filter(p -> p.getAge() > 15)
                .collect(Collectors.groupingBy(p -> p.getGender()));
        System.out.println(collect);
    }

    public static void main(String[] args) {
        streamGroupByLearn();
    }

}
