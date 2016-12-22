package com.dingmouren.rxjavademo.算术和聚合操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.MathObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * averageInteger操作符位于rxjava-math模块，使用MathObservable对象，这个操作符是计算所有Integer类型数据的平均值，注意Integer的取舍规则
 * 变体有averageDouble()、averageFloat()、averageLong()
 */

public class AverageIntegerDemo {
    public static void main(String[] args){
        MathObservable.averageInteger(Observable.just(3,6,3)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("MathObservable.averageInteger（Observable） onNext:" + integer);
            }
        });
    }
}
