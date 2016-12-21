package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by dingmouren on 2016/12/21.
 * finallyDo操作符注册了一个动作，Observable完成之后，不管是异常终止还是正常完成都会执行这个回调函数
 */

public class FinallyDoDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4).finallyDo(new Action0() {
            @Override
            public void call() {
                System.out.println("finallyDo(Action0) call执行了:"  );
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("finallyDo(Action0) onCompleted:"  );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("finallyDo(Action0) onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("finallyDo(Action0) onNext:" + integer);
            }
        }).unsubscribe();
    }
}
