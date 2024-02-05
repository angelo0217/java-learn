package reactor_lean;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class FluxAPIDemo {
    public static Mono<String> oneMono() {
        return Mono.just("a");
    }

    public static void main(String[] args) {
//        目前最多合併8個
        Flux.zip(Flux.just(1, 2), Flux.just("a", "b"), Flux.just("r", "t"))
                .log()
                .map(tuple -> {
                    Integer t1 = tuple.getT1();
                    String t2 = tuple.getT2();
                    String t3 = tuple.getT3();
                    return t1 + t2 + t3;
                })
                .log().subscribe();
    }

    public static void zipWith() {
        // zipWith 將不同的流，相同index的位置合併新的陣列當單一元素，超過的index捨棄
        Flux.just(1, 2, 3, 8).zipWith(Flux.just("a", "b", "c"))
                .log()
                .map(tuple -> {
                    Integer t1 = tuple.getT1();
                    String t2 = tuple.getT2();
                    return t1+""+t2;
                })
                .log().subscribe();

    }

    public static void merge() throws IOException {
//        concat 強制有順序，連接
        //merge 會依照誰先發布先返回，合併
//      mergeSequential 看主流，哪個元素先到，該流會先跑完，再換第二個
        Flux.merge(
                Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1)),
                Flux.just("a", "b").delayElements(Duration.ofMillis(1500)),
                Flux.just("ee", "rr", "tt").delayElements(Duration.ofMillis(800))
        ).log().subscribe();

        Flux.just(1, 2, 3).mergeWith(Flux.just(4,5)).log().subscribe();
        System.in.read();
    }

    public static void empty() {
//        defaultIfEmpty 沒有值誒設返回
//        switchIfEmpty 空轉換，調用其他流
        oneMono()
//            .defaultIfEmpty("x")
                .switchIfEmpty(Mono.just("xx"))
            .subscribe(v -> System.out.println("v: " + v));
    }

    public static void transform() {
        AtomicInteger atomic = new AtomicInteger();
        var flux = Flux.just("a", "b", "c")
//                .transform(//不共享外部變數，無論多少訂閱者 transform 只跑一次
                .transformDeferred( //共享外部變數, 每個訂閱者都執行
                        val -> {
//                      ++atomic 先加在做事情
                            if (atomic.incrementAndGet() == 1)
                                return val.map(String::toUpperCase);
                            else
                                return val;
                        }
                );
        flux.subscribe(v -> System.out.println("s1: " + v));
        flux.subscribe(v -> System.out.println("s2: " + v));
    }
}
