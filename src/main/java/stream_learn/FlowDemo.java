package stream_learn;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemo {

    static class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("processor bind done");
            this.subscription = subscription;
            this.subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            System.out.println("processor get item "+ item);
            //再加工
            item = item + " haha";
            submit(item);
            this.subscription.request(1); //持續要下一個
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
    }
    /**
     * 1 publisher 發布者
     * 2 subscriber 訂閱者
     * 3 subscription 訂閱關係
     * 4 process 處理器
     */
    public static void main(String[] args) throws InterruptedException {

        //jvm底層對於整個發布訂閱關係做好了 異步 + 緩存
        //定義發布者
        SubmissionPublisher<String> publisher1 = new SubmissionPublisher();

        //定義一個process
        MyProcessor myProcessor = new MyProcessor();
        MyProcessor myProcessor1 = new MyProcessor();
        MyProcessor myProcessor2 = new MyProcessor();

        //定義一個訂閱者
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override //在訂閱時呼叫
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println(Thread.currentThread() + " start to subscription " + subscription);
                this.subscription = subscription;
                //從上游請求 n 個數據，會直接跑n次 on next
                subscription.request(2);
            }

            @Override
            public void onNext(String item) {
                System.out.println(Thread.currentThread() + " get msg " + item);
                //持續要下一個
                if (item.equals("p7")){
                    this.subscription.cancel();
                } else {
                    this.subscription.request(1);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(Thread.currentThread() + " error " + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread() + " done ");
            }
        };

        //綁定發布者和訂閱者 + 處理器
        //自訂連結處理器關係
        publisher1.subscribe(myProcessor);
        myProcessor.subscribe(myProcessor1);
        myProcessor1.subscribe(myProcessor2);
        myProcessor2.subscribe(subscriber);

        for(int i = 0; i < 10; i++) {
            publisher1.submit("p" + i); //數據會存在buffer區
        }
        Thread.sleep(2000);
    }

    /**
     * 1 publisher 發布者
     * 2 subscriber 訂閱者
     * 3 subscription 訂閱關係
     */
    public static void demo1() throws InterruptedException {

        //jvm底層對於整個發布訂閱關係做好了 異步 + 緩存
        //定義發布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher();

        //定義一個訂閱者
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override //在訂閱時呼叫
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println(Thread.currentThread() + " start to subscription " + subscription);
                this.subscription = subscription;
                //從上游請求 n 個數據，會直接跑n次 on next
                subscription.request(2);
            }

            @Override
            public void onNext(String item) {
                System.out.println(Thread.currentThread() + " get msg " + item);
                //持續要下一個
                if (item.equals("p7")){
                    this.subscription.cancel();
                } else {
                    this.subscription.request(1);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(Thread.currentThread() + " error " + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread() + " done ");
            }
        };

        Flow.Subscriber<String> subscriber2 = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override //在訂閱時呼叫
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println(Thread.currentThread() + " 2start to subscription " + subscription);
                this.subscription = subscription;

                //從上游請求一個數據
                subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println(Thread.currentThread() + " 2get msg " + item);
//                持續要下一個
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(Thread.currentThread() + " 2error " + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread() + " 2done ");
            }
        };

        //綁定發布者和訂閱者 可以有多個訂閱者
        publisher.subscribe(subscriber);
        publisher.subscribe(subscriber2);

        for(int i = 0; i < 10; i++) {
            publisher.submit("p" + i); //數據會存在buffer區
        }
        Thread.sleep(2000);
    }
}
