package com.dingmouren.rxjavademo.条件和布尔操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * amb操作符只发射第一个Observable发射的数据，其他的忽略
 */

public class AmbDemo {
    public static void main(String[] args){
        Observable<Integer> ob1 = Observable.just(1,2,3);
        Observable<Integer> ob2 = Observable.just(4,5,6);
        Observable<Integer> ob3 = Observable.just(7,8,9);
        Observable.amb(ob2,ob1,ob3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("amd(ob1,ob2) onNext:" + integer);
            }
        });
    }
}
