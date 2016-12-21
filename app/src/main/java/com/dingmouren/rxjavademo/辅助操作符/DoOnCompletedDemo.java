package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * doOnCompleted操作符注册了一个动作，也就是一个回调函数，当Observable正常完成时会执行这个回调函数，在onCompelted执行之前。
 */

public class DoOnCompletedDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4).doOnCompleted(new Action0() {
            @Override
            public void call() {
                System.out.println("doOnCompleted(Action0) call执行了");
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("doOnCompleted(Action0) onCompleted:"  );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("doOnCompleted(Action0) onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("doOnCompleted(Action0) onNext:" + integer);
            }
        }).unsubscribe();
    }
}
