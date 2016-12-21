package com.dingmouren.rxjavademo.辅助操作符;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by dingmouren on 2016/12/21.
 * toSortedList操作符将Observable发射的数据项按照自然排序进行排序，组合成一个List，再这个List发射出去，默认是自然升序
 * toSortedList(Func2)操作符可以实现倒序排列，具体看下面的例子
 */

public class ToSortedListDemo {
    public static void main(String[] args){
        Observable.just(3,6,2,9,4,21).toSortedList().subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("toSortedList onNext:" + integers.toString());
            }
        });
        System.out.println("- - - - - - - - - - - -");
        Observable.just(3,6,2,9,4,21).toSortedList(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer intHou, Integer intQian) {
                System.out.println("toSortedList(Func2) call函数:intHou=" +intHou + " intQian=" + intQian);
                return intQian.compareTo(intHou);//这两个参数前后倒换可以实现升序和倒序
            }
        }).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("toSortedList(Func2) onNext:" + integers.toString());
            }
        }).unsubscribe();
    }
}
