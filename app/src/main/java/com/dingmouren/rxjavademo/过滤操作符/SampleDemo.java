package com.dingmouren.rxjavademo.过滤操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by dingmouren on 2016/12/19.
 * sample操作符对Observable发射的数据定时进行采样，采的是自从上一次采样以来，Observable最近发射的一项数据，也就是这段时间间隔中最后一个数据项。
 * 如果自上一次采样以来，源Observable没有发射任何数据，sample操作符返回的Observable在此段时间也不会发射任何数据
 * 默认在computation调度器上执行，但是可以指定它执行的线程 sample(long,TimeUnit,Scheduler)
 * 与之对应的操作符是throttleFirst,它采样的是采样时间间隔中第一项数据，在最后一个时间段会发射最后一个数据项，看下面例子
 * △time = 2.2s
 * Data :    1------2------3------4------5------6------7
 * Time(s) : 0------1------2------3------4------5------6------7................
 * △time点: 0------1------2-。----3------4-。---5------6----。-7................
 * SampleResults:  3,5,7
 * ThrottleFirst:  1,4,7
 */

public class SampleDemo {
    public static void main(String[] args){
        //smaple
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;//如果没有订阅者直接返回
                try {
                    //前3个数字的时间间隔设置1秒，最后一个设置2秒
                    for (int i = 1; i <8 ; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(1000);
                    }
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                   subscriber.onError(e);
                }
            }
        }).sample(2200, TimeUnit.MILLISECONDS)//采样间隔时间为2200毫秒
                .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("sample  onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
        System.out.println("- - - - - - - - - - ");
        //throttleFirst
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    for (int i = 1; i < 9 ; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(1000);
                    }
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        }).throttleFirst(2200,TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("throttleFirst  onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
                    }
                });
    }
}
