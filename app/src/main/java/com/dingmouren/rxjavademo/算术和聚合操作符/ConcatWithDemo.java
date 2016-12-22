package com.dingmouren.rxjavademo.算术和聚合操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/22.
 * concatWith操作符不会让多个Observable发射的数据项出现交错的情况
 */

public class ConcatWithDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).concatWith(Observable.just(4,5,6)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("concatWith(Observable) onNext:"+ integer);
            }
        });
    }
}
