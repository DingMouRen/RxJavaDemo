package com.dingmouren.rxjavademo.算术和聚合操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by dingmouren on 2016/12/22.
 * reduce操作符按照顺序对源Observable发射的每项数据应用一个函数并发射这个函数计算后的值。场景中有时呗乘坐累积，聚集等
 */

public class ReduceDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).reduce(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer1, Integer integer2) {
                System.out.println("integer1:"+ integer1 + " integer2:" + integer2 );
                return integer1 + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("reduce(Func2) onNext:" + integer);
            }
        });
    }
}
