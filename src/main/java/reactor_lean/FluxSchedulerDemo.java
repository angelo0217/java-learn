package reactor_lean;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FluxSchedulerDemo {
    public static void main(String[] args) {
//        流的每個操作都會產生新的流
        var flux = Flux.range(1, 10)
                .map(v -> "aa" + v)
                .log()
//                .publishOn(Schedulers.immediate())// 預設主線程
//                .publishOn(Schedulers.single())// 切換現程執行，單一線程
//                .publishOn(Schedulers.boundedElastic())//有界，彈性，不是無限擴充的線程，線程中有 10*cpu個核心線程，默認隊列 100K，keep alive 60s
                .publishOn(Schedulers.fromExecutor(
                        new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000))
                ))
                .map(v -> "bb" + v)
                .log();
        //只要不指定線程，發布者默認使用訂閱者線程
        new Thread(()-> flux.subscribe()).start();
    }
}
    