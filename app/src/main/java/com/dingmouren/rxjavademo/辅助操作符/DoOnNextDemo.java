package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * doOnNext操作符回调中的参数就是Observable发射的数据项，一般用于保存或者缓存网络结果
 */

public class DoOnNextDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("doOnNext(Action1) call回调中的integer:" + integer +"  所在线程：" + Thread.currentThread().getName() );
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("doOnNext(Action1) onNext:" +  integer +"  所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
