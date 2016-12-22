package com.dingmouren.rxjavademo.算术和聚合操作符;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;

/**
 * Created by dingmouren on 2016/12/22.
 * collect操作符将Observable发射的数据收集到一个数据结构中，收集完成后将这个数据结构提交给订阅者。
 * collect(Func0,Action2)参数解释：第一个参数：返回可变数据结构的函数  第二个参数：注册一个动作，修改这个数据结构
 */

public class CollectDemo {
    public static void main(String[] args){
        Observable.just(1,2,3).collect(new Func0<List<Integer>>() {
            @Override
            public List<Integer> call() {
                return new ArrayList<Integer>();
            }
        }, new Action2<List<Integer>, Integer>() {
            @Override
            public void call(List<Integer> integers, Integer integer) {
                System.out.println("Action2的call函数：integers="+ integers.toString() + "  integer:"+ integer);
                integers.add(integer);
            }
        }).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("collect(Func0,Action2) onNext:" + integers.toString());
            }
        });
    }
}
