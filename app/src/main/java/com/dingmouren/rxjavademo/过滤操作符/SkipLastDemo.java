package com.dingmouren.rxjavademo.过滤操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by dingmouren on 2016/12/19.
 * skipLast操作符主要有两个变体，skipLast(count)和skipLast(long,TimeUnit),
 * skipLast(count) 对于源Observable发射的数据项，省略最后count项，将前面的数据项提交给订阅者
 * skipLast(long,TimeUnit)对于原Observalbe发射的数据项，省略最后long时间段的数据项，将之前的数据提交给订阅者，可以指定执行线程
 */

public class SkipLastDemo {
    public static void main(String[] args){
        //skipLast(count)
        Observable.just(1,2,3,4,5,6).skipLast(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("skipLast(count) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
        System.out.println("- - - - - - - - - ");
        //skipLast(long,TimeUnit)
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
        }).skipLast(2, TimeUnit.SECONDS, Schedulers.newThread()).subscribe(new Action1<Integer>() {//发射2秒之后的数据项
            @Override
            public void call(Integer integer) {
                System.out.println("skipLast(long,TimeUnit) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());

            }
        });
    }
}
