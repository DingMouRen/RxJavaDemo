package com.dingmouren.rxjavademo.错误处理操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by dingmouren on 2016/12/20.
 * retryWhen操作符类似于retry操作符，都是在源observable出现错误或者异常时，重新尝试执行源observable的逻辑，不同在于retryWhen操作符是在源Observable出现错误或者异常时，
 * 通过回调第二个Observable来判断是否重新尝试执行源Observable的逻辑，如果第二个Observable没有错误或者异常出现，则就会重新尝试执行源Observable的逻辑，
 * 否则就会直接回调执行订阅者的onError方法,可以指定执行线程 retryWhen(Func1,Scheduler)
 */

public class RetryWhenDemo {
    public static void main(String[] args){
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("subscribing");
                subscriber.onError(new RuntimeException("always fails"));
            }
        });

        observable.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.zipWith(Observable.just(1, 2), new Func2<Throwable, Integer, Integer>() {

                    @Override
                    public Integer call(Throwable throwable, Integer integer) {
                        System.out.println("retryWhen integer:" + integer);
                        return integer;
                    }
                });
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("retryWhen onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                System.out.println("retryWhen onError:" +e.toString());
            }
            @Override
            public void onNext(Integer integer) {
                System.out.println("retryWhen onNext:" + integer);
            }
        }) ;
    }
}
