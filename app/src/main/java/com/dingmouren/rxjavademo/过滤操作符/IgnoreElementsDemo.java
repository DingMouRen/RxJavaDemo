package com.dingmouren.rxjavademo.过滤操作符;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dingmouren on 2016/12/19.
 * ignoreElements()操作符不提交任何数据给订阅者，只提交终止通知（onError或者onCompeleted）给订阅者，默认不在任何特定的调度器上执行
 */

public class IgnoreElementsDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).ignoreElements().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("ignoreElements() onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
