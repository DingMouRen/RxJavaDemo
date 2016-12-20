package com.dingmouren.rxjavademo.错误处理操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/20.
 * onExceptionResumeNexr操作符是在发生异常时触发，如果throw不是exception，它就会将错误通知提交给订阅者，订阅者就会调用onError方法，不会使用别用的Observable
 */

public class OnExceptionResumeNextDemo {
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

        observable.onErrorResumeNext(Observable.just(100,100)).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onExceptionResumeNext onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onExceptionResumeNext onError:" +  e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onExceptionResumeNext onNext:" + integer);
            }
        });
    }
}
