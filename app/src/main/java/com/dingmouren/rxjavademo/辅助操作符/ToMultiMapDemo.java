package com.dingmouren.rxjavademo.辅助操作符;

import java.util.Collection;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/21.
 * toMultiMap操作符类似于toMap操作符，不同的是它生成的Map的值是个clection，可以通过工厂方法修改
 */

public class ToMultiMapDemo {
    public static void main(String[] args){
        Observable.just(1).toMultimap(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "键";
            }
        }).subscribe(new Action1<Map<String, Collection<Integer>>>() {
            @Override
            public void call(Map<String, Collection<Integer>> stringCollectionMap) {
                System.out.println("toMultiMap onNext:" + stringCollectionMap.get("键").toString());
            }
        });
    }
}
