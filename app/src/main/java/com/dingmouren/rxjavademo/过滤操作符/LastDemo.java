package com.dingmouren.rxjavademo.过滤操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/19.
 * last()操作符与first（）操作符相反，只提交最后一个数据项给订阅者，如果只是作为过滤操作符，最好使用takeLast(1),
 * 官方文档解释说：first()操作符和last()操作符在某些实现中会返回一个阻塞函数。
 * 与first()操作符系列对应，也有last(Func1)、lastOrDefault(T）、lastOrDefault(T,Func1)
 */

public class LastDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).last().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("last() onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
