package com.dingmouren.rxjavademo.条件和布尔操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/21.
 * exists操作符通过一个函数来判断源Observable是否发射过指定的数据项，如果发射过，操作符就给订阅者返回一个true,否则false
 */

public class ExistsDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).exists(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer == 2;//在此判断知否存在指定的数据项
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("exists(Func1) onNext:" + aBoolean);
            }
        });
    }
}
