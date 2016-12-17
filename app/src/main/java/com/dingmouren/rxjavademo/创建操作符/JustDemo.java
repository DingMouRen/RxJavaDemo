package com.dingmouren.rxjavademo.创建操作符;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dingmouren on 2016/12/17.
 *  Just :创建一个发射指定值得Observable
 *  Just只是简单的将数据原样发射，下面就分两种情况来说明，第一种是Int类型的值，第二种是Int[]数组，结果是原来的数据是什么样，发射出来的就是什么样，即使传入null,也会给你发射出一个null。
 *  它可以接受1到9个参数，返回一个按照参数列表顺序发射数据的Observable
 *  操作符form()是将数组或者Iterable中的元素取出后逐个发射
 */

public class JustDemo {
    public static void main(String[] args){
        Observable.just(1,2,3)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted:完成。");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:" + e.toString());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext:" + integer+" 所在线程："+ Thread.currentThread().getName());
                    }
                });

        System.out.println("--------------------------");
        int[] nums = {1,2,3};
        Observable.just(nums)
                .subscribe(new Subscriber<int[]>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted:完成。");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:" + e.toString());
                    }

                    @Override
                    public void onNext(int[] nums) {
                        System.out.println("onNext:" + nums+" 所在线程："+ Thread.currentThread().getName());
                    }
                });
    }
}
