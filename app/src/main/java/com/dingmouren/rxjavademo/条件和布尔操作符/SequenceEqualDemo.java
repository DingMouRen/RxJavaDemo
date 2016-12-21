package com.dingmouren.rxjavademo.条件和布尔操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * sequenceEqual操作符比较两个Observable发射的数据项，如果相同的数据，相同的顺序，相同的终止状态，操作符就给订阅者返回true，否则返回false，
 * 另外还可以使用sequenceEqual(ob1,ob2,Func2)比较两个数据项是否相同
 */

public class SequenceEqualDemo {
    public static void main(String[] args){
        Observable<Integer> ob1 = Observable.just(1,2,3);
        Observable<Integer> ob2 = Observable.just(1,2,3);
        Observable.sequenceEqual(ob1,ob2).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("sequenceEqual(ob1,ob2) onNext:" + aBoolean);
            }
        });
    }
}
