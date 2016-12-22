package com.dingmouren.rxjavademo.异步操作符;

import rx.functions.Action1;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by dingmouren on 2016/12/22.
 * start操作符在异步线程创建一个Observable对象，它发射的数据是call函数的返回值
 */

public class StartDemo {
    public static void main(String[] args){
        Async.start(new Func0<Integer>() {

            @Override
            public Integer call() {
                //这里是异步线程
                System.out.println("start(Func0) 所在线程：" + Thread.currentThread().getName());
                return 1;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("start(Func0) onNext:" + integer + " 所在线程："+Thread.currentThread().getName());
            }
        });
    }
}
