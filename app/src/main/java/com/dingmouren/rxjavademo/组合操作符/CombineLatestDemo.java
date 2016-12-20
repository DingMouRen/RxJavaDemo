package com.dingmouren.rxjavademo.组合操作符;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;

/**
 * Created by dingmouren on 2016/12/19.
 *  combineLatest操作符把两个Observable产生的结果进行合并，合并的结果组成一个新的Observable。下面的栗子是ob2中的每一个数据项都与ob1中的最后一项进行相加，将生成的结果组成一个新的Observable对象.
 *  combineLatest操作符可以接受2-9个Observable作为参数，最后一个Observable中的每一个数据项，都与前面Observable中的最后一项进行规则运算。也就是call方法中的最后一个值参数是最后一个Observable的每一项数据，
 *  前面的参数是前面每一个Observable的最后一项数据，固定不变的。
 *  combineLatest(List,FuncN)操作符可以接受一个Observable的list集合，集合中最后一个Observable中的每一项数据，会跟前面每一个Observable对象的最后一项数据进行规则运算
 *  默认不在任何特定的调度器上执行。
 */

public class CombineLatestDemo {
    public static void main(String[] args){
        
        Observable<Integer> ob1 = Observable.just(1,2,3);
        Observable<Integer> ob2 = Observable.just(4,5,6);
        Observable<Integer> ob3 = Observable.just(7,8,9);
        
        List<Observable<Integer>> list = new ArrayList<>();
        list.add(ob1);list.add(ob2);list.add(ob3);

        Observable.combineLatest(ob1, ob2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                System.out.println("combineLatest(o1,o2,Func2):"+"o1:" + integer +" o2:"+ integer2 );
                return integer + integer2;//这里进行合并的规则，可以用函数进行运算返回一个数据
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("combineLatest(o1,o2,Func2) onNext:" + integer);
            }
        });

        System.out.println("- - - - - - - - - ");
        Observable.combineLatest(ob1, ob2, ob3, new Func3<Integer, Integer, Integer, Integer>() {
            @Override//这里进行合并的规则，可以用函数进行运算返回一个数据
            public Integer call(Integer integer, Integer integer2, Integer integer3) {
                System.out.println("combineLatest(o1,o2,o3,Func3):"+"o1:" + integer +" o2:"+ integer2 +" o3:"+ integer3);
                return integer + integer2 + integer3;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("combineLatest(o1,o2,o3,Func3) onNext:" + integer);
            }
        });

        System.out.println("- - - - - - - - - ");
        Observable.combineLatest(list, new FuncN<String>() {
            @Override//这里进行合并的规则，可以用函数进行运算返回一个数据
            public String call(Object... args) {
                String concat = "";
               for (Object value : args){
                   System.out.println("combineLatest(List,FuncN) value:" + value);
                   concat += value;

               }
                return concat;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("combineLatest(List,FuncN) onNext:" + s);
            }
        });
    }
}
