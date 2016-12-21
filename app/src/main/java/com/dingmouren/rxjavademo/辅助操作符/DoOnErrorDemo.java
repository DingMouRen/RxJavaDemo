package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * doOnError操作符注册一个动作（回调函数），在Observable异常终止时会回调这个方法，在onError执行前执行。
 */

public class DoOnErrorDemo {
    public static void main(String[] args){
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                subscriber.onError(new Exception("有错误"));
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("doOnError(Action1) call执行:" + throwable.toString());

            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("doOnError(Action1) onCompleted:"  );

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("doOnError(Action1) onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("doOnError(Action1) onNext:" + integer);
            }
        }).unsubscribe();
    }
}
