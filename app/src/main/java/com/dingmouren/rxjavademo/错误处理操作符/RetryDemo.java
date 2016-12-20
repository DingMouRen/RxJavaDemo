package com.dingmouren.rxjavademo.错误处理操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by dingmouren on 2016/12/20.
 * retry操作符是当Observable发生异常时，重新尝试执行Observable的逻辑，不指定次数就会一直重新运行，由于重新订阅，可能会造成数据项重复。
 */

public class RetryDemo {
    public static void main(String[] args){
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    //循环输出数字，在输出2时抛出异常
                    for (int i = 0; i < 4; i++) {
                        if (2 == i){
                            throw new Exception("当前number等于2，error");
                        }
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        observable.retry(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("retry onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("retry onError:" +  e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("retry onNext:" + integer);
            }
        });

    }
}
