package com.dingmouren.rxjavademo.辅助操作符;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/21.
 * using操作符在Observable生命周期内创建一次性使用的资源，在Observable终止后这个资源会被释放掉
 * 属于一种特殊的创建操作符，接收三个参数
 * 1.创建一次性资源的工厂函数
 * 2.创建Observable的工厂函数
 * 3.用于释放资源的函数（Observable终止后执行这个函数）
 */

public class UsingDemo {
    public static void main(String[] args){
        Observable.using(new Func0<List<Integer>>() {
            @Override
            public List<Integer> call() {
                List<Integer> list = new ArrayList<Integer>();
                list.add(1);
                list.add(2);
                list.add(3);
                return list;
            }
        }, new Func1<List<Integer>, Observable<?>>() {
            @Override
            public Observable<List<Integer>> call(List<Integer> integers) {
                return Observable.just(integers);
            }
        }, new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("using Action1;" + integers.toString());
                    integers.clear();

            }
        }).subscribe(new Subscriber<Object>() {

            @Override
            public void onCompleted() {
                System.out.println("using onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("using onError:" + e.toString());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("using onNext:" + o.toString());
            }
        });
    }
}
