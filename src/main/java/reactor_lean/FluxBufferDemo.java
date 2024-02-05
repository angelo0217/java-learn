package reactor_lean;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class FluxBufferDemo {

    public static void create() {
//        AtomicReference<MyListener> listener = null;
//
//        Flux.create(fluxSink -> {
//           listener.set(new MyListener(fluxSink));
//        });

        Flux.create(fluxSink -> {
            var listener = new MyListener(fluxSink);
            for (int i = 0; i < 100; i++) {
                listener.online("aa"+i);
            }
        }).log().subscribe();
    }

    static class MyListener {
        FluxSink<Object> sink;

        public MyListener(FluxSink<Object> sink) {
            this.sink = sink;
        }

        public void online(String userName) {
            this.sink.next("用戶登入 " + userName);
        }
    }
    public static void main(String[] args) {
        create();
    }


    public static void generate() throws IOException {
        var flux = Flux.generate(() -> 0, (state, sink) -> {
            if(state <= 10)
                sink.next(state);
            else
                sink.complete();

            if(state == 7)
                sink.error(new RuntimeException("7777"));
            return state + 1;
        }).delayElements(Duration.ofSeconds(1)).log();

        var disposable = flux.subscribe();


        new Thread(()->{
            try {
                Thread.sleep(3000);
                disposable.dispose();//中途取消流，會觸發cancel
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.in.read();
    }

    public static void limitRate() {
        Flux.range(1, 1000)
                .log()
//                .map() 不可改變型態
//                .handle((value, sink) -> {
//                    sink.next("aaa " + sink);
//                }) //可改變值得型態，使用sink 回傳，可結合 buffer 多合一或是 一變多
                .limitRate(100)// 第一次100個 如果超過 75% 已經處理，繼續抓新的 75%元素
                .subscribe();
    }

    public static void buffer() {
        var flux = Flux.range(1, 10)
                .buffer(3); //緩沖區 消費者一次最多拿到3個,讓消費端批量處理

//        flux.subscribe(v -> System.out.println(v.getClass() + " vale: " + v));
        flux.subscribe(new BaseSubscriber<List<Integer>>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("bind relation");
                request(2);
            }

            @Override
            protected void hookOnNext(List<Integer> value) {
                System.out.println("value: " + value);
                request(1);
            }
        });
    }
}
