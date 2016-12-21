package com.dingmouren.rxjavademo.辅助操作符;

import java.util.Iterator;

import rx.Observable;
import rx.observables.BlockingObservable;

/**
 * Created by dingmouren on 2016/12/21.
 * getIterator操作符只能用于BlockingObservable，可以将BlockingObservable转换成一个迭代器，可以通过迭代器的方式获取BlockingObservable发射的数据项
 */

public class GetIteratorDemo {


    public static void main(String[] args){
        Iterator<Integer> iterator1 = Observable.just(1, 2, 3).toBlocking().getIterator();
        while(iterator1.hasNext()){
            System.out.println("getIterator " + iterator1.next());
        }
        System.out.println("- - - - - - - - - - -");
        Iterator<Integer> iterator2 = BlockingObservable.from(Observable.just(1,2,3)).getIterator();
        while(iterator2.hasNext()){
            System.out.println("getIterator " + iterator2.next());
        }
    }
}
