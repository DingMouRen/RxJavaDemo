package com.dingmouren.rxjavademo.条件和布尔操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/21.
 * skipWhile操作符会丢弃源Observable发射的数据项，直到一个指定的条件不成立。
 */

public class SkipWhileDemo {
    public static void main(String[] args){
        Observable<Integer> ob1 = Observable.just(1,2,3);
        Observable<Integer> ob2 = Observable.just(4,5,6).delay(10,TimeUnit.SECONDS);
        Observable.just(1,2,3).skipWhile(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("skipWhile(Func1) onNext:" + integer);
            }
        });

    }
}
