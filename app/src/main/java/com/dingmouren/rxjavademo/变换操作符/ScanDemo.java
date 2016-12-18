package com.dingmouren.rxjavademo.变换操作符;

import java.sql.SQLOutput;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by dingmouren on 2016/12/18.
 * scan():通过遍历原来Observable产生的结果，每一次对每一个结果按照指定规则进行运算，
 * 计算的结果作为下一个迭代项参数，每一次都会把迭代项数据发射给订阅者，这里result就是那个迭代项
 * scan()操作符有个变体，可以设置初始值，下方就是讲初始值设置成了6,发射的第一个值就是6而不是1
 */

public class ScanDemo {
    public static void main(String[] args){
        Observable.just(1,2,3,4,5).scan(6,new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer result, Integer item2) {
                return result + item2 ;//result是上一次计算的结果
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("scan onNext " + integer);
            }
        });
    }
}
