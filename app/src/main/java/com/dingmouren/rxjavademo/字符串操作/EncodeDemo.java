package com.dingmouren.rxjavademo.字符串操作;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.StringObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * encode操作符发射的是字符串的字节数组
 */

public class EncodeDemo {
    public static void main(String[] args){
        final String s = "床前明月光，疑是地上霜；举头望明月，低头思故乡";
        StringObservable.encode(Observable.just(s ),"utf-8").subscribe(new Action1<byte[]>() {
            @Override
            public void call(byte[] bytes) {
                System.out.println("encode onNext:" + bytes.toString());
            }
        });
    }
}
