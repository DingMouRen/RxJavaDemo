package com.dingmouren.rxjavademo.组合操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/20.
 * startWith操作符是在源Observable提交结果之前插入指定的数据，可以是数值，也可以是Observable对象
 */

public class StartWithDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).startWith(0).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("startWith(T) onNext:" + integer);
            }
        });
        System.out.println(" - - - - - -  - - ");
        Observable<Integer> ob2 = Observable.just(4,5,6);
        Observable.just(1,2,3).startWith(ob2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("startWith(T) onNext:" + integer);
            }
        });
    }
}
