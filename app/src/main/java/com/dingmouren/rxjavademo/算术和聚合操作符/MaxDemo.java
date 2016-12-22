package com.dingmouren.rxjavademo.算术和聚合操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.MathObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * max操作符是MathObservable对象中的操作符，取目标Observable发射的数据中最大的数据项提交给订阅者，
 */

public class MaxDemo {
    public static void main(String[] args){
       MathObservable.max(Observable.just(1,2,3,4)).subscribe(new Action1<Integer>() {
           @Override
           public void call(Integer integer) {
               System.out.println("MathObservable.max(Observable) onNext:" + integer );
           }
       });
    }
}
