package com.dingmouren.rxjavademo.过滤操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/19.
 * ofType操作符类似于filter操作符，区别在于ofType按照数据项的类型进行过滤，默认不在任何特定的调度器上执行
 */

public class OfTypeDemo {
    public static void main(String[] args){
        Observable.just(1,"String类型",true).ofType(String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("ofType(class) onNext:" + s + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
