package com.dingmouren.rxjavademo.字符串操作;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.StringObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * join操作符将源Observable发射的单个字符串用指定的字符串连接起来
 */

public class JoinDemo {
    public static void main(String[] args){
        StringObservable.join(Observable.just("苹果","香蕉","栗子"),"-").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("join(Observable,String) onNext:" + s);
            }
        });
    }
}
