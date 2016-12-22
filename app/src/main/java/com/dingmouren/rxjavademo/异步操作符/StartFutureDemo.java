package com.dingmouren.rxjavademo.异步操作符;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.functions.Action1;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by dingmouren on 2016/12/22.
 * startFuture操作符将一个返回Future的函数转换成一个Observable，Observable发射Future的返回值。
 * 传递的参数是一个函数，这个函数会返回一个Future对象，startFuture操作符会立即调用这个函数获取Future对象，
 * 然后调用Future的get(）方法来获取值，操作符创建Observable，Obervable发射这个值给订阅者
 */

public class StartFutureDemo {
    public static void main(String[] args){
        Async.startFuture(new Func0<Future<Integer>>() {
            @Override
            public Future<Integer> call() {
                System.out.println("Call函数所在线程："+ Thread.currentThread().getName());

                return new Future<Integer>() {
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
                    public Integer get() throws InterruptedException, ExecutionException {
                        System.out.println("Future 所在线程："+ Thread.currentThread().getName());
                        return 1;
                    }

                    @Override
                    public Integer get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                        return null;
                    }
                };
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("startFuture(Func0) onNext:" + integer +" 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
