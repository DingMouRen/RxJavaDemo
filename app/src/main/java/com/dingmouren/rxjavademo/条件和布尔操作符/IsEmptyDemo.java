package com.dingmouren.rxjavademo.条件和布尔操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * isEmpty操作符判断源Observable是否发射过数据，如果没有发射过数据，操作符就给订阅者返回一个true,否则就返回false
 */

public class IsEmptyDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).isEmpty().subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("isEmpty（） onNext:" + aBoolean );
            }
        });
    }
}
