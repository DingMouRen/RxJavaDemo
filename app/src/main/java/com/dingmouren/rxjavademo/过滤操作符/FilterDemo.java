package com.dingmouren.rxjavademo.过滤操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/19.
 * filter操作符对源Observable发射的数据项按照指定的条件进行过滤，满足的条件的才会调给订阅者。默认不在任何特定的调度器上执行
 */

public class FilterDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4,5).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 3;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("filter(Func1) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
