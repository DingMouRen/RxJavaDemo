package com.dingmouren.rxjavademo.字符串操作;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.StringObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * decode操作符将一个多字节的字符流转换为一个Observable，它按字符边界发射字节数组
 */

public class DecodeDemo {
    public static void main(String[] args){
        String s = "床前明月光，疑是地上霜；举头望明月，低头思故乡";
        StringObservable.decode(Observable.just(s.getBytes()),"utf-8").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("decode(Observable,Utf-8) onNext:" + s);
            }
        });
    }
}
