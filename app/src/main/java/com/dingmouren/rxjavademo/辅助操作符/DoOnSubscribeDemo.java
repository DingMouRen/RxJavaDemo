package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * doOnSubscirbe操作符与doOnUnsubscribe操作符对立存在，都有一个call的回调方法,doOnSubscrbe操作符是在绑定订阅者的时候，也就是发射
 * 数据之前执行，doOnUnsubscribe操作符是在解除订阅的时候执行call的回调方法。
 */

public class DoOnSubscribeDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                System.out.println("call执行了");
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("doOnSubscribe(Action0) onNext:" + integer);
            }
        }).unsubscribe();
        System.out.println("- - - - - - - - - - -");
        Observable<Integer> observable = Observable.just(1,2,3,4).doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                System.out.println("call执行了");
            }
        });
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("doOnUnsubscribe(Action0) onNext:" + integer);
            }
        }).unsubscribe();

    }
}
