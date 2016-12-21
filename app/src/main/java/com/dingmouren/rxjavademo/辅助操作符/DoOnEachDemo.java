package com.dingmouren.rxjavademo.辅助操作符;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * doOnEach操作符注册了一个回调，doOnEach(Action1)中有一个Notification通知类作为参数，Observable每发射一项数据都会回调Action1，通知类中会记录
 * 发射的数据值，onNext onCompelted onError通知，Notification中的内部类Kind就是通知的种类
 */

public class DoOnEachDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4).doOnEach(new Action1<Notification<? super Integer>>() {
            @Override
            public void call(Notification<? super Integer> notification) {
                System.out.println("doOnEach(Actiion1) call所在线程：" + Thread.currentThread().getName());
                System.out.println("doOnEach(Actiion1) notification的value:"+notification.getValue());
                System.out.println("doOnEach(Actiion1) notification的Kind:"+notification.getKind());
                System.out.println("doOnEach(Actiion1) notification的Throwable:"+notification.getThrowable());
                System.out.println("doOnEach(Actiion1) notification的next error compelted状态:"+notification.isOnNext()+"-"+notification.isOnError()+"-"+notification.isOnCompleted());
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("doOnEach(Actiion1) onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("doOnEach(Actiion1) onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("doOnEach(Actiion1) onNext:" + integer + "  所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
