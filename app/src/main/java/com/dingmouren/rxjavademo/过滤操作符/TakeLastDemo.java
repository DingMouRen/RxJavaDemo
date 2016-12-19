package com.dingmouren.rxjavademo.过滤操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/19.
 * takeLast(count)操作符对于源Observable发射的数据项，提取前面的count项数据提交给订阅者，忽略后面的
 * takeLast(long,TimeUnit)操作符对于源Obsrvable发射的数据项，提取前面long时间段里的数据项提交给订阅者，忽略后面的，可以指定线程
 */

public class TakeLastDemo {
    public static void main(String[] args){
        //takeLast(count)
        Observable.just(1,2,3).takeLast(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("takeLast(count) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());

            }
        });
        System.out.println("- - - - - - - - - - ");
        //takeLast(long,TimeUnit)
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    for (int i = 1; i < 5 ; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(1000);
                    }
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        }).takeLast(2, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("takeLast(long,TimeUnit) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
