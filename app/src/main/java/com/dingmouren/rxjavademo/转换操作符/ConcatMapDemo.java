package com.dingmouren.rxjavademo.转换操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/18.
 * concatMap():类似于最简单版本的flatMap,唯一不同是concatMap是按次序连接而不是合并那那些生成的Observables，然后产生自己的数据序列，
 *  concatMap操作符在处理产生的Observable时，采用的是“concat”连接的方式，不是“merge”,如果需求是要保证顺序的话建议用concatMap()
 */

public class ConcatMapDemo {
    public static void main(String[] args){
        Integer[] nums = {1,2,3,4,5,6};
        Observable.just(nums).concatMap(new Func1<Integer[], Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer[] integers) {
                return Observable.from(integers);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("onNext:" + integer + " 所在线程：" + Thread.currentThread().getName());
            }
        });

    }
}
