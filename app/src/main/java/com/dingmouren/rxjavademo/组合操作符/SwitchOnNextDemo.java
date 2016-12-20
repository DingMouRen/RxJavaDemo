package com.dingmouren.rxjavademo.组合操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/20.
 * switchOnNext操作符是把一组Observable转换成一个Observable，转换规则为：对于这组Observable中的每一个Observable所产生的结果，如果在同一个时间内存在两个或多个Observable提交的结果，只取最后一个Observable提交的结果给订阅者
 */

public class SwitchOnNextDemo {
    public static void main(String[] args){

    }
}
