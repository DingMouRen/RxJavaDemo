package com.dingmouren.rxjavademo.组合操作符;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by dingmouren on 2016/12/20.
 * zip操作符严格按照顺序进行组合Observable，假设两个Observable合并，ob1发射2个数据，ob2发射3个数据，最终合并的胡发射2个合并的数据。
 * zipWith操作符与上面类似，具体的看下面的例子
 * 默认不在特定的调度器上执行
 */

public class ZipDemo {
    public static void main(String[] args){

        Observable<Integer> ob1 = Observable.just(1,2,3);
        Observable<Integer> ob2 = Observable.just(4,5,6);

        Observable.zip(ob1, ob2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer1, Integer integer2) {
                System.out.println("zip(ob1,ob2,Func2) integer1:" + integer1 +" integer2:"+integer2);
                return integer1 + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("zip(ob1,ob2,Func2) onNext:" + integer);
            }
        });
        System.out.println("- - - - - - - -");
        ob1.zipWith(ob2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer1, Integer integer2) {
                System.out.println("ob1.zipWith(ob2,Func2) integer1:" + integer1 +" integer2:"+integer2);
                return integer1 + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("ob1.zipWith(ob2,Func2)  " + integer );
            }
        });
    }
}
