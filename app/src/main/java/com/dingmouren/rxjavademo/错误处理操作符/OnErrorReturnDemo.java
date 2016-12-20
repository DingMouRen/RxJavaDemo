package com.dingmouren.rxjavademo.错误处理操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/20.
 * onErrorReturn操作符实在Observable发生错误时候（即将回调onError方法时），拦截错误通知并执行指定的逻辑，
 * 返回一个跟源Observable相同类型的结果，最后回调订阅者的onCompleted方法。
 */

public class OnErrorReturnDemo {
    public static void main(String[] args){
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
            if (subscriber.isUnsubscribed()) return;
                try {
                    //循环输出数字，在输出2时抛出异常
                    for (int i = 0; i < 4; i++) {
                        if (2 == i){
                            throw new Exception("当前number等于2，error");
                        }
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        observable.onErrorReturn(new Func1<Throwable, Integer>() {
            @Override
            public Integer call(Throwable throwable) {
                return 100;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onErrorReturn onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onErrorReturn onError:" +  e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onErrorReturn onNext:" + integer);
            }
        });
    }
}
