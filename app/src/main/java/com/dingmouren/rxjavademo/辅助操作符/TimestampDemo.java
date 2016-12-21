package com.dingmouren.rxjavademo.辅助操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Timestamped;

/**
 * Created by dingmouren on 2016/12/21.
 * timestamp操作符会给Observable发射的每一个数据添加一个时间戳，可以指定执行线程
 */

public class TimestampDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).timestamp().subscribe(new Action1<Timestamped<Integer>>() {
            @Override
            public void call(Timestamped<Integer> integerTimestamped) {
                System.out.println("timestamp() onNext:value="+ integerTimestamped.getValue() +" 时间戳="+integerTimestamped.getTimestampMillis());
            }
        }).unsubscribe();
    }
}
