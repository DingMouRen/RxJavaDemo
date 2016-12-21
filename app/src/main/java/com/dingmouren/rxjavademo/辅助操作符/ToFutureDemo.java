package com.dingmouren.rxjavademo.辅助操作符;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import rx.Observable;

/**
 * Created by dingmouren on 2016/12/21.
 * toFuture操作符只能用于BlockingObservable，这个操作符将Observable转换一个返回单个数据项的Future对象，
 * 如果源Observable发射了多个数据项，就会出现异常，没有任何数据也会抛异常。
 * 发射多个数据项的Observable，使用例子中的方法就可以
 */

public class ToFutureDemo {
    public static void main(String[] args){
        Future future1 = Observable.just(1).toBlocking().toFuture();
        try {
            System.out.println("toFuture " + future1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("- - - - - - - - - -");
        Future future2 = Observable.just(1,2,3).toList().toBlocking().toFuture();
        try {
            System.out.println("toFuture " + future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
