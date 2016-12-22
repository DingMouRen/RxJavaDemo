package com.dingmouren.rxjavademo.字符串操作;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.StringObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * split操作符将源Observable发射的字符串按照指定的正则表达式进行分割发射。注意中英字符的差异
 */

public class SplitDemo {
    public static void main(String[] args){
        Observable<String> ob1 = Observable.just("床前明月光，疑是地上霜，举头望明月，低头思故乡。");
        StringObservable.split(ob1,"，").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("split(Observable,指定的正则表达式规则) onNext:" + s);
            }
        });
    }
}
