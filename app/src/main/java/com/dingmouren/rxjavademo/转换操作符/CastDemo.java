package com.dingmouren.rxjavademo.转换操作符;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/18.
 * cast():类似于map操作符，map()操作符可以自定义规则，将值A1编程A2，A1和A2的类型可以一样也可以不一样；而cast()操作符主要是做类型转换的，
 * 传入参数的类型class，如果Observable发射的结果不能转成指定的class,就会抛出ClassCastException运行异常
 */

public class CastDemo {
    public static void main(String[] args){
        Observable.just("苹果","香蕉").cast(String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("cast onNext " + s);
            }
        });
    }
}
