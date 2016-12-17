package com.dingmouren.rxjavademo.创建操作符;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dingmouren on 2016/12/17.
 *  这三个操作符生成的Observable行为非常特殊和受限。测试的时候很有用，有时候也用于结
 *   合其它的Observables，或者作为其它需要Observable的操作符的参数。
 */

public class Empty_Never_ThrowDemo {
    public static void main(String[] args){
        //创建一个不发射任何数据但是正常终止的Observable
        Observable.empty().subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:完成.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.toString());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext:" + o.toString()+" 所在线程："+ Thread.currentThread().getName());
            }
        });
        System.out.println("-------------");
        //创建一个不发射数据也不终止的Observable
        Observable.never().subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:完成.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.toString());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext:" + o.toString()+" 所在线程："+ Thread.currentThread().getName());
            }
        });
        System.out.println("-------------");
        //创建一个不发射数据以一个错误终止的Observable
        Observable.error(new Throwable()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:完成.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.toString());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext:" + o.toString()+" 所在线程："+ Thread.currentThread().getName());
            }
        });

    }
}
