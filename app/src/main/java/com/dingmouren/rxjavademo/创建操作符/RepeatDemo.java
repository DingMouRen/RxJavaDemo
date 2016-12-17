package com.dingmouren.rxjavademo.创建操作符;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by dingmouren on 2016/12/17.
 *  repeat:创建一个发射特定数据重复多次的Observable,有多个构造函数
 *  默认在tranpoline调度器上运行，当其他排队的任务完成后，在当前线程排队开始执行，在这的当前线程是main,可以通过参数执行运行所在的线程
 */

public class RepeatDemo {
    public static void main(String[] args){
        Observable.just(1).repeat(5, Schedulers.trampoline()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:完成" );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext:" + integer +" 所在线程:"+ Thread.currentThread().getName());

            }
        });
    }
}
