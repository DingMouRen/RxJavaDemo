package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * nest操作符可以将源Observable改变成一个发射Observable的Observbale,下面这个例子相当于把自己发射出去了
 */

public class NestDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).nest().subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(Observable<Integer> observable) {
                System.out.println("nest onNext:" + observable.toString());
            }
        });
    }
}
