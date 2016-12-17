package com.dingmouren.rxjavademo.创建操作符;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dingmouren on 2016/12/17.
 *  Range:创建一个发射特定整数序列的Observable，可以通过参数指定线程
 *  第一参数：起始值   第二个参数：终止值  第三个参数：所在线程
 *  如果第二个参数为0，将导致Observable不发送任何数据，如果设置成负数会抛异常
 *  range()默认不在任何特定的调度器上
 */

public class RangeDemo {
    public static void main(String[] args){
        Observable.range(1,5).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:完成" );
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext:" + integer +" 所在线程:"+ Thread.currentThread().getName());
            }
        });
    }
}
