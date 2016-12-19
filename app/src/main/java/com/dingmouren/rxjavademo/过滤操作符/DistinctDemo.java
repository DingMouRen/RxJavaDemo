package com.dingmouren.rxjavademo.过滤操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/19.
 * distinct()操作符：过滤掉重复的数据项。过滤规则是只允许没有发射过的数据项通过，
 * 变体distinct(Func1)根据返回的key值去过滤，不用数据本身.
 * distinctUntilChanged()只判断这个数据项跟前一个数据项是否相同，distinctUnitilChanged(Func1)也是根据返回的key值去比较过滤。
 * 默认不在任何特定的调度器上执行
 */

public class DistinctDemo {
    public static void main(String[] args){
        Observable.just(1,2,2,3,4,4,5).distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("distinct() onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
        System.out.println("- - - - - - - - - - - -");
        Observable.just(1,2,2,3,4,4,5).distinct(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return 3 < integer ? "第一组" :"第二组";//这里返回key值，小于3的key值是第一组，也就是说1和2的key值都是第一组，只会将1提交给订阅者，2的key值与1相同就直接被过滤掉了，这个变体是根据key值进行过滤的
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("distinct(Func1) onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
        System.out.println("- - - - - - - - - - - -");
        Observable.just(1,2,3,2,4,5,4).distinctUntilChanged().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("distinctUntilChanged() onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });
    }
}
