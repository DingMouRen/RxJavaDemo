package com.dingmouren.rxjavademo.字符串操作;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.StringObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * stringConcat操作符将源Observable发射的字符串数据项连接成一个字符串，并将这个字符串提交给订阅者。
 */

public class StringConcatDemo {
    public static void main(String[] args){
        StringObservable.stringConcat(Observable.just("床前明月光，","疑是地上霜","举头望明月，","低头思故乡。")).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("stringConcat(Observable) onNext:" + s);
            }
        });
    }
}
