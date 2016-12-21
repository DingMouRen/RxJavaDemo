package com.dingmouren.rxjavademo.辅助操作符;

import rx.Notification;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * materialize操作符不仅发射数据项，还将onNext onCompleted onError作为数据项进行发射，中间会通过一个中间类Notification来存储,由它发射数据
 * dematerialize操作符正好相反，将materialize转换的结果还原成原来的样子
 */

public class MaterializeDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4).materialize().subscribe(new Action1<Notification<Integer>>() {
            @Override
            public void call(Notification<Integer> integerNotification) {
                System.out.println("materialize() onNext : integer " + integerNotification.getValue());
                System.out.println("materialize() onNext : kind " + integerNotification.getKind());
                System.out.println("materialize() onNext : 状态 onNext onCompleted onError " + integerNotification.isOnNext()+"-"+integerNotification.isOnCompleted()+"-"+integerNotification.isOnError());


            }
        }).unsubscribe();
    }
}
