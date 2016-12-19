package com.dingmouren.rxjavademo.转换操作符;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dingmouren on 2016/12/18.
 * map():对Observable发射的每一项数据使用函数执行变换操作，然后在发射出去。返回的对象可以随便指定，可以实现一对一的转换，
 * flatmap()：使用指定函数对原始的Observable发射的每一项数据执行变换操作，返回一个Observable，这个Observable也可以发射数据，可以实现一对多转换，可能还会出现次序出错的问题，使用的merge,不能保证原来的顺序，
 * 相同点：都是讲传入的参数转化之后返回另一个对象
 * 不同点：flatmap()返回的是Observable对象，
 * flatMap() 的原理是这样的：1. 使用传入的事件对象创建一个 Observable 对象；2. 并不发送这个 Observable, 而是将它激活，于是它开始发送事件；
 * 3. 每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法。
 * 这三个步骤，把事件拆成了两级，通过一组新创建的 Observable 将初始的对象『铺平』之后通过统一路径分发了下去。而这个『铺平』就是 flatMap() 所谓的 flat。
 */

public class FlatMapAndMapDemo {
    public static void main(String[] args){
        List<String> course = new ArrayList<>();
        course.add("数学");course.add("语文");course.add("英语");
        Student student = new Student("小明",course);
        Observable.just(student).flatMap(new Func1<Student, Observable<String>>() {
            @Override
            public Observable<String> call(Student student) {
                //转换的函数放在这里
                return Observable.from(student.getCourse());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String cour) {
                System.out.println("onNext:" + cour + "  所在线程：" + Thread.currentThread().getName());
            }
        });

        System.out.println("- - - - - - - - - - - - ");
        Observable.just(student).map(new Func1<Student, Student>() {
            @Override
            public Student call(Student student) {
                //转换的函数放在这里
                System.out.println(student.getName());
                student.setName("小刚");
                return student;
            }
        }).subscribe(new Action1<Student>() {
            @Override
            public void call(Student student) {
                System.out.println("onNext:" + student.getName() + " 所在线程：" + Thread.currentThread().getName());
            }
        });

    }
}

class Student{
    private String name;
    private List<String> course = new ArrayList<>();

    public Student(String name, List<String> course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }
}
