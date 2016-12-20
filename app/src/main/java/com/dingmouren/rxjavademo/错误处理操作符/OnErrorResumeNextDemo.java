package com.dingmouren.rxjavademo.错误处理操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/20.
 * onErrorResumeNext操作符类似于onErrorReturn操作符，onErrorReturn只能在发生错误时返回一个跟源Observable相同类型的结果，
 * onErrorResumeNext在发生错误(error)时会返回一个Observable,可以返回多个与源Observable发射类型形同的结果。
 */

public class OnErrorResumeNextDemo {
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

        observable.onErrorResumeNext(new Func1<Throwable, Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call(Throwable throwable) {
                return Observable.just(100,100,100);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onErrorResumeNext onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onErrorResumeNext onError:" +  e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onErrorResumeNext onNext:" + integer);
            }
        });
    }
}
