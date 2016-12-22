package com.dingmouren.rxjavademo.异步操作符;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by dingmouren on 2016/12/22.
 * deferFuture操作符将一个返回Observaable的Future转换成一个Observable，但是并不尝试获取这个Future返回的Observable，直到有订阅者订阅它。
 * 传递给它一个返回Future的函数（这个Future返回一个Observable），deferFuture返回一个Observable，但是不会调用你提供的函数，
 * 直到有观察者订阅它返回的Observable。这时，它立即调用Future的get()方法，然后镜像发射get()方法返回的Observable发射的数据
 */

public class DeferFutureDemo {
    public static void main(String[] args){
        Async.deferFuture(new Func0<Future<Observable<Integer>>>() {
            @Override
            public Future<Observable<Integer>> call() {
                return new Future<Observable<Integer>>() {
                    @Override
                    public boolean cancel(boolean b) {
                        return false;
                    }

                    @Override
                    public boolean isCancelled() {
                        return false;
                    }

                    @Override
                    public boolean isDone() {
                        return false;
                    }

                    @Override
                    public Observable<Integer> get() throws InterruptedException, ExecutionException {

                        return Observable.just(1,2,3);
                    }

                    @Override
                    public Observable<Integer> get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                        return null;
                    }
                };
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("deferFuture(Func0) onNext;" + integer);
            }
        });
    }
}
