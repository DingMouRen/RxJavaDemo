package com.dingmouren.rxjavademo.异步操作符;

import rx.functions.Action1;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by dingmouren on 2016/12/22.
 * toAsync操作符是创建一个异步函数，获取一个值，通过call()返回一个发射这个值给后续订阅者的Observable对象，只会执行一次
 * toAsync, asyncAction, 和asyncFunc。它们接受一个函数或一个Action作为参数。对于函数(functions)，这个操作符调用这个函数获取一个值
 * ，然后返回一个会发射这个值给后续观察者的Observable（和start一样）。对于动作(Action)，过程类似，但是没有返回值，在这种情况下，这个操作符在终止前会发射一个null值。
 */

public class ToAsyncDemo {
    public static void main(String[] args){
        Async.toAsync(new Func0<Integer>() {
            @Override
            public Integer call() {
                System.out.println("toAsync(Func0) 所在线程：" + Thread.currentThread().getName());
                return 1;
            }
        }).call().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("toAsync(Func0) onNext:" + integer +"  所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
