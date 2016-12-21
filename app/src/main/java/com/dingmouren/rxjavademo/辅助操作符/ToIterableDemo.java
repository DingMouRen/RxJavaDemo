package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;

/**
 * Created by dingmouren on 2016/12/21.
 * toIterable操作符只能作用于BlockingObservable，可以将BlockingObservable转换成Iterable对象
 */

public class ToIterableDemo {

    public static void main(String[] args){
        Iterable<Integer> integers = Observable.just(1, 2, 3).toBlocking().toIterable();
    }
}
