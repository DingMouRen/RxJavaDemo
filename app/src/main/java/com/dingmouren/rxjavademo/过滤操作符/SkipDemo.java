package com.dingmouren.rxjavademo.过滤操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by dingmouren on 2016/12/19.
 * skip操作符主要有两个变体，skip(count)和skip(long,TimeUnit),
 * skip(count) 对于源Observable发射的数据项，跳过前count项，将后面的数据项提交给订阅者
 * skip(long,TimeUnit)对于原Observalbe发射的数据项，跳过long前的数据项，将之后的数据提交给订阅者，可以指定执行线程
 */

public class SkipDemo {
    public static void main(String[] args){
        //skip(count)
        Observable.just(1,2,3,4,5,6).skip(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("skip(count) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
        System.out.println("- - - - - - - - - ");
        //skip(long,TimeUnit)
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    //每隔一秒发射一项数据
                    for (int i = 1; i < 5 ; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(1000);
                    }
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        }).skip(2, TimeUnit.SECONDS, Schedulers.newThread()).subscribe(new Action1<Integer>() {//发射2秒之后的数据项
            @Override
            public void call(Integer integer) {
                System.out.println("skip(long,TimeUnit) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
