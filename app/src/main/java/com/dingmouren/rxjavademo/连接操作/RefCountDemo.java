package com.dingmouren.rxjavademo.连接操作;

import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * refCount操作符将ConnectableObservable对象转换成普通的Observable对象
 */

public class RefCountDemo {
    public static void main(String[] args){
        Observable<Integer> observable = Observable.just(1,2,3).publish().refCount();
        Subscriber<Integer> subscriber1 = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("publish subscriber1--onCompleted:" );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("publish subscriber1--onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("publish subscriber1--onNext:" + integer);
            }
        };
        Subscriber<Integer> subscriber2 = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("publish subscriber2--onCompleted:" );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("publish subscriber2--onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("publish subscriber2--onNext:" + integer);
            }
        };
        System.out.println("绑定订阅者");
        observable.subscribe(subscriber1);
        observable.subscribe(subscriber2);

    }
}
