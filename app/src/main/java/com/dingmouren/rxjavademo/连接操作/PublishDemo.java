package com.dingmouren.rxjavademo.连接操作;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.ActionSubscriber;
import rx.observables.ConnectableObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * publish操作符将一个普通的Observable转换成一个ConnectableObservable对象，ConnectableObservable在被订阅时并不会立即
 * 发射数据，只有这个ConnectableObservable调用connect()时才开始发射数据。可以绑定很多订阅者，在需要发射数据的时候，再调用connect()来发射数据。
 */

public class PublishDemo {
    public static void main(String[] args){
        ConnectableObservable<Integer> observable = Observable.just(1,2,3).publish();
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
        System.out.println("Observable调用connect(）方法");
        observable.connect();

    }
}
