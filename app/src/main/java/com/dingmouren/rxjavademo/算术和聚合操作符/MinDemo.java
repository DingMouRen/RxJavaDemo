package com.dingmouren.rxjavademo.算术和聚合操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.MathObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * min操作符是MathObservable中的方法，取目标Observable发射的数据项中最小的数据提交给订阅者
 */

public class MinDemo {
    public static void main(String[] args){
        MathObservable.min(Observable.just(1,2,3,4)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("MathObservable.min(Observable) onNext:" + integer);
            }
        });
    }
}
