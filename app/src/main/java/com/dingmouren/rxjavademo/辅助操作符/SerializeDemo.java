package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by dingmouren on 2016/12/21.
 * serialize操作符将不符合约定的Observable的过滤掉
 */

public class SerializeDemo {
    public static void main(String[] args){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onCompleted();
                subscriber.onNext(3);
                subscriber.onCompleted();
            }
        }).serialize().doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("serialize() onUnsubscribe.");
                    }
                })
                .unsafeSubscribe(new Subscriber<Integer>() {//不会自动取消订阅者的方法
                    @Override
                    public void onCompleted() {
                        System.out.println("serialize() onCompleted.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("serialize() onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("serialize() onNext: " + integer);
                    }
                });
    }
}
