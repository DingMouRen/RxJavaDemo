package com.dingmouren.rxjavademo.过滤操作符;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/19.
 * takeLastBuffer(count)操作符与takeLast(count)操作符类似，唯一不同就是takeLastBuffer(count)将最后的那些数据项收集到一个list集合中，提交这个集合给订阅者
 * takeLastBuffer(long,TimeUnit)操作符与takeLast(long,TimeUnit)操作符类似，唯一不同就是将在最后时间段Long中数据项收集到一个list集合中，
 * 将这个集合提交给了订阅者，可以指定运行的线程。
 */

public class TakeLastBufferDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4,5).takeLastBuffer(3).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("takeLastBuffer(count) onNext:" + integers.toString() + " 所在线程：" + Thread.currentThread().getName());
            }
        });
        System.out.println("- - - - - - - - - -");
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
        }).takeLastBuffer(3, TimeUnit.SECONDS).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("takeLastBuffer(long,TimeUnit) onNext:" + integers.toString() + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
