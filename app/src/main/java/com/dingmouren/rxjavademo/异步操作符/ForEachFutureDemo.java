package com.dingmouren.rxjavademo.异步操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.util.async.Async;

/**
 * Created by dingmouren on 2016/12/22.
 * forEachFuture操作符传递Subscriber方法给一个Subscriber，但是同时表现的想一个Future一样阻塞到直到完成。
 * forEachFuture操作符自己返回一个Future并且在get方法处阻塞，直到源Observable执行完成。
 */

public class ForEachFutureDemo {
    public static void main(String[] args){
        Async.forEachFuture(Observable.just(1, 2, 3), new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("forEachFuture(Observable,Action1)  integer:" + integer +" 所在线程："+ Thread.currentThread().getName());
            }
        });
    }
}
