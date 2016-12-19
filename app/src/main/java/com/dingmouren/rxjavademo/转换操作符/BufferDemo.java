package com.dingmouren.rxjavademo.转换操作符;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by dingmouren on 2016/12/18.
 *  Buffer():定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值。
 *  1.Buffer操作符将一个Observable变换成另一个，原来的Observable正常发射数据，变换产生的Observable发射这些数据的缓存集合。
 *  2.如果原来的Observable发射了一个onError通知，buffer会立即传递这个通知，而不是首先发射缓存的数据，即使已经缓存了数据。
 *  3.buffer有很多的变体，有多个重载方法
 */

public class BufferDemo {
    public static void main(String[] args) {
        //1.buffer(count):以列表（List集合）的形式发射非重叠（不是指没有重复元素，而是集合的界限）的缓存数据，每一个缓存（List集合）最多包含来自原始Observable的count个数据（最后发射的列表List可能少于count个，最后一个list可能没有count个元素），此处的count相当于集合的size()
        System.out.println("buffer(count) ");
        Observable.range(1, 6).buffer(2).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("onNnext:" + integers.toString());
            }
        });
        /**
         * 2.buffer(count,skip) :有这么一种情况 buffer(count) 等价于 buffer(count,count).
         * 详细说一下： buffer(2)输出为 【1,2】【3,4】【5,6】 那么buffer(2,3)就要这么算：首先从第一项开始缓存2个数据，【1,2】，此时的skip=3，意思是第二个集合的第一项是第skip+1项的数据，也就是第四个,集合长度是2，结果就是【4,5】，
         * 此时可用的数据是4,5,6，再计算第三个集合的时候，第三个集合的第一项就应该是第skip+1项的数据，然而没有第四个数据，就没有第三个集合了。
         */
        System.out.println("- - - - - - - - - - - - - - - - - - - - - ");
        System.out.println("buffer(count,skip) ");
        Observable.range(1, 6).buffer(3, 2).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("onNnext:" + integers.toString());
            }
        });
    }
}
