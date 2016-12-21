package com.dingmouren.rxjavademo.辅助操作符;

import java.util.Map;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/21.
 * toMap  收集原始Observable发射的所有数据项到一个Map（默认是HashMap）然后发射这个
 Map。你可以提供一个用于生成Map的Key的函数，还可以提供一个函数转换数据项到Map存
 储的值（默认数据项本身就是值）
 */

public class ToMapDemo {
    public static void main(String[] args){
        Observable.just(1).toMap(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                System.out.println("toMap call执行 " + integer);
                return "键";
            }
        }).subscribe(new Action1<Map<String, Integer>>() {
            @Override
            public void call(Map<String, Integer> map) {
                System.out.println("toMap onNext " + map.get("键"));
            }
        });
    }
}
