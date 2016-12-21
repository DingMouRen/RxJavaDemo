package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * foreach操作符是简单化的subscribe，不同就是不能取消订阅,无法使用unsubscribe方法，
 */

public class ForeachDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4).forEach(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("foreach onNext:" + integer);
            }
        });
    }
}
