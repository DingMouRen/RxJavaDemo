package com.dingmouren.rxjavademo.组合操作符;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by dingmouren on 2016/12/20.
 * join操作符将两个Observable产生的结果合并成一个新Observable对象，join操作符可以控制每个Observable产生结果的生命周期。
 * 参数解释 ob1.join(ob2,ob1产生结果生命周期控制函数，ob2产生结果生命周期控制函数，ob1和ob2合并结果的规则)
 * groupJoin()操作符第四个参数与join操作符不同，详细的运行栗子查看
 */

public class JoinDemo {
    public static void main(String[] args){

        Observable<Integer> ob1 = Observable.just(1,2);
        Observable<Integer> ob2 = Observable.just(3,4);
        //join操作符
        ob1.join(ob2, new Func1<Integer, Observable<Integer>>() {//ob1产生结果生命周期控制函数
            @Override
            public Observable<Integer> call(Integer integer) {
                //使ob1延迟200毫秒执行
                return Observable.just(integer).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new Func1<Integer, Observable<Integer>>() {//ob2产生结果声明周期控制函数
            @Override
            public Observable<Integer> call(Integer integer) {
                //使ob2延迟200毫秒执行
                return Observable.just(integer).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new Func2<Integer, Integer, Integer>() {//ob1 和ob2产生结果的合并规则
            @Override
            public Integer call(Integer integer1, Integer integer2) {
                System.out.println("join(ob2,Func1,Func1,Func2) " + "integer1:" +integer1+ " integer2:" + integer2);
                return integer1 + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("join(ob2,Func1,Func1,Func2) " + integer);
            }
        });
        System.out.println(" - - - - - - - - - - ");
        //groupJoin操作符
        ob1.groupJoin(ob2, new Func1<Integer, Observable<Integer>>() {//ob1产生结果生命周期控制函数
            @Override
            public Observable<Integer> call(Integer integer) {
                //使ob1延迟1600毫秒执行
                return Observable.just(integer).delay(1600, TimeUnit.MILLISECONDS);
            }
        }, new Func1<Integer, Observable<Integer>>() {//ob2产生结果声明周期控制函数
            @Override
            public Observable<Integer> call(Integer integer) {
                //使ob2延迟600毫秒执行
                return Observable.just(integer).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Func2<Integer, Observable<Integer>, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(final Integer integer1, Observable<Integer> observable) {
                return observable.map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer2) {
                        System.out.println("groupJoin(ob2,Func1,Func1,Func2) " + "integer1:" + integer1 + " integer2:" + integer2);
                        return integer1 + integer2;
                    }
                });
            }
        }). subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(Observable<Integer> observable) {
                observable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("groupJoin(ob2,Func1,Func1,Func2) onNnext:" + integer);
                    }
                });
            }
        });
    }
}
