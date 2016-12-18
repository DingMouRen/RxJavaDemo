package com.dingmouren.rxjavademo.创建操作符;

import java.util.concurrent.Future;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dingmouren on 2016/12/17.
 *  From操作符可以转换Future、Iterable和数组，对于Iterable和数组，产生的Observable会发射Iterable或者数组的每一项数据
 *  对于Future，Observable会发射Future.get()方法返回的单个数据，还支持设置超时时间，时间单位，如果到时间没有返回值，就发射错误通知，
 *  另外还可以通过设置Scheduler来管理这个Future,查看from()的重载方法就明白了
 *
 */

public class FromDemo {
    public static void main(String[] args){
        Integer[] nums = {1,2,3,4,5,6};
        Observable.from(nums).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:完成.");
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
    }
}
