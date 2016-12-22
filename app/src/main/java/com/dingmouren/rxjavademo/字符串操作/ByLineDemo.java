package com.dingmouren.rxjavademo.字符串操作;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.StringObservable;

/**
 * Created by dingmouren on 2016/12/22.
 * byLine操作符将Observable发射的数据项当做流处理，然后按照换行符分割，合成一行。
 */

public class ByLineDemo {
    public static void main(String[] args){
         StringObservable.byLine(Observable.just("床前明月光，","疑是地上霜")).subscribe(new Action1<String>() {
             @Override
             public void call(String s) {
                 System.out.println("StringObservable.byLine(Observable) onNext:" + s);
             }
         });
    }
}
