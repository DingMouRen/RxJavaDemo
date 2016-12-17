package com.dingmouren.rxjavademo.创建操作符;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dingmouren on 2016/12/17.
 *
 *  Create 从头开始创建一个Observable，
 *  建议：一般在观察者在订阅的状态下进行运算，没有订阅的情况下不发射数据或者做一些昂贵的运算:
 *  create()方法默认不在任何特定的调度器上执行
 */

public class CreateDemo {

    static String name;

    public static void main(String[] args){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                name.getBytes();//这行代码用来测试onError的情况，测试onCompelted onNext,将这句代码隐藏掉
                try {
                    if (!observer.isUnsubscribed()){
                        for (int i = 0; i < 6; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:完成.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext:" + integer+" 所在线程："+ Thread.currentThread().getName());
            }
        });
    }
}
