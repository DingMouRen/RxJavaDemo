package com.dingmouren.rxjavademo.算术和聚合操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.MathObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * sum操作符将目标Observable发射的数据项相加成一个值，将这个值提交给订阅者
 * 变体：sumDouble()、sumFloat()、sumLong()
 */

public class SumDemo {
    public static void main(String[] args){
        MathObservable.sumInteger(Observable.just(1,1,1)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("MathObservable.sum(Observable) onNext:" + integer);
            }
        });
    }
}
