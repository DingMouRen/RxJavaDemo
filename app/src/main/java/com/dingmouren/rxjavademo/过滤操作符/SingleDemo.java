package com.dingmouren.rxjavademo.过滤操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/19.
 * single()操作符：在源Observable只发射一个数据项的时候，single()操作符会将这个数据提交给订阅者，大于1个就抛Sequence contains too many elements的异常，不是正好是1个数据项就会抛异常
 * single(Func1)操作符是对源Observable发射的数据项进行判断，如果符合条件的数据数量大于1就会抛异常，不是正好是1个数据项就会抛异常
 * 也有设置默认值得api，默认不在任何特定的调度器上执行
 */

public class SingleDemo{
    public static void main(String[] args){
        Observable.just(1,2,3).single().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted"  );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("single() onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
        System.out.println("- - - - - - - - ");
        Observable.just(1,2,3).single(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return 1 < integer;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted"  );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("single(Func1) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
