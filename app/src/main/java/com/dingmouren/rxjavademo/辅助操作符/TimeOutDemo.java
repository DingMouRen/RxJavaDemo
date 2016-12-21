package com.dingmouren.rxjavademo.辅助操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dingmouren on 2016/12/21.
 * timeout操作符是Observable在指定的时间没有发射数据，会抛出TimeOutException的异常，终止Observable.
 * timeout(long,TimeUnit,Observable)在执行时间内Observable没有发射数据的话，就使用备用的Observable,可以指定运行的线程
 */

public class TimeOutDemo {
    public static void main(String[] args){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        }).timeout(2, TimeUnit.SECONDS).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("timeout(long,TimeUnit) onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("timeout(long,TimeUnit) onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("timeout(long,TimeUnit) onNext:" + integer);
            }
        }).unsubscribe();
        System.out.println("- - - - - - - - - - - - - -");
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        }).timeout(2, TimeUnit.SECONDS,Observable.just(1,2,3)).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("timeout(long,TimeUnit,Observable) onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("timeout(long,TimeUnit,Observable) onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("timeout(long,TimeUnit,Observable) onNext:" + integer);
            }
        }).unsubscribe();

    }
}
