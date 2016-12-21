package com.dingmouren.rxjavademo.条件和布尔操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/21.
 * all操作符判断源Observble发射的每一项数据，如果都满足条件的话就给订阅者返回一个true，如果有一个数据项不满足条件就给订阅者返回一个false
 */

public class AllDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).all(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 5;
            }
        }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                System.out.println("all(Func1) onCompleted:" );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("all(Func1) onError:" + e.toString());
            }

            @Override
            public void onNext(Boolean aBoolean) {
                System.out.println("all(Func1) onNext:" + aBoolean);
            }
        }).unsubscribe();
    }
}
