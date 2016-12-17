package com.dingmouren.rxjavademo.创建操作符;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * Created by dingmouren on 2016/12/17.
 *  Defer:直到有观察者订阅时才会创建Observable，并且为每一个观察者创建一个新的Obsevable
 *
 */

public class DeferDemo {
    public static void main(String[] args){
        Student student = new Student();
        Observable<Integer> observable1 = student.justShowAge();
        student.setAge(18);
        observable1.subscribe(new Subscriber<Integer>() {
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
                System.out.println("onNext:" + integer +" 所在线程："+ Thread.currentThread().getName());
            }
        });

        System.out.println("----------------------");

        Observable<Integer> observable2 = student.deferShowAge();
        student.setAge(28);
        observable2.subscribe(new Subscriber<Integer>() {
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
                System.out.println("onNext:" + integer +" 所在线程："+ Thread.currentThread().getName());
            }
        });

    }
}

/**
 * 定义一个学生类，只有一个年龄
 */
class Student{
    public int age;

    public void setAge(int age) {
        this.age = age;
    }
    //just方式，此时age已经进行了初始化，默认是0，create()为每一个订阅者都使用同一个Observable对象，所以将要发射的age的值已经确定了，就是int的默认值0，再设置新值是没有效果的
    public Observable<Integer> justShowAge(){
        return Observable.just(age);
    }
    //defger方式，只有在订阅后才会创建Observable对象，也就是代码中age值被设置成28时，才创建的Observable对象
    public Observable<Integer> deferShowAge(){
        return Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.just(age);
            }
        });
    }
}