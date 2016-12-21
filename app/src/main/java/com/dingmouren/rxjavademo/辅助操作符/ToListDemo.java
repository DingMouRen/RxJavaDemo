package com.dingmouren.rxjavademo.辅助操作符;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/21.
 *  toList操作符将Observable发射的多个数据项组合成一个List集合，然后发射这个List集合
 *  默认不在任何指定的调度器上执行
 */

public class ToListDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).toList().subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("toList onNext " + integers.toString());
            }
        });

    }
}
