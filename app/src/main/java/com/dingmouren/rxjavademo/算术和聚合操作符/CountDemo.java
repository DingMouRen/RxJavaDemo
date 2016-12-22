package com.dingmouren.rxjavademo.算术和聚合操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * count操作符计算源Observable发射数据项的数量，将这个数量提交给订阅者
 */

public class CountDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).count().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("count() + onNext:" + integer);
            }
        });
    }
}
