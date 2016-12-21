package com.dingmouren.rxjavademo.条件和布尔操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/21.
 * cotains操作符传入一个指定数据项，如果源Observbale发射过这个数据项，操作符将给订阅者返回一个true，否则就返回一个false
 */

public class ContainsDemo{
    public static void main(String[] args){
        Observable.just(1,2,3).contains(2).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("contains(Object) onNext:" + aBoolean);
            }
        });
    }

}
