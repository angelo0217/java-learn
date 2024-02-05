package reactor_lean;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.io.IOException;
import java.time.Duration;

public class FluxDemo {

    static class MySubscribe<T> extends BaseSubscriber<T> {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("hookOnSubscribe " + subscription);
            request(1);
//                requestUnbounded(); //要數據最大值，則不需再 on next 加上 request
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println("hookOnNext: " + value);

            if (value instanceof Integer) {
                if ((Integer) value == 1) {
                    cancel(); //主動取消流
//                    throw new RuntimeException("haha");
                }
            }

//            cancel(); 主動取消流
            request(1);
        }

        @Override
        protected void hookOnComplete() {
            System.out.println("hookOnComplete");
        }

        @Override
        protected void hookOnError(Throwable throwable) {
            System.out.println("hookOnCancel");
        }

        @Override
        protected void hookOnCancel() {
            System.out.println("hookOnCancel");
        }

        @Override
        protected void hookFinally(SignalType type) {
            System.out.println("hookFinally------");
        }
    }

    public static void main(String[] args) throws IOException {
//        flaxDoOn();
        Flux<String> flow = Flux.range(1, 10)
                .flatMap(i -> {
                    System.out.println("flatMap ..." + i);
                    //可針對單筆資料改程流回傳，最一個若有錯誤替代值的方式
                    return Mono.just(i)
                            .map(value -> {
                                if (value == 8) {
                                    throw new RuntimeException("Error at " + value);
                                }
                                return "haha" + value;
                            })
                            .onErrorResume(error -> {
                                return Mono.just("haha error"); // 替代值
                            });
                })
//                .onErrorComplete()//流錯誤時，把錯誤吃掉，轉為正常訊號
//                .onErrorContinue((error, value) -> { // 跳過錯誤的那筆
//                    System.err.println("Error occurred: " + error.getMessage());
//                    System.err.println("Continuing with default value: haha1");
//                })
//                .onErrorResume(error -> { // 替換錯誤那筆，並結束
//                    System.err.println("onErrorResume: " + error.getMessage());
//                    return Mono.just("11111"); // 替換為可識別的值
//                })
//                .onErrorReturn("9"); // 替代值
                ;


        flow.subscribe(new MySubscribe<String>());
    }


    public static void baseDemo() {
//        Flux.concat(Flux.just(1, 2, 3), Flux.just(7, 8, 9))
//                .subscribe(System.out::println);

        Flux.range(1, 7)
//                .log()
                .filter(i -> i > 3)
                .map(i -> {
//                    if(i == 6)
//                        throw new RuntimeException("123");
                    return "haha-" + i;
                })
                .subscribe(
                        v -> System.out.println("c: " + v),
                        throwable -> System.out.println("error: " + throwable),
                        () -> System.out.println("flow end")
                );
    }

    /**
     * 信號 正常/異常
     * SignalType:
     * SUBSCRIBE: 被訂閱
     * REQUEST: 被求了N個元素
     * CANCEL
     * ON_SUBSCRIBE
     * ON_NEXT
     * ON_ERROR
     * ON_COMPLETE
     * AFTER_TERMINATE 中斷以後
     * CURRENT_CONTEXT 當前上下文
     * ON_CONTEXT 感知上下文
     * <p>
     * doOnNext 每個元素(流的數據)到達後觸發
     * doOnEach 每個元素(流的數據和信號) 到達後觸發
     * doOnRequest 校費者請求流元素
     * doOnError 錯誤時
     * doOnSubscribe 流被訂閱時
     * doOnTerminate 發送取消/異常訊號中斷了流
     * doOnCancel 流被取消
     * doOnDiscard: 流中元素被忽略時
     */
    public static void doOnMethod() {

        //關鍵: doOnNext表示流中某個元素到達後觸發
        // doOnXxxx要感知某個流的事件，要寫在這個流的後面，新流的前面
        Flux.just(1, 2, 3, 4, 5, 6, 7, 0, 11, 23)
                .doOnNext(integer -> System.out.println("1 ele: " + integer)) //放前面，不會跑map
                .doOnEach(integerSignal -> System.out.println("doOnEach.." + integerSignal)) //each 封裝的詳細
                .map(integer -> 10 / integer)
                .doOnError(throwable -> System.out.println("keep error " + throwable))
                .doOnNext(integer -> System.out.println("ele: " + integer))//後面會跑map
                .subscribe(System.out::println);
    }

    public static void flaxDoOn() throws IOException {
        //空流
        Flux<Object> empty = Flux.empty()
                .doOnComplete(() -> {
                    System.out.println("流結束了");
                });
        //要有訂閱，流才有開始結束
        empty.subscribe(System.out::println);

        // 事件感知AI: 當流發生什麼事的時候，觸發一個回調，系統調用提前定義好的鉤子函數(Hook 鉤子函數): doOnxxxxx
        Flux<Integer> flow = Flux.range(1, 7);
        flow.delayElements(Duration.ofSeconds(1))
                .doOnComplete(() -> {
                    System.out.println("flow 流結束了");
                }).doOnCancel(() -> {
                    System.out.println("flow 取消了");
                }).doOnError(throwable -> {
                    System.out.println("流出錯了: " + throwable);
                }).doOnNext(integer -> System.out.println("next: " + integer))
        ;
        flow.subscribe(new MySubscribe<Integer>());

        System.in.read();
    }

    public static void monoDemo() {
        Mono<Integer> just = Mono.just(1);
        just.subscribe(System.out::println);
    }

    public static void fluxDemo() throws IOException {
        // Mono 0|1個元素
        // Flux N個元素

        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5);
        //流不消費就沒用
        just.subscribe(e -> System.out.println("e=" + e));
        //一個數據流可以有很多消費者
        just.subscribe(e -> System.out.println("e1=" + e));

        //對於每個消費者來說，都是相同的: 廣播模式

        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1)); //每秒產生一個從0開始的數字
        flux.subscribe(System.out::println);

        System.in.read(); //控制台輸入，中斷點
    }
}
