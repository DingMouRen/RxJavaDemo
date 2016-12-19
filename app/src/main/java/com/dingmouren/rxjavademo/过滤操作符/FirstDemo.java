package com.dingmouren.rxjavademo.过滤操作符;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/19.
 * first()操作符提交源Observable发射的第一项数据，如果只是想要一个过滤符，最好使用take(2)或者elementAt(0)
 * first(Func1)操作符是提交第一项符合自定义条件的数据
 * firstOrDefault(T)操作符是在Observable没有发射任何数据时提交一个指定的默认值
 * takeFirst(Func1)操作符提交符合自定义条件的的第一项数据，
 * 与first（Func1）不同的是，takeFirst(Func1)在没有符合条件的时候，会调用onCompleted，而first(Func1)会抛一个NoSuchElementException的异常
 */

public class FirstDemo {

    public static void main(String[] args){

        Observable.just(1,2,3).first().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("first() onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });

        System.out.println("- - - - - - - -");
        Observable.just(1,2,3).first(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return 1 < integer;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("first(Func1) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });

        System.out.println("- - - - - - - -");
        Observable.just(1,2,3).takeFirst(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return 3 < integer;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("takeFirst(Func1) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
