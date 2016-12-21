package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.TimeInterval;

/**
 * Created by dingmouren on 2016/12/21.
 * timeInterval操作符会拦截Observable发射的数据项，并发射两个数据项之间的时间
 * 默认在immediate上执行，可以指定执行线程timeInterval(Scheduler)
 */

public class TimeIntervalDemo {
    public static void main(String[] args){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    for (int i = 0; i < 8; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(i * 100);
                    }
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        }).timeInterval().subscribe(new Action1<TimeInterval<Integer>>() {
            @Override
            public void call(TimeInterval<Integer> integerTimeInterval) {
                System.out.println("timeInterval() onNext:" + "value=" + integerTimeInterval.getValue()+" △time="+integerTimeInterval.getIntervalInMilliseconds()+"ms");
            }
        }).unsubscribe();
    }
}
