package com.dingmouren.rxjavademo.异步操作符;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.util.async.Async;

/**
 * Created by dingmouren on 2016/12/22.
 * fromAction(Action0,Object)操作符讲一个Action动作转换成Observable,当一个订阅者订阅时，Observable执行这个action并发射它的返回值
 */

public class FromActionDemo {
    public static void main(String[] args){
        Async.fromAction(new Action0() {
            @Override
            public void call() {
                System.out.println("call函数所在线程：" + Thread.currentThread().getName());
                System.out.println("计算开始" );
                for (int i = 0; i <10 ; i++) {
                    System.out.println("计算进行中：" + i);
                }
                System.out.println("计算结束" );
            }
        },1).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("fromAction(Action0,Object) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        }).unsubscribe();
    }
}
