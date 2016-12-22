package com.dingmouren.rxjavademo.算术和聚合操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/22.
 */

public class ConcatDemo {
    public static void main(String[] args){
        Observable<Integer> ob1 = Observable.just(1,2,3);
        Observable<Integer> ob2 = Observable.just(4,5,6);

        Observable.concat(ob1,ob2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("concat(ob1,ob2) onNext:" + integer);
            }
        });

    }
}
