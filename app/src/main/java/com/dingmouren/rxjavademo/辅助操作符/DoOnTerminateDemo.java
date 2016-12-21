package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 * doOnTerminate操作符注册了一个动作，当Observable终止之前，不管是正常终止和爱是异常终止都会在之前执行回调函数
 */

public class DoOnTerminateDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4).doOnTerminate(new Action0() {
            @Override
            public void call() {
                System.out.println("doOnTerminate(Action0) call执行了:"  );
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("doOnTerminate(Action0) onCompleted:"  );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("doOnTerminate(Action0) onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("doOnTerminate(Action0) onNext:" + integer);
            }
        }).unsubscribe();
    }
}
