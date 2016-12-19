package com.dingmouren.rxjavademo.过滤操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/19.
 * elementAt(index);将指定索引的数据项提交给订阅者，索引是从0开始，当没有这个索引或者索引为负数会抛异常。
 * elementAtOrDefault(index,default)：这个会设置一个默认值，当没有指定的索引就提交默认值给订阅者，为负数就抛异常。
 */

public class ElementAtDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4).elementAt(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("elementAt onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
        System.out.println("- - - - - - - - - - ");
        Observable.just(1,2,3,4).elementAtOrDefault(6,6).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("elementAtOrDefault onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
