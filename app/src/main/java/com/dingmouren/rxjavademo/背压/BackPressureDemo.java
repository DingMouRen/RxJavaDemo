package com.dingmouren.rxjavademo.背压;



import rx.Observable;
import rx.Subscriber;


/**
 * Created by dingmouren on 2016/12/27.
 * 背压：在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，通知被观察者降低发送速度的策略。
 * 响应式拉取：观察者主动从被观察者那里拉取数据，而被观察者被动的等待通知再发射数据。
 */

public class BackPressureDemo {
    public static void main(String[] args){
        Observable observable = Observable.range(1,10);
        observable.subscribe(new MySubscriber());
    }
    static class MySubscriber extends Subscriber {
        @Override
        public void onStart() {
            request(1);//在OnStrart中通知观察者先发送一个事件
        }

        @Override
        public void onCompleted() {
            System.out.println("BackPressure onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("BackPressure onError");
        }

        @Override
        public void onNext(Object o) {
            try {
                Thread.sleep(1000);
                System.out.println(o.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //处理完毕之后，再通知被观察者发送下一个事件
            request(1);
        }
    }
}
