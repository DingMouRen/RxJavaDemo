package com.dingmouren.rxjavademo;

import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;



public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

//        timerDemo();
//        intervalDemo();
//        switchMapDemo();
//        groupByDemo();
        windowDemo();
    }

    /**
     * 创建操作符timer:创建一个Observable,它在一个给定的延迟后发射一个特殊的值0
     * 默认在computation调度器上运行，可以通过参数指定运行的线程
     */
    private void timerDemo(){
        Observable.timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("timer onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("timer onError:");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("timer onNext:" + aLong +" 所在线程:"+ Thread.currentThread().getName());
            }
        });
    }

    /**
     * 创建操作符interval:创建一个按固定时间间隔发射整数序列的Observable，有多个重载方法，
     * 第一个参数：第一次延时多久发射数据 ，第二个参数：发射数据的时间间隔（从第二次开始），第三个参数：时间单位，第四个参数：指定发射数据所在的线程
     * interval默认的线程是在computation  调度器上，
     * 使用场景一：网络重连，没有连接上网络就一直去连接，直到连接上。
     *
     */
    private void intervalDemo(){
        Observable.interval(3,2,TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("interval onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("interval onError:");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("interval onNext:"+aLong+" 所在线程："+ Thread.currentThread().getName());
            }
        });
    }

    /**
     * switchMap():类似于flatMap(),有一点不同，只监视当前Observable，或者是最后发射的数据。需要延时执行，当是延时是0 的时候回发射第一个数据，延时是大于0的任何值都是发射最后一个值
     * 有一个使用场景：搜索的时候，在输入完毕之后再去请求数据，大可不必没每输入一个都要发送请求。
     */
    private void switchMapDemo(){
        Observable.just(10, 20, 30).switchMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {

                return Observable.just(integer).delay(0, TimeUnit.MILLISECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("switchMap onNext:" + integer);
            }
        });
    }

    /**
     * groupBy():对原来Observable发射的数据进行分组，形成一个GroupedObservable的结果集，GroupedObservable.getKey()可以获取键,
     * 注意：由于结果集中的GroupedObservable是把分组结果缓存起来，如果对每一个GroupedObservable不进行处理（既不订阅也不对其进行别的操作符运算）
     * 就有可能出现内存泄漏，所以如果不对GroupedObservable进行处理，最好对其使用操作符take(0)处理
     *
     */
    private void groupByDemo(){
        Observable.just(1,2,3,4).take(4).groupBy(new Func1<Integer, String>() {
            @Override
            public String call(Integer value) {
                return value < 3 ? "第一组":"第二组"; //这里执行分组的函数
            }
        }).subscribe(new Action1<GroupedObservable<String, Integer>>() {
            @Override
            public void call(final GroupedObservable<String, Integer> result) {
                result.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer value) {
                        System.out.println("GroupBy  onNext " + result.getKey() +":"+ value);
                    }
                });
            }
        });
    }

    /**
     * window():类似于buffer()操作符，区别在于buffer操作符产生的结果是List缓存，而window()操作符产生的是一个Observable对象
     * 订阅者可以对这个产生的Observable对象重新进行订阅处理
     * window操作符有很多重载方法
     */
    private void windowDemo(){
        Observable.interval(1, TimeUnit.SECONDS).take(6)
                .window(3,TimeUnit.SECONDS).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> observable) {
                observable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("window onNext " + aLong);
                    }
                });
            }
        });
    }
}


